package bootstrap;


import utils.PropsUtil;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

/**
 * Created by webhugo on 17-7-14.
 * read properties file
 */
public class Config {

    public static String propertiesName = null;
    public static String NodeName = null;
    public static Integer NodePort = null;
    public static String RootFolder = null;
    public static String Volume = null;
    public static String FileServerIP = null;
    public static Integer FileServerPort = null;
    public static String NodeIp;
    public static String remainVolume = null;


    public static String fileRecordName = "fileRecord1.md";
    public static Integer fileCount = 0;

    public static void setPropertiesName(String pName,String rName) {
        propertiesName = pName;
        fileRecordName = rName;
        init();
    }

    private static void init() {
        Properties ps = PropsUtil.loadProps(propertiesName);
        NodeName = ps.getProperty("NodeName");
        NodePort = Integer.parseInt(ps.getProperty("NodePort"));
        NodeIp = ps.getProperty("NodeIP");
        RootFolder = ps.getProperty("RootFolder");
        Volume = ps.getProperty("Volume");
        FileServerIP = ps.getProperty("FileServerIP");
        FileServerPort = Integer.parseInt(ps.getProperty("FileServerPort"));
        remainVolume = ps.getProperty("remainVolume");

        try {
            FileReader in = new FileReader(fileRecordName);
            LineNumberReader reader = new LineNumberReader(in);
            //去除前两行
            reader.readLine();
            reader.readLine();
            String strLine = "";
            while (strLine != null) {
                fileCount++;
                strLine = reader.readLine();
            }
            reader.close();
            in.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
