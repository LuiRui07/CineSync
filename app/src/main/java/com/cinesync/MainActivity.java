package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    DbHelper admin;

    SQLiteDatabase db;
    EditText etTag, etNucleo, etRespuestasTexto, etRespuestasImagenes,etRespuestaCorrecta;

    private void initCine() {
        // Adición de valores a la BD
        ContentValues values = new ContentValues();

        //----TAG DIRECTOR ----//
        // Pregunta 1
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "director");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Scorsese");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://m.media-amazon.com/images/M/MV5BMjIyNTQ5NjQ1OV5BMl5BanBnXkFtZTcwODg1MDU4OA@@._V1_FMjpg_UX1000_.jpg,https://upload.wikimedia.org/wikipedia/commons/c/c0/Citizen_Kane_poster%2C_1941_%28Style_B%2C_unrestored%29.jpg,https://pics.filmaffinity.com/Taxi_Driver-173769074-mmed.jpg,https://pics.filmaffinity.com/Seven_Se7en-498520078-large.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 2);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 2
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "director");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Tarantino");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://www.lahiguera.net/cinemania/pelicula/10297/barbie-cartel-11222.jpg,https://i.pinimg.com/originals/26/4f/ca/264fcaad6f4004948e659fa3fc0f71bd.jpg,https://pics.filmaffinity.com/Heat-911641527-large.jpg,https://musicart.xboxlive.com/7/952f0400-0000-0000-0000-000000000002/504/image.jpg?w=1920&h=1080");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 1);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 3
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "director");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Greta Gerwig");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://www.naoslibros.es/media/img/portadas/_visd_0000JPG02EA2.jpg,https://m.media-amazon.com/images/M/MV5BODA2MjU1NTI1MV5BMl5BanBnXkFtZTgwOTU4ODIwMjE@._V1_FMjpg_UX1000_.jpg,https://m.media-amazon.com/images/M/MV5BMDIzODcyY2EtMmY2MC00ZWVlLTgwMzAtMjQwOWUyNmJjNTYyXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_.jpg,https://m.media-amazon.com/images/M/MV5BY2QzYTQyYzItMzAwYi00YjZlLThjNTUtNzMyMDdkYzJiNWM4XkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 3);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 4
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "director");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Stanley Kubrick");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://i.pinimg.com/originals/d4/96/5a/d4965a1709df4fb9fb2700e7d421f78e.jpg,https://www.lavanguardia.com/peliculas-series/images/movie/poster/1980/10/w1280/upBsuweJjBftrkJGqVf8PHCdH0l.jpg,https://m.media-amazon.com/images/M/MV5BN2EwM2I5OWMtMGQyMi00Zjg1LWJkNTctZTdjYTA4OGUwZjMyXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg,https://m.media-amazon.com/images/M/MV5BZjhkMDM4MWItZTVjOC00ZDRhLThmYTAtM2I5NzBmNmNlMzI1XkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 1);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        //---- TAG OSCARS ----
        // Pregunta 1
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "oscars");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Leonardo Dicaprio");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://es.web.img3.acsta.net/pictures/210/615/21061596_20131129121836144.jpg,https://es.web.img2.acsta.net/pictures/15/12/14/16/38/050093.jpg,https://static.abc.es/media/peliculas/000/044/428/titanic-1.jpg,https://m.media-amazon.com/images/I/71J9BzTif3L._AC_UF894,1000_QL80_.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, "1");
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 2
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "oscars");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Brad Pitt");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://es.web.img3.acsta.net/pictures/19/07/17/09/56/2643815.jpg,https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg,https://pics.filmaffinity.com/El_curioso_caso_de_Benjamin_Button-553974725-large.jpg,https://pics.filmaffinity.com/Moneyball_Rompiendo_las_reglas-572287299-large.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, "0");
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 3
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "oscars");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Meryl Streep");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://m.media-amazon.com/images/M/MV5BYWFjZmE2NGYtNGIzYi00Nzc2LTlhZWMtMTNkOTBkOTQ0MTJmXkEyXkFqcGdeQXVyMTkzODUwNzk@._V1_FMjpg_UX1000_.jpg,https://pics.filmaffinity.com/El_diablo_viste_de_Prada-912143633-large.jpg,https://m.media-amazon.com/images/M/MV5BNDhmNTA0ZDMtYjhkNS00NzEzLWIzYTItOGNkMTVmYjE2YmI3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg,https://pics.filmaffinity.com/La_dama_de_hierro-820040507-large.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, "3");
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 4
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "oscars");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "matthew mcconaughey");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://m.media-amazon.com/images/I/71EgZDWkUYL._AC_UF894,1000_QL80_.jpg, https://pics.filmaffinity.com/Dallas_Buyers_Club-828242648-large.jpg,https://m.media-amazon.com/images/I/81EiNfuGMGL.jpg, https://es.web.img3.acsta.net/pictures/210/615/21061596_20131129121836144.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, "1");


        //---- TAG RANDOM ----
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "Random");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "¿Quien dirigio la película Titanic?");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "Stanley Kubrick, Steven Spielberg, James Cameron, Alfred Hitchcock");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, ""); // Assuming there is no image URL for this entry
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 2);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Inserting values for the second entry


        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "Random");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "¿Cual es el nombre del personaje que interpreta Marlon Brando en El Padrino?");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "Vito Corleone, Michael Corleone, Sonny Corleone, Fredo Corleone");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, ""); // Assuming there is no image URL for this entry
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 0);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Inserting values for the third entry


        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "Random");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "¿De qué película es la famosa frase: Que la fuerza te acompañe?");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, ""); // Leave it empty as there is no RespuestasTexto
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "https://m.media-amazon.com/images/M/MV5BYWFjZmE2NGYtNGIzYi00Nzc2LTlhZWMtMTNkOTBkOTQ0MTJmXkEyXkFqcGdeQXVyMTkzODUwNzk@._V1_FMjpg_UX1000_.jpg");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 3);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Inserting values for the fourth entry


        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "Random");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "¿Cuál es la famosa frase dicha por Robert De Niro en Taxi Driver?");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "I’m the king of the world!, You talking to me?, Say hello to my little friend!");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, ""); // Assuming there is no image URL for this entry
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 1);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        //---- TAG FECHA ----
        // Pregunta 1
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "fecha");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Schindler’s List");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "¿En qué año se estrenó la película Schindler’s List?");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 0);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 2
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "fecha");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Fight Club");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "¿En qué año se estrenó la película Fight Club?");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 3);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 3
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "fecha");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "The Lord of the Rings: The Return of the King");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "¿En qué año se estrenó la película The Lord of the Rings: The Return of the King?");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 1);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);


        // Pregunta 4
        values.clear();
        values.put(CineContract.CineEntry.COLUMN_NAME_TAG, "fecha");
        values.put(CineContract.CineEntry.COLUMN_NAME_NUCLEO, "Avatar");
        values.put(CineContract.CineEntry.COLUMN_NAME_TEXT, "¿En qué año se estrenó la película Avatar?");
        values.put(CineContract.CineEntry.COLUMN_NAME_IMG, "");
        values.put(CineContract.CineEntry.COLUMN_NAME_RIGHT, 0);
        db.insert(CineContract.CineEntry.TABLE_NAME, null, values);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        admin =  new DbHelper(this,"bd1");
        db = admin.getWritableDatabase();


        initCine();
    }


    public void agregar(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put("tag", etTag.getText().toString());
        registro.put("nucleo", etNucleo.getText().toString());
        registro.put("respuestasTexto", etRespuestasTexto.getText().toString());
        registro.put("respuestasImagenes", etRespuestasImagenes.getText().toString());
        registro.put("respuestaCorrecta", etRespuestaCorrecta.getText().toString());
        bd.insert("preguntas",null,registro);

        etTag.setText("");
        etNucleo.setText("");
        etRespuestasTexto.setText("");
        etRespuestasImagenes.setText("");
        etRespuestaCorrecta.setText("");
        //bd.close();

        Toast.makeText(this,"Se almaceno la pregunta", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v){
        SQLiteDatabase bd = admin.getReadableDatabase();
        String tag = etTag.getText().toString();
        Cursor fila = bd.rawQuery("select nucleo,respuestasTexto,respuestasImagenes,respuestaCorrecta from preguntas where tag='"+tag+"'",null);
        if (fila.moveToFirst()){
            etNucleo.setText(fila.getString(0));
            etRespuestasTexto.setText(fila.getString(1));
            etRespuestasImagenes.setText(fila.getString(2));
            etRespuestaCorrecta.setText(fila.getString(3));
        } else {
            Toast.makeText(this,"No existe tal pregunta", Toast.LENGTH_SHORT).show();
            etTag.setText("");
            etNucleo.setText("");
            etRespuestasTexto.setText("");
            etRespuestasImagenes.setText("");
            etRespuestaCorrecta.setText("");
        }
        //bd.close();
    }

    public void borrar(View v){
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tag = etTag.getText().toString();
        int cant = bd.delete("preguntas","tag='"+tag+"'",null);
        if (cant == 1){
            Toast.makeText(this,"Pregunta borrada", Toast.LENGTH_SHORT).show();
            etTag.setText("");
            etNucleo.setText("");
            etRespuestasTexto.setText("");
            etRespuestasImagenes.setText("");
            etRespuestaCorrecta.setText("");
        } else {
            Toast.makeText(this,"No existe tal pregunta", Toast.LENGTH_SHORT).show();
        }
        //bd.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public void pulsado (View v) {
        Intent intento = new Intent(this, Inicio.class);
        Bundle n = new Bundle();
        n.putString("variable","hola");
        intento.putExtras((n));
        startActivity(intento);
    }
}