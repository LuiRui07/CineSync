package com.cinesync;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game extends AppCompatActivity {
    TextView preguntaText;
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

    int puntuacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            admin = new DbHelper(this, "bd1");

            ImgOp1 = findViewById(R.id.imageButton1);
            ImgOp2 = findViewById(R.id.imageButton2);
            ImgOp3 = findViewById(R.id.imageButton3);
            ImgOp4 = findViewById(R.id.imageButton4);

            ImgOp1.setVisibility(View.GONE);
            ImgOp2.setVisibility(View.GONE);
            ImgOp3.setVisibility(View.GONE);
            ImgOp4.setVisibility(View.GONE);

            TxOp1 = findViewById(R.id.respuesta1);
            TxOp2 = findViewById(R.id.respuesta2);
            TxOp3 = findViewById(R.id.respuesta3);
            TxOp4 = findViewById(R.id.respuesta4);

            nucleoText = findViewById(R.id.nucleo);
            categoria = obtenerCategoria();
            preguntaText = findViewById(R.id.pregunta);
            preguntaText.setText(stringTraducido(categoria));
            puntuacion = 0;
            crearCategoria(categoria);

    }

    public String stringTraducido(String categoria) {
        String res = "";
        switch (categoria) {
            case "director" :
                res = getString(R.string.director);
                break;
            case "oscars" :
                res = getString(R.string.oscars);
                break;
            case "fecha" :
                res = getString(R.string.fecha);
                break;
            case "descripcion" :
                res = getString(R.string.oscars);
                break;
            case "randomT" :
                res = getString(R.string.randomTitanic);
                break;
            case "randomB" :
                res = getString(R.string.randomBrando);
                break;
            case "randomF" :
                res = getString(R.string.randomForce);
                break;
            case "RandomD" :
                res = getString(R.string.randomDeNiro);
                break;
            default:
                break;
        }

        return res;
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
        if (indexCategoria > categorias.size()-1){
            Toast.makeText(this,R.string.JuegoTerminado, Toast.LENGTH_SHORT).show();
            Intent finJuego = new Intent(this, FinPartida.class);
            finJuego.putExtra("puntuacion", puntuacion);
            finJuego.putExtra("totalPreguntas", getNumeroPreguntas());
            startActivity(finJuego);
        } else {
            categoria = categorias.get(indexCategoria);
            preguntaText.setText(stringTraducido(categoria));
        }
        crearCategoria(categoria);
    }

    private int getNumeroPreguntas() {
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("select count(*) from preguntas",null);
        if (fila.moveToFirst()) {
            return fila.getInt(0);
        }
        return 0;
    }

    public void crearCategoria(String cat){
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("select nucleo,respuestasTexto,respuestasImagenes,respuestaCorrecta from preguntas where tag=?",new String[]{cat});
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
                imageButton.setVisibility(View.VISIBLE);
                // Utiliza Picasso para cargar la imagen en el ImageButton
                Picasso.get()
                    .load(enlace)
                    .resize(450,450)
                    .centerCrop()
                    .into(imageButton);
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
        ImgOp1.setVisibility(View.GONE);
        ImgOp2.setVisibility(View.GONE);
        ImgOp3.setVisibility(View.GONE);
        ImgOp4.setVisibility(View.GONE);
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
            puntuacion++;
            Toast.makeText(this,R.string.Correcta, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,R.string.Incorrecta, Toast.LENGTH_SHORT).show();
        }
        siguientePregunta();

    }

}