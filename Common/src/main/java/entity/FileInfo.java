package entity;

/**
 * Created by webhugo on 17-7-16.
 */
public class FileInfo extends Message{
    private String name;
    private Long size;
    private String md5;
    private String uuid;
    private String mainNodeIp;
    private Integer mainNodePort;
    private String backupNodeIp;
    private Integer backupNodePort;

    public Integer getMainNodePort() {
        return mainNodePort;
    }

    public void setMainNodePort(Integer mainNodePort) {
        this.mainNodePort = mainNodePort;
    }

    public Integer getBackupNodePort() {
        return backupNodePort;
    }

    public void setBackupNodePort(Integer backupNodePort) {
        this.backupNodePort = backupNodePort;
    }

    public String getBackupNodeIp() {
        return backupNodeIp;
    }

    public void setBackupNodeIp(String backupNodeIp) {
        this.backupNodeIp = backupNodeIp;
    }

    public String getMainNodeIp() {
        return mainNodeIp;
    }

    public void setMainNodeIp(String mainNodeIp) {
        this.mainNodeIp = mainNodeIp;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
