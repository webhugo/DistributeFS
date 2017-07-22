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
        Config.fileInfoMap.put(fileInfo.getUuid(), fileInfo);
    }
}
