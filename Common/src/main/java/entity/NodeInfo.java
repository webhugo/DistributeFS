package entity;

import java.io.Serializable;

/**
 * Created by webhugo on 17-7-14.
 */
public class NodeInfo extends Message{
    private String nodeIp;
    private Integer nodePort;
    private String volume;
    private String rootFolder;
    private String nodeName;
    private String remainVolume;
    private Integer fileCount;

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public String getRemainVolume() {
        return remainVolume;
    }

    public void setRemainVolume(String remainVolume) {
        this.remainVolume = remainVolume;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(String rootFolder) {
        this.rootFolder = rootFolder;
    }

    public String getNodeIp() {
        return nodeIp;
    }

    public void setNodeIp(String nodeIp) {
        this.nodeIp = nodeIp;
    }

    public Integer getNodePort() {
        return nodePort;
    }

    public void setNodePort(Integer nodePort) {
        this.nodePort = nodePort;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
