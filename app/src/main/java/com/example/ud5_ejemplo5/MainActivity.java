package com.example.ud5_ejemplo5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = findViewById(R.id.textoDialogo);
        Button botonAbrir = findViewById(R.id.botonAbrir);

        // Al pulsar el botón "Abrir" creamos nuestro Dialogo (que es un Fragment) y lo mostramos.
        botonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialogo dialogo = new Dialogo();
                dialogo.show(getSupportFragmentManager(), "DialogoFragment");
            }
        });
    }
}
