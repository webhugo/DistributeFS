package upload;

import entity.FileInfo;
import entity.MessageType;
import entity.NodeInfo;
import utils.JsonUtils;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by webhugo on 17-7-15.
 * client -->  storage
 * storage --> storage(备份)
 */
public class UploadClient {
    /**
     * @param nodeInfo
     * @param fileName
     * @param type     upload or backup
     * @throws IOException
     */
    public static String upload(NodeInfo nodeInfo, String fileName, MessageType type, FileInfo fileIf) throws IOException {
        Socket client = new Socket(nodeInfo.getNodeIp(), nodeInfo.getNodePort());
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //****************************************
        File file = new File(fileName);
        FileInfo fileInfo = null;
        if (fileIf == null) {
            fileInfo = new FileInfo();
            fileInfo.setName(fileName);
            fileInfo.setSize(file.length());
            fileInfo.setMd5(getMd5ByFile(file));
            fileInfo.setType(type);
            fileInfo.setMainNodeIp(nodeInfo.getNodeName());
        } else
            fileInfo = fileIf;

        bw.write(JsonUtils.toJson(fileInfo));
        bw.newLine();
        //****************************************
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        client.shutdownOutput();
        //****************************************
        br.close();
        //接受反馈
        if (type.equals(MessageType.Upload)) {
            BufferedReader brClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String Uuuid = brClient.readLine();
            return Uuuid;
        }
        return null;
    }

    private static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
