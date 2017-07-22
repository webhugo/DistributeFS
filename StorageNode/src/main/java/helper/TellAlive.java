package helper;

import bootstrap.Config;
import entity.MessageType;
import entity.NodeInfo;
import utils.JsonUtils;

import java.io.IOException;
import java.net.*;

/**
 * Created by webhugo on 17-7-14.
 * send udp data to server to tell alive
 */
public class TellAlive {


    static final Integer TIMEOUT = 1000;

    public static void sendUDP() throws IOException {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeIp(Config.NodeIp);
        nodeInfo.setType(MessageType.Survival);
        nodeInfo.setNodeName(Config.NodeName);
        nodeInfo.setNodePort(Config.NodePort);
        nodeInfo.setVolume(Config.Volume);
        nodeInfo.setRemainVolume(Config.remainVolume);
        //客户端在8000端口监听接收到的数据
        DatagramSocket ds = new DatagramSocket(8001);
        InetAddress loc = InetAddress.getLocalHost();
        //定义用来发送数据的DatagramPacket实例
        DatagramPacket dp_send = new DatagramPacket(JsonUtils.toJson(nodeInfo).getBytes(),
                JsonUtils.toJson(nodeInfo).length(), loc, 8000);
        ds.setSoTimeout(TIMEOUT);
        ds.send(dp_send);
        ds.close();
    }

    public static void main(String[] args) {
        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.setNodeIp(Config.NodeIp);
        nodeInfo.setType(MessageType.Survival);
        System.out.println(nodeInfo);
        System.out.println(JsonUtils.toJson(nodeInfo).length());
    }
}
