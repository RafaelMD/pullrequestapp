package app;

import com.google.inject.Inject;
import core.PullRequestService;
import core.PullRequest;
import core.PullRequestDataProvider;

import java.util.List;

public class PullRequestServiceImpl implements PullRequestService {

    @Inject
    private PullRequestDataProvider provider;

    @Override
    public List<PullRequest> list() {
        return provider.list();
    }

    @Override
    public PullRequest insert(PullRequest pr) {
        return provider.insert(pr);
    }
}
