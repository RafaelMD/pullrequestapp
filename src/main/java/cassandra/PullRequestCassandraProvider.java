package cassandra;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;
import com.google.inject.Inject;
import com.google.inject.Provider;
import core.PullRequest;
import core.PullRequestDataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PullRequestCassandraProvider implements PullRequestDataProvider {

    private final String KEYSPACE = "PullRequestListKeySpace";
    private final String TABLE = "PullRequest";

    private Session session;

    @Inject
    public PullRequestCassandraProvider(Provider<Session> sessionProvider){
        this.session = sessionProvider.get();
    }

    @Override
    public List<PullRequest> list() {
        Select select = QueryBuilder.select().from(KEYSPACE, TABLE);
        ResultSet resultSet = session.execute(select);
        return convertPullRequests(resultSet);
    }

    @Override
    public PullRequest insert(PullRequest pr) {
        UUID id = UUIDs.timeBased();

        Insert insert = QueryBuilder.insertInto(KEYSPACE, TABLE)
                .value("id", id)
                .value("url", pr.url)
                .value("branch", pr.branch)
                .value("comment", pr.comment);
        session.execute(insert);

        pr.id = id;
        return pr;
    }

    private List<PullRequest> convertPullRequests(ResultSet resultSet) {
        List<PullRequest> list = new ArrayList<>();

        for(Row row: resultSet.all())
            list.add(convertPullRequest(row));

        return list;
    }

    private PullRequest convertPullRequest(Row row) {
        PullRequest pr = new PullRequest();
        pr.comment = row.getString("comment");
        pr.url = row.getString("url");
        pr.branch = row.getString("branch");
        pr.id = row.getUUID("id");
        return pr;
    }
}
