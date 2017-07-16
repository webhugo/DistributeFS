package helper;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by webhugo on 17-7-14.
 * set interval to tell server alive
 */
public class Schedule {

    public static void go() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    TellAlive.sendUDP();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 3000L);
    }
}
