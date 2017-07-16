package entity;

/**
 * Created by webhugo on 17-7-16.
 */
public class FileInfo extends Message{
    private String name;
    private Long size;
    private String md5;
    private String uuid;
    private String mainNodeName;
    private String backupNodeName;

    public String getMainNodeName() {
        return mainNodeName;
    }

    public void setMainNodeName(String mainNodeName) {
        this.mainNodeName = mainNodeName;
    }

    public String getBackupNodeName() {
        return backupNodeName;
    }

    public void setBackupNodeName(String backupNodeName) {
        this.backupNodeName = backupNodeName;
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
