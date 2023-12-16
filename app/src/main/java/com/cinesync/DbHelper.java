package com.cinesync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
