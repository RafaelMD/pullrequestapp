package core;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement
public class PullRequest {
    public String url;
    public String branch;
    public String comment;
    public UUID id;
}
