import entity.FileInfo;
import entity.MessageType;
import entity.NodeInfo;
import toFileServer.ServerConnect;
import upload.UploadClient;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-14.
 */
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        NodeInfo nodeInfo = ServerConnect.getNodeInfo();
        String uuid = UploadClient.upload(nodeInfo, "a.txt", MessageType.Upload, null);
        System.out.println("uuid: "+uuid);
        Thread.sleep(1000L);
        FileInfo fileInfo = ServerConnect.getDownloadNodeInfo(uuid);
        System.out.println("fileInfo1: "+fileInfo);
        ServerConnect.download(fileInfo);
    }
}
