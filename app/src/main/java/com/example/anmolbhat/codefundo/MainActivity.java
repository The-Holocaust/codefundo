package com.example.anmolbhat.codefundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button precaution = findViewById(R.id.bttnprecaution);
        Button help = findViewById(R.id.bttnhelp);
        Button relief = findViewById(R.id.bttnrelief);
        Button find = findViewById(R.id.bttnfind);
        Button signuphelp = findViewById(R.id.bttnhelper);

        precaution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
        signuphelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });
        relief.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity(){
        Intent newAct = new Intent(this, precaution_activity.class);
        startActivity(newAct);
    }
    public void openActivity1(){
        Intent newAct = new Intent(this, MapsActivity.class);
        startActivity(newAct);
    }
    public void openActivity2(){
        Intent newAct = new Intent(this, relief_activity.class);
        startActivity(newAct);
    }
    public void openActivity3(){
        Intent newAct = new Intent(this, people_activity.class);
        startActivity(newAct);
    }
    public void openActivity4(){
        Intent newAct = new Intent(this, helper_signup.class);
        startActivity(newAct);
    }
}
