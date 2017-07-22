package toFileServer;


import java.io.*;
import java.net.Socket;

import entity.FileInfo;
import entity.Message;
import entity.MessageType;
import entity.NodeInfo;
import utils.JsonUtils;


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
            message.setType(MessageType.NodeInfo);
            out.writeObject(message);
            // 读取服务器返回的消息
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            return (NodeInfo) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static FileInfo getDownloadNodeInfo(String uuid) {
        try {
            //连接到服务器的8080端口
            Socket client = new Socket("127.0.0.1", 8080);
            client.setSoTimeout(5000);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            NodeInfo nodeInfo = new NodeInfo();
            nodeInfo.setType(MessageType.DownloadNodeInfo);
            nodeInfo.setExtra(uuid);
            out.writeObject(nodeInfo);
            // 读取服务器返回的消息
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            return (FileInfo) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void download(FileInfo fileInfo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileInfo.getName()));
            System.out.println("fileInfo: " + fileInfo);
            Socket client = new Socket(fileInfo.getMainNodeIp(), fileInfo.getMainNodePort());
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            fileInfo.setType(MessageType.Download);
            bwServer.write(JsonUtils.toJson(fileInfo));
            bwServer.newLine();
            bwServer.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null) {
                // 阻塞
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
