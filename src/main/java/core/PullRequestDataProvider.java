package core;

import java.util.List;

public interface PullRequestDataProvider {
    List<PullRequest> list();

    PullRequest insert(PullRequest pr);
}
