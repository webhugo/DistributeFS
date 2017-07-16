package toFileServer;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import entity.Message;
import entity.MessageType;
import entity.NodeInfo;


/**
 * Created by webhugo on 17-7-15.
 */
public class ServerConnect {

    public static NodeInfo getNodeInfo() {
        try {
            //连接到服务器的8080端口
            Socket client = new Socket("127.0.0.1", 8080);
            client.setSoTimeout(5000);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            Message message = new Message();
            message.setType(MessageType.GetNode);
            out.writeObject(message);
            // 读取服务器返回的消息
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            return (NodeInfo) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static NodeInfo getDownloadNodeInfo(String uuid) {
        try {
            //连接到服务器的8080端口
            Socket client = new Socket("127.0.0.1", 8080);
            client.setSoTimeout(5000);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            Message message = new Message();
            message.setType(MessageType.DownloadInfo);
            message.setExtra(uuid);
            out.writeObject(message);
            // 读取服务器返回的消息
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            return (NodeInfo) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
