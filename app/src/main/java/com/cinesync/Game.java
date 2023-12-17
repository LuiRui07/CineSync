package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_game);

            // Añadir registros de log para depurar
            Log.d("GameActivity", "Antes de inicializar DbHelper");

            admin = new DbHelper(this, "bd1");

            // Añadir registros de log para depurar
            Log.d("GameActivity", "Después de inicializar DbHelper");

            categoria = obtenerCategoria();

            categoriaText = findViewById(R.id.categoria);
            categoriaText.setText("Categoria " + c);
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

}