package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void jugar (View v) {
        Intent intento = new Intent(this,Game.class);
        Bundle n = new Bundle();
        n.putString("variable","hola");
        intento.putExtras((n));
        startActivity(intento);
    }
}