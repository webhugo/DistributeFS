package helper;

import Config.MainInfo;
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
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(MainInfo.recordFile, true));
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
            sb.append(fileInfo.getBackupNodeName());
            sb.append("|");

            pw.println(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void getRecord(String uuid) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(MainInfo.recordFile));
            String line = "";
            FileInfo fileInfo = new FileInfo();

            while ((line = scanner.nextLine()) != null) {
                System.out.println(line);
                if (line.contains(uuid)) {
                    String[] strings = line.split("\\|");

                }
            }
            System.out.println("=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
