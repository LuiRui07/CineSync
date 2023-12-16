package com.cinesync;

import android.provider.BaseColumns;

public class Contract {

    private Contract() {

    }

    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "PREGUNTA";
        public static final String COLUMN_NAME_KEY = "clave";
        public static final String COLUMN_NAME_VAL = "valor";
    }
}
