package helper;

import entity.NodeInfo;
import toStorageNode.Schedule;

import java.util.Iterator;
import java.util.Map;


/**
 * Created by webhugo on 17-7-14.
 */
public class NodeHelper {

    public static void wirteNodeToMap(NodeInfo registerInfo) {
        System.out.println("registerInfo: " + registerInfo);
        //****************************************
        Schedule schedule = Config.scheduleMap.get(registerInfo.getNodeName());
        schedule = schedule == null ? new Schedule() : schedule;
        schedule.invoke(registerInfo.getNodeName());
        Config.scheduleMap.put(registerInfo.getNodeName(), schedule);
        //****************************************
        Map<String, NodeInfo> nodeInfoMap = Config.nodeInfoMap.get("node");
        nodeInfoMap.put(registerInfo.getNodeName(), registerInfo);
    }

    public static NodeInfo getSuitableNode() {
        Map<String, NodeInfo> nodeInfoMap = Config.nodeInfoMap.get("node");
        String name = null;
        Double minVolume = -1.0;

        for (Map.Entry<String, NodeInfo> entry : nodeInfoMap.entrySet()) {
            Double tmp = Double.parseDouble(entry.getValue().getRemainVolume().replace("GB", ""));
            if (tmp > minVolume) {
                name = entry.getKey();
                minVolume = tmp;
            }
        }
        return nodeInfoMap.get(name);
    }

    public static NodeInfo getBackupNode(NodeInfo nodeInfo) {
        String nodeName = nodeInfo.getNodeName();
        Map<String, NodeInfo> nodeInfoMap = Config.nodeInfoMap.get("node");
        String name = null;
        Double minVolume = -1.0;

        for (Map.Entry<String, NodeInfo> entry : nodeInfoMap.entrySet()) {

            Double tmp = Double.parseDouble(entry.getValue().getRemainVolume().replace("GB", ""));
            if (tmp > minVolume && !entry.getValue().getNodeName().equals(nodeName)) {
                name = entry.getKey();
                minVolume = tmp;
            }
        }
        return nodeInfoMap.get(name);
    }

    public static void deleteNode(String nodeName) {
        Map<String, NodeInfo> nodeInfoMap = Config.nodeInfoMap.get("node");
        Iterator it = nodeInfoMap.keySet().iterator();
        while (it.hasNext()) {
            Object key = it.next();
            NodeInfo nodeInfo = nodeInfoMap.get(key);
            if (nodeName.equals(nodeInfo.getNodeName()))
                it.remove();
        }
    }
}
