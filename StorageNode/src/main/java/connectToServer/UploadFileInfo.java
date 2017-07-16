package connectToServer;

import bootstrap.Config;
import entity.FileInfo;
import entity.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by webhugo on 17-7-16.
 */
public class UploadFileInfo {
    public static void transfer(FileInfo fileInfo) {
        try {
            Socket socket = new Socket(Config.FileServerIP, 8080);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            fileInfo.setType(MessageType.TellFileInfo);
            out.writeObject(fileInfo);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
