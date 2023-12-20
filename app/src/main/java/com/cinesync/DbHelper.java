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
    public static final int DATABASE_VERSION = 4;

    public static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + CineContract.CineEntry.TABLE_NAME + " (" +
                CineContract.CineEntry._ID + " INTEGER PRIMARY KEY," +
                CineContract.CineEntry.COLUMN_NAME_TAG + " TEXT, " +
                CineContract.CineEntry.COLUMN_NAME_NUCLEO + " TEXT, " +
                CineContract.CineEntry.COLUMN_NAME_TEXT + " TEXT, " +
                CineContract.CineEntry.COLUMN_NAME_IMG + " TEXT, " +
                CineContract.CineEntry.COLUMN_NAME_RIGHT + " INTEGER" +
                " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CineContract.CineEntry.TABLE_NAME;

    public DbHelper(Context context, String database_name) {
        super(context, database_name, null, DATABASE_VERSION);
    }
    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table preguntas (\n" + " id integer primary key,\n" +
                "tag text,\n" + "nucleo text,\n" + "respuestasTexto texto,\n" +
                "respuestasImagenes texto,\n" + "respuestaCorrecta integer\t\n" + ")");
    }
    */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
