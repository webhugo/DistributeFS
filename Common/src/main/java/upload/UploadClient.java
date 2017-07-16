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
    public static void upload(NodeInfo nodeInfo, String fileName, MessageType type) throws IOException {
        System.out.print("upload client nodeInfo: ");
        System.out.println(nodeInfo);
        Socket client = new Socket(nodeInfo.getNodeIp(), nodeInfo.getNodePort());
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        //****************************************
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setSize(new File(fileName).length());
        fileInfo.setMd5(getMd5ByFile(new File(fileName)));
        fileInfo.setType(type);
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
        //接受反馈
        BufferedReader brClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String feedback = brClient.readLine();
        System.out.println(feedback);
        br.close();
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
