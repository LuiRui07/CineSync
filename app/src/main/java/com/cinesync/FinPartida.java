package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinPartida extends AppCompatActivity {

    TextView mensajeFinText;
    TextView puntuacionText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_partida);

        mensajeFinText = findViewById(R.id.fin);
        puntuacionText = findViewById(R.id.puntos);

        Bundle datos = getIntent().getExtras();
        int puntuacion = datos.getInt("puntuacion");
        mensajeFinText.setText(R.string.finJuego);
        String tuPuntuacion = getString(R.string.puntuacionFinal);
        puntuacionText.setText((tuPuntuacion +" " + puntuacion));
    }

    public void pulsado (View v) {
        Intent intento = new Intent(this, Inicio.class);
        Bundle n = new Bundle();
        n.putString("variable","hola");
        intento.putExtras((n));
        startActivity(intento);
    }




}