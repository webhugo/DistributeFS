package upload;

import bootstrap.Config;
import connectToServer.UploadFileInfo;
import entity.BackupNodeInfo;
import entity.FileInfo;
import entity.Message;
import entity.MessageType;
import utils.JsonUtils;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

/**
 * Created by webhugo on 17-7-15.
 */
public class UploadServer {
    public static void receiveFile(Socket client) {
        BufferedWriter bw = null;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //****************************************
            String str = br.readLine();
            FileInfo fileInfo = JsonUtils.parse(str, FileInfo.class);
            String uuid = UUID.randomUUID().toString();
            String filepath = Config.RootFolder + "/" + uuid;
            fileInfo.setUuid(uuid);
            fileInfo.setMainNodeName(Config.NodeName);
            //****************************************
            // 封装文本文件
            bw = new BufferedWriter(new FileWriter(filepath));
            String line = null;
            while ((line = br.readLine()) != null) {
                // 阻塞
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            // 给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bwServer.write(uuid);

            //发送到备份节点
            if (!fileInfo.getType().equals(MessageType.Backup)) {
                System.out.println("tobackup");
                String backUpNodeName = BackupNodeHelper.sendToBackNode(filepath);
                fileInfo.setBackupNodeName(backUpNodeName);
                FileHelper.writeRecord(fileInfo);
                //发送到fileServer
                fileInfo.setMainNodeName(Config.NodeName);
                UploadFileInfo.transfer(fileInfo);
            } else {
                FileHelper.writeRecord(fileInfo);
            }
            bwServer.newLine();
            bwServer.flush();

        } catch (Exception e) {

        } finally {
            if (bw != null)
                // 释放资源
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
