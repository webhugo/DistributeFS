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
    public static void main(String[] args) throws IOException {
        NodeInfo nodeInfo = ServerConnect.getNodeInfo();
//        UploadClient.upload(nodeInfo, "a.txt", MessageType.Upload);
        ServerConnect.getDownloadNodeInfo("736fbb3e-ca30-49c3-9a5e-a83153cfbf4c");
    }
}
