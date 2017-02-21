package br.com.thiengo.superplacar.extras;

import android.os.SystemClock;
import java.lang.ref.WeakReference;

import br.com.thiengo.superplacar.MainActivity;


public class Worker extends Thread {
    private WeakReference<MainActivity> activity;

    public Worker( MainActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    public void run() {
        super.run();

        while( activity.get() != null ){
            SystemClock.sleep(60000);
            new SuperPlacarRequest( activity.get() ).execute();
        }
    }
}
