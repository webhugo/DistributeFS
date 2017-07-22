package helper;

import entity.FileInfo;
import entity.NodeInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by webhugo on 17-7-16.
 */
public class FileHelper {
    public static void writeRecord(FileInfo fileInfo) {
        Config.fileInfoMap.put(fileInfo.getUuid(), fileInfo);
    }


    public static FileInfo getRecord(String uuid) {
        FileInfo fileInfo = Config.fileInfoMap.get(uuid);
        return fileInfo;
    }
}
