package pullrequest;

import com.google.inject.Inject;
import core.PullRequestService;
import core.PullRequest;

import javax.ws.rs.*;
import java.util.List;

@Path("pullrequest")
@Consumes("application/json")
@Produces("application/json")
public class PullRequestRC {

    @Inject
    private PullRequestService service;

    @GET
    public List<PullRequest> list() {
        return service.list();
    }

    @POST
    public PullRequest insert(PullRequest pr) {
        return service.insert(pr);
    }
}
