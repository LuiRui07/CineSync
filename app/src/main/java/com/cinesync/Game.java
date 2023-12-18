package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ImageButton ImgOp1,ImgOp2,ImgOp3,ImgOp4;

    boolean respondida = false;

    int rpCorrectaActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            admin = new DbHelper(this, "bd1");

            ImgOp1 = findViewById(R.id.imageButton1);
            ImgOp2 = findViewById(R.id.imageButton2);
            ImgOp3 = findViewById(R.id.imageButton3);
            ImgOp4 = findViewById(R.id.imageButton4);

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
           // do {
                String nucleo = fila.getString(0);
                String respustasTexto = fila.getString(1);
                String respuestasImagenes = fila.getString(2);
                int respuestaCorrecta = fila.getInt(3);
                crearPregunta(nucleo,respustasTexto,respuestasImagenes,respuestaCorrecta);
           // } while (fila.moveToNext());
        }
    }

    public void crearPregunta(String nuc, String rpTX, String rpImg, int rpC){
    //Aqui las creamos como tal 1 a 1.
        rpCorrectaActual = rpC;
        if (!TextUtils.isEmpty(rpImg)){ //Habria que tener en cuenta si hay 3 o 4 imagenes
            String[] enlacesArray = rpImg.split(",");
            ImageButton[] imageButtonsArray = {ImgOp1, ImgOp2, ImgOp3, ImgOp4};

            for (int i = 0; i < enlacesArray.length && i < imageButtonsArray.length; i++) {
                String enlace = enlacesArray[i];
                ImageButton imageButton = imageButtonsArray[i];
                // Utiliza Picasso para cargar la imagen en el ImageButton
                Picasso.get().load(enlace).into(imageButton);
            }
        }

    }

    public void pulsado(View view){
        ImageButton imageButton = (ImageButton) view; //Aqui veo que boton ha pulsado
        //Comprobar si es correcta o no
        int buttonId = imageButton.getId();

        if (buttonId == R.id.imageButton1) {
            Log.d("MiApp", "Se presionó el 1");
        } else if (buttonId == R.id.imageButton2) {
            Log.d("MiApp", "Se presionó el 2");
        }else if (buttonId == R.id.imageButton3) {
            Log.d("MiApp", "Se presionó el 3");
        }else if (buttonId == R.id.imageButton4) {
            Log.d("MiApp", "Se presionó el 4");
        }

    }

}