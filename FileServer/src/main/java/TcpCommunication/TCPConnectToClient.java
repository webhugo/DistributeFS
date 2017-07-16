package TcpCommunication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-14.
 */
public class TCPConnectToClient {
    public static void go() throws IOException {
        ServerSocket server = new ServerSocket(8080);
        boolean flag = true;
        while (flag) {
            Socket client = server.accept();
            System.out.println("与客户端连接成功!");
            new Thread(new ServerThread(client)).start();
        }
    }
}
