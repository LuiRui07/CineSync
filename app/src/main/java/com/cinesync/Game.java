package com.cinesync;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.sax.TextElementListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game extends AppCompatActivity {
    TextView categoriaText;
    TextView nucleoText;
    DbHelper admin;
    String categoria;
    List<String> categorias;
    int indexCategoria;
    ImageButton ImgOp1,ImgOp2,ImgOp3,ImgOp4;
    TextView TxOp1,TxOp2,TxOp3,TxOp4;
    boolean tipoImagen = false;
    boolean finCategoria = false;
    Cursor fila;
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

            TxOp1 = findViewById(R.id.respuesta1);
            TxOp2 = findViewById(R.id.respuesta2);
            TxOp3 = findViewById(R.id.respuesta3);
            TxOp4 = findViewById(R.id.respuesta4);

            nucleoText = findViewById(R.id.nucleo);
            categoria = obtenerCategoria();
            categoriaText = findViewById(R.id.categoria);
            categoriaText.setText(categoria);
            crearCategoria(categoria);

    }

    public String obtenerCategoria() {
        categorias = obtenerCategorias();
        categoria = categorias.get(0);
        indexCategoria = 0 ;

        return categoria;
    }

    public List<String> obtenerCategorias() {
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
            Collections.shuffle(categorias);
        } else {
            Toast.makeText(this,R.string.SinCategorias, Toast.LENGTH_SHORT).show();
        }
        return categorias;
    }



    public void siguienteCategoria(){
        indexCategoria++;
        if (indexCategoria > categorias.size()){
            Toast.makeText(this,R.string.JuegoTerminado, Toast.LENGTH_SHORT).show();
        } else {
            categoria = categorias.get(indexCategoria);
            categoriaText.setText(categoria);
        }
        crearCategoria(categoria);
    }

    public void crearCategoria(String cat){
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("select nucleo,respuestasTexto,respuestasImagenes,respuestaCorrecta from preguntas where tag='"+cat+"'",null);
        if (fila.moveToFirst()) {
                String nucleo = fila.getString(0);
                String respustasTexto = fila.getString(1);
                String respuestasImagenes = fila.getString(2);
                int respuestaCorrecta = fila.getInt(3);
                this.fila = fila;
                crearPregunta(nucleo,respustasTexto,respuestasImagenes,respuestaCorrecta);
        }
    }

    public void siguientePregunta() throws InterruptedException {
        borrarTodo();
        if (fila.moveToNext()){
            String nucleo = fila.getString(0);
            String respustasTexto = fila.getString(1);
            String respuestasImagenes = fila.getString(2);
            int respuestaCorrecta = fila.getInt(3);
            crearPregunta(nucleo,respustasTexto,respuestasImagenes,respuestaCorrecta);
        } else {
            Toast.makeText(this,R.string.CategoriaCompletada, Toast.LENGTH_SHORT).show();
            sleep(3);
            siguienteCategoria();
        }
    }

    public void crearPregunta(String nuc, String rpTX, String rpImg, int rpC){
    //Aqui las creamos como tal 1 a 1.
        rpCorrectaActual = rpC;
        nucleoText.setText(nuc);
        if (!TextUtils.isEmpty(rpImg)){ //Habria que tener en cuenta si hay 3 o 4 imagenes
            tipoImagen = true;
            String[] enlacesArray = rpImg.split(",");
            ImageButton[] imageButtonsArray = {ImgOp1, ImgOp2, ImgOp3, ImgOp4};
            for (int i = 0; i < enlacesArray.length && i < imageButtonsArray.length; i++) {
                String enlace = enlacesArray[i];
                ImageButton imageButton = imageButtonsArray[i];
                // Utiliza Picasso para cargar la imagen en el ImageButton
                Picasso.get().load(enlace).into(imageButton);
            }
        } else {
            tipoImagen = false;
            String[] respuestas = rpTX.split(",");
            TextView[] respuestasTextArray = {TxOp1, TxOp2, TxOp3, TxOp4} ;
            for (int i = 0; i < respuestas.length && i < respuestasTextArray.length; i++) {
                String respuesta = respuestas[i];
                TextView textView = respuestasTextArray[i];
                textView.setText(respuesta);
            }
        }

    }

    public void borrarTodo(){
        ImgOp1.setImageDrawable(null);
        ImgOp2.setImageDrawable(null);
        ImgOp3.setImageDrawable(null);
        ImgOp4.setImageDrawable(null);
        TxOp1.setText("");
        TxOp2.setText("");
        TxOp3.setText("");
        TxOp4.setText("");
    }

    public void pulsado(View view) throws InterruptedException {
        int respuesta = -1;

        if (tipoImagen){
            ImageButton imageButton = (ImageButton) view; //Aqui veo que boton ha pulsado
            //Comprobar si es correcta o no
            int buttonId = imageButton.getId();
            if (buttonId == R.id.imageButton1) {
                Log.d("MiApp", "Se presionó el 1");
                respuesta = 0;
            } else if (buttonId == R.id.imageButton2) {
                Log.d("MiApp", "Se presionó el 2");
                respuesta = 1;
            }else if (buttonId == R.id.imageButton3) {
                Log.d("MiApp", "Se presionó el 3");
                respuesta = 2;
            }else if (buttonId == R.id.imageButton4) {
                Log.d("MiApp", "Se presionó el 4");
                respuesta = 3;
            }
        } else {
            TextView textView = (TextView) view;
            int textId = textView.getId();
            if (textId == R.id.respuesta1) {
                Log.d("MiApp", "Se presionó el 1");
                respuesta = 0;
            } else if (textId == R.id.respuesta2) {
                Log.d("MiApp", "Se presionó el 2");
                respuesta = 1;
            }else if (textId == R.id.respuesta3) {
                Log.d("MiApp", "Se presionó el 3");
                respuesta = 2;
            }else if (textId == R.id.respuesta4) {
                Log.d("MiApp", "Se presionó el 4");
                respuesta = 3;
            }
        }

        if (respuesta == rpCorrectaActual){
            Toast.makeText(this,R.string.Correcta, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,R.string.Incorrecta, Toast.LENGTH_SHORT).show();
        }
        siguientePregunta();

    }

}