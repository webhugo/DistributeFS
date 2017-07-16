package entity;

/**
 * Created by webhugo on 17-7-16.
 */
public class RequestBackup extends Message{
    private String nodeName;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
}
