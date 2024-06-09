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

    //Suma de a 1 segundo para luego utilizarlo en la estadia para el calculo del factor demanda.
    public void start() {
        timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                UT++;
            }
        }, 0, 1000);
    }

    //Finaliza el temporizador de UT.
    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }
}
