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
    EditText etTag, etNucleo, etRespuestasTexto, etRespuestasImagenes,etRespuestaCorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTag = findViewById(R.id.etTag);
        etNucleo = findViewById(R.id.etNucleo);
        etRespuestasTexto = findViewById(R.id.etRespuestasTexto);
        etRespuestasImagenes = findViewById(R.id.etRespuestasImagenes);
        etRespuestaCorrecta= findViewById(R.id.etRespuestaCorrecta);
        admin =  new DbHelper(this,"bd1");

        admin.insertDataFromCSV("preguntas");
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

    public void pulsado (View v) {
        Intent intento = new Intent(this, Inicio.class);
        Bundle n = new Bundle();
        n.putString("variable","hola");
        intento.putExtras((n));
        startActivity(intento);
    }
}