package upload;

import bootstrap.Config;
import entity.FileInfo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created by webhugo on 17-7-16.
 */
public class FileHelper {
    public static void writeRecord(FileInfo fileInfo) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(Config.fileRecordName, true));
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            sb.append(fileInfo.getUuid());
            sb.append("|");
            sb.append(fileInfo.getName());
            sb.append("|");
            sb.append(fileInfo.getSize());
            sb.append("|");
            sb.append(fileInfo.getMd5());
            sb.append("|");
            sb.append(fileInfo.getMainNodeName());
            sb.append("|");
            if (fileInfo.getBackupNodeName() != null)
                sb.append(fileInfo.getBackupNodeName());
            sb.append("|");

            pw.println(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
