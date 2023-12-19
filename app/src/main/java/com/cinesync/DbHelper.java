package com.cinesync;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context, String database_name) {
    super(context, database_name, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table preguntas (\n" + " id integer primary key,\n" +
                "tag text,\n" + "nucleo text,\n" + "respuestasTexto texto,\n" +
                "respuestasImagenes texto,\n" + "respuestaCorrecta integer\t\n" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Método para insertar datos desde un archivo CSV
    public void insertDataFromCSV(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String csvFile = "C:\\Users\\Nombre\\Desktop\\bd1-preguntas.csv"; // Ruta completa al archivo CSV

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";"); // Suponiendo que los valores en tu CSV están separados por comas

                // Inserta los datos en tu tabla
                // Cambia "columna1", "columna2", ... por los nombres reales de tus columnas
                String query = "INSERT INTO " + tableName + " (tag, nucleo, respuestasTexto, respuestasImagenes, respuestaCorrecta) VALUES (?, ?, ?, ?, ?)";
                // Crear un array para almacenar los valores de las columnas
                Object[] bindArgs = new Object[values.length];

                // Copiar los valores del CSV al array de argumentos
                for (int i = 0; i < values.length; i++) {
                    bindArgs[i] = values[i].trim(); // Puedes necesitar ajustar esto según tu caso
                }

                // Ejecutar la sentencia de inserción
                db.execSQL(query, bindArgs);
            }
            br.close();
        } catch (IOException e) {
            Log.e(TAG, "Error al leer el archivo CSV", e);
        }

        db.close();
    }

}
