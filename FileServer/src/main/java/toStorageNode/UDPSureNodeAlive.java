package toStorageNode;

import Config.MainInfo;
import entity.Message;
import entity.MessageType;
import entity.NodeInfo;
import helper.NodeHelper;
import utils.JsonUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by webhugo on 17-7-14.
 */
public class UDPSureNodeAlive {

    public static void go() throws IOException {
        byte[] buf = new byte[1024];
        DatagramSocket ds = new DatagramSocket(8000);
        DatagramPacket dp_receive = new DatagramPacket(buf, 1024);

        boolean flag = true;
        while (flag) {
            ds.receive(dp_receive);
            try {

                String str = new String(dp_receive.getData(), 0, dp_receive.getLength());
                Message message = JsonUtils.parse(str, Message.class);
                if (message.getType() == MessageType.Register) {
                    NodeInfo nodeInfo = JsonUtils.parse(str, NodeInfo.class);
                    NodeHelper.wirteNodeToMap(nodeInfo);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            dp_receive.setLength(1024);
        }
        ds.close();
    }
}
