package com.cinesync;

import android.provider.BaseColumns;

public class CineContract {

    private CineContract() {}

    public static abstract class CineEntry implements BaseColumns {
        public static final String TABLE_NAME = "preguntas";
        public static final String COLUMN_NAME_TAG = "tag";
        public static final String COLUMN_NAME_NUCLEO = "nucleo";
        public static final String COLUMN_NAME_TEXT = "respuestasTexto";
        public static final String COLUMN_NAME_IMG = "respuestasImagenes";
        public static final String COLUMN_NAME_RIGHT = "respuestaCorrecta";
    }
}
