package com.example.trabajopractico2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i =  new Intent(this,Servicio.class);
        startService(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}