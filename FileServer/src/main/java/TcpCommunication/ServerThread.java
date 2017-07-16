package TcpCommunication;

import entity.*;
import helper.FileHelper;
import helper.NodeHelper;
import utils.JsonUtils;

import java.io.*;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-14.
 */
public class ServerThread implements Runnable {
    private Socket client = null;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            //获取Socket的输入流，用来接收从客户端发送过来的数据
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            //发送返回给客户端
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            Message message = (Message) in.readObject();
            switch (message.getType()) {
                case GetNode://来自客户端获取可用节点
                    NodeInfo nodeInfo = NodeHelper.getSuitableNode();
                    System.out.print("nodeinfo: ");
                    System.out.println(nodeInfo);
                    out.writeObject(nodeInfo);
                    break;
                case Backup://来自子节点获取备份节点
                    RequestBackup requestBackup = (RequestBackup) message;
                    System.out.print("requestBackup: ");
                    System.out.println(requestBackup);
                    NodeInfo nodeInfo1 = NodeHelper.getBackupNode(requestBackup);
                    System.out.print("nodeinfo1: ");
                    System.out.println(nodeInfo1);
                    out.writeObject(nodeInfo1);
                    break;
                case TellFileInfo:
                    FileInfo fileInfo = (FileInfo) message;
                    System.out.print("tellfileInfo:");
                    System.out.println(fileInfo);
                    FileHelper.writeRecord(fileInfo);
                    break;
                case DownloadInfo:
                    String uuid = message.getExtra();
                    FileHelper.getRecord(uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
