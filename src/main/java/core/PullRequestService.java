package core;

import java.util.List;

public interface PullRequestService {
    List<PullRequest> list();

    PullRequest insert(PullRequest pr);
}
