package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {
    TextView categoriaText;
    DbHelper admin;

    String categoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            admin = new DbHelper(this, "bd1");

            categoria = obtenerCategoria();
            categoriaText = findViewById(R.id.categoria);
            categoriaText.setText(categoria);
            crearPreguntas(categoria);
    }

    public String obtenerCategoria() {
        SQLiteDatabase bd = admin.getReadableDatabase();
        List<String> categorias = new ArrayList<>(); // Obtener todas las categorías distintas

        Cursor fila = bd.rawQuery("SELECT DISTINCT tag FROM preguntas", null);
        if (fila.moveToFirst()) {
            do {
                int index = fila.getColumnIndex("tag");
                String cat= fila.getString(index);
                categorias.add(cat);
            } while (fila.moveToNext());
        }

        fila.close();

        if (!categorias.isEmpty()) {
            Random random = new Random();
            int indiceAleatorio = random.nextInt(categorias.size()); // Obtener índice aleatorio
            return categorias.get(indiceAleatorio); // Devolver categoría aleatoria
        } else {
            return "Sin categorías disponibles";
        }
    }

    public void crearPreguntas(String cat){
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("select nucleo,respuestasTexto,respuestasImagenes,respuestaCorrecta from preguntas where tag='"+cat+"'",null);
        if (fila.moveToFirst()) {
            do {
                String nucleo = fila.getString(0);
                String respustasTexto = fila.getString(1);
                String respuestasImagenes = fila.getString(2);
                int respuestaCorrecta = fila.getInt(3);
                crearPregunta(nucleo,respustasTexto,respuestasImagenes,respuestaCorrecta);
            } while (fila.moveToNext());
        }
    }

    public void crearPregunta(String nuc, String rpTX, String rpImg, int rpC){
    //Aqui las creamos como tal 1 a 1.
    }

}