package upload;

import bootstrap.Config;
import entity.FileInfo;
import entity.MessageType;
import entity.NodeInfo;

import java.io.*;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-16.
 */
public class BackupNodeHelper {
    private static NodeInfo askForBackupNode() {
        try {
            Socket socket = new Socket(Config.FileServerIP, 8080);
            NodeInfo requestBackup = new NodeInfo();
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

    public static NodeInfo sendToBackNode(FileInfo fileInfo, String filepath) {
        NodeInfo nodeInfo = askForBackupNode();
        try {
            fileInfo.setType(MessageType.Backup);
            UploadClient.upload(nodeInfo, filepath, MessageType.Backup, fileInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodeInfo;
    }
}
