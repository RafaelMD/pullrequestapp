package config;

import app.PullRequestServiceImpl;
import cassandra.PullRequestCassandraProvider;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import core.PullRequestDataProvider;
import core.PullRequestService;

public class AppModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(PullRequestDataProvider.class).to(PullRequestCassandraProvider.class);
        bind(PullRequestService.class).to(PullRequestServiceImpl.class);
    }

    @Provides
    public Session sessionProvider(){
        Cluster cluster = Cluster.builder()
                .withPort(9042)
                .addContactPoint("127.0.0.1")
                .build();
        cluster.getConfiguration().getSocketOptions().setReadTimeoutMillis(9999);
        return cluster.connect();
    }
}