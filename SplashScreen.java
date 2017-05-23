package com.example.elisabeth.jogodavelha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

/**
 * Created by Elisabeth on 22/05/2017.
 */

public class SplashScreen extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally{
                    Intent intent = new Intent(SplashScreen.this, ActMain.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    protected void onPause(){
        super.onPause();
        finish();
    }
}
