package TcpCommunication;

import entity.*;
import helper.FileHelper;
import helper.NodeHelper;

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
                case NodeInfo://来自客户端获取可用节点
                    NodeInfo nodeInfo = NodeHelper.getSuitableNode();
                    out.writeObject(nodeInfo);
                    break;
                case DownloadNodeInfo:
                    String uuid = message.getExtra();
                    FileInfo fileInfo1 = FileHelper.getRecord(uuid);
                    System.out.println("fileInfo1: "+fileInfo1);
                    out.writeObject(fileInfo1);
                    out.flush();
                    break;
                case Delete:
                    break;
                case Backup://来自子节点获取备份节点
                    NodeInfo requestBackup = (NodeInfo) message;
                    NodeInfo nodeInfo1 = NodeHelper.getBackupNode(requestBackup);
                    out.writeObject(nodeInfo1);
                    break;
                case TellFileInfo:
                    FileInfo fileInfo = (FileInfo) message;
                    System.out.println("tellFileInfo: " + fileInfo);
                    FileHelper.writeRecord(fileInfo);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
