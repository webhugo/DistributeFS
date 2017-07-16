import TcpCommunication.TCPConnectToClient;
import toStorageNode.UDPSureNodeAlive;

import java.io.IOException;

/**
 * Created by webhugo on 17-7-15.
 */
public class Bootstrap {
    public static void main(String[] args) throws IOException {
        //新开线程后台保证node存活
        new Thread(() -> {
            try {
                UDPSureNodeAlive.go();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //信息交互
        new Thread(() -> {
            try {
                TCPConnectToClient.go();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
