package dominio;

import java.util.Timer;
import java.util.TimerTask;

public class TemporizadorUT {
    private Timer timer;
    private int UT;

    public TemporizadorUT() {
        this.UT = 0;
    }

    public int getUT() {
        return UT;
    }

    public void start() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                UT++;
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
