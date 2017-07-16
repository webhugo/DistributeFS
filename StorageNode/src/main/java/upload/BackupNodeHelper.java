package upload;

import bootstrap.Config;
import entity.BackupNodeInfo;
import entity.MessageType;
import entity.NodeInfo;
import entity.RequestBackup;
import utils.JsonUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by webhugo on 17-7-16.
 */
public class BackupNodeHelper {
    private static NodeInfo askForBackupNode() {
        try {
            Socket socket = new Socket(Config.FileServerIP, 8080);
            RequestBackup requestBackup = new RequestBackup();
            requestBackup.setNodeName(Config.NodeName);
            requestBackup.setType(MessageType.Backup);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(requestBackup);
            ObjectInputStream in = new ObjectInputStream((socket.getInputStream()));
            return (NodeInfo) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendToBackNode(String fileName) {
        NodeInfo nodeInfo = askForBackupNode();
        System.out.println(nodeInfo);
        try {
            UploadClient.upload(nodeInfo, fileName, MessageType.Backup);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodeInfo.getNodeName();
    }
}
