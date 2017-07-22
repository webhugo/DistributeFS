package bootstrap;

import helper.Schedule;
import upload.TCPService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-14.
 * startup the node
 */
public class StartNode {
    public static void main(String[] args) throws IOException {

        String[] strings = args[0].split("=");
        String[] strings1 = args[1].split("=");
        Config.setPropertiesName(strings[1], strings1[1]);
        //定时任务向服务器发送udp报文
        Schedule.go();
        //多线程并发上传文件和下载文件
        ServerSocket socket = new ServerSocket(Config.NodePort);
        new Thread(() -> {
            while (true) {

                try {
                    Socket client = socket.accept();
                    new Thread(() -> {

                        TCPService.accept(client);

                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
