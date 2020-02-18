package com.acamara.dm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page_logo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getStarted();
            }
        }, 5000);   //5 seconds
    }
    public void getStarted(){
        Intent intent = new Intent(this, Get_started.class);
        startActivity(intent);
    }
}
