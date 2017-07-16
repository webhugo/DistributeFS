package toStorageNode;

import helper.NodeHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by webhugo on 17-7-16.
 */
public class Schedule {
    private TimerTask timeout = null;
    private String nodeName;

    public void init() {
        Timer timer = new Timer();
        timeout = new TimeOutTask();
        timer.schedule(timeout, 10000L);//10秒不来,就当做子节点已经死了
    }

    public void invoke(String nName) {
        if (timeout != null)
            timeout.cancel();// 取消当前的 timeout 任务
        nodeName = nName;
        init();
    }


    class TimeOutTask extends TimerTask {
        @Override
        //从map中删除对应的node
        public void run() {
            System.out.println("delete node " + nodeName);
            NodeHelper.deleteNode(nodeName);
        }
    }
}
