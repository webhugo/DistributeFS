package Config;

import entity.NodeInfo;
import toStorageNode.Schedule;
import utils.PropsUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;

/**
 * Created by webhugo on 17-7-14.
 */
public class MainInfo {
    public static String recordFile = null;
    public static Integer server_tcp_port;
    public static Integer server_udp_port;
    public static Map<String, Map<String, NodeInfo>> nodeInfoMap = new HashMap<>();
    public static Map<String, Schedule> scheduleMap = new HashMap<>();

    static {
        nodeInfoMap.put("node",new HashMap<>());
        Properties ps = PropsUtil.loadProps("config.properties");
        recordFile = ps.getProperty("recordFile");
        server_tcp_port = Integer.valueOf(ps.getProperty("server_tcp_port"));
        server_udp_port = Integer.valueOf(ps.getProperty("server_udp_port"));
    }
}
