package com.acamara.dm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.acamara.dm.R;
import com.acamara.dm.login;

public class Get_started extends AppCompatActivity {
    private TextView gotoSignIN;
    private Button gotoSignUP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gotoSignIN = findViewById(R.id.get_started_sign_in_button);
        gotoSignUP = findViewById(R.id.get_started_signup_btn);


    }
    public void setGotoSignIN(View view){
        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }
}
