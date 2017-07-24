package upload;

import bootstrap.Config;
import connectToServer.UploadFileInfo;
import entity.FileInfo;
import entity.Message;
import entity.MessageType;
import entity.NodeInfo;
import utils.JsonUtils;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by webhugo on 17-7-15.
 */
public class TCPService {
    public static void accept(Socket client) {
        BufferedWriter bw = null;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //****************************************
            String str = br.readLine();
            Message message = JsonUtils.parse(str, Message.class);
            System.out.println("message: " + message);

            if (message.getType().equals(MessageType.Upload) || message.getType().equals(MessageType.Backup)) {
                FileInfo fileInfo = JsonUtils.parse(str, FileInfo.class);
                System.out.println("type: " + fileInfo.getType());
                System.out.println(fileInfo);
                String uuid = fileInfo.getUuid() == null ? UUID.randomUUID().toString() : fileInfo.getUuid();
                fileInfo.setUuid(uuid);
                System.out.println(fileInfo);
                String filepath = Config.RootFolder + "/" + uuid;
                if (fileInfo.getType().equals(MessageType.Backup))
                    fileInfo.setBackupNodeIp(Config.NodeName);

                //****************************************
                // 封装文本文件
                System.out.println("filepath: " + filepath);
                bw = new BufferedWriter(new FileWriter(filepath));
                String line = null;
                while ((line = br.readLine()) != null) {
                    // 阻塞
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
                Config.fileInfoMap.put(uuid, fileInfo);
                // 给出反馈
                if (fileInfo.getType().equals(MessageType.Upload)) {
                    BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                    bwServer.write(uuid);
                    bwServer.newLine();
                    bwServer.flush();
                }

                //发送到备份节点
                if (fileInfo.getType().equals(MessageType.Upload)) {
                    NodeInfo nodeInfo = BackupNodeHelper.sendToBackNode(fileInfo, filepath);
                    fileInfo.setBackupNodeIp(nodeInfo.getNodeIp());
                    fileInfo.setBackupNodePort(nodeInfo.getNodePort());
                    //发送到fileServer
                    fileInfo.setMainNodeIp(Config.NodeIp);
                    fileInfo.setMainNodePort(Config.NodePort);
                    UploadFileInfo.transfer(fileInfo);
                    FileHelper.writeRecord(fileInfo);
                } else if (fileInfo.getType().equals(MessageType.Backup)) {
                    FileHelper.writeRecord(fileInfo);
                }


            } else if (message.getType().equals(MessageType.Download)) {
                FileInfo fileInfo = JsonUtils.parse(str, FileInfo.class);
                System.out.println("fileInfo: " + fileInfo);
                BufferedReader fileBr = new BufferedReader(new FileReader(Config.RootFolder + "/" + fileInfo.getUuid()));
                //****************************************
                String line = null;
                bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                while ((line = fileBr.readLine()) != null) {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
                client.shutdownOutput();
                //****************************************

            } else if (message.getType().equals(MessageType.Delete)) {
                FileInfo fileInfo = JsonUtils.parse(str, FileInfo.class);
                String filepath = Config.RootFolder + "/" + fileInfo.getUuid();
                File file = new File(filepath);
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e) {

        } finally {
            if (bw != null)
                // 释放资源
                try {
                    bw.close();
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


        }
    }
}
