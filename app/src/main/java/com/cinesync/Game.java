package com.cinesync;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Supongamos que tienes un ImageView con id "imageView" en tu layout
        ImageView imageView = findViewById(R.id.imageView);

        // URL de la imagen en tu base de datos
        String imageUrl = "https://www.nationalgeographic.com.es/medio/2022/12/12/rana-1_66a9a5c8_221212161515_1280x720.jpg";

        // Cargar la imagen con Picasso
        Picasso.get()
                .load(imageUrl)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // La imagen se cargó con éxito
                    }

                    @Override
                    public void onError(Exception e) {
                        // Error al cargar la imagen
                        e.printStackTrace();
                    }
                });

        Picasso.get().setLoggingEnabled(true);


    }
}