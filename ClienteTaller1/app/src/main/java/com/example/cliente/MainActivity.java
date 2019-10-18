package com.example.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements Observer {
    //variable boton que hace aubir de carril
    private Button arriba;
    //variable que hace bajar de carril
    private Button abajo;
    //variable de comunicacion
    private Comunicacion com;
    @Override
    //metodo que permite inicializar las variables y el hilo
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arriba = findViewById(R.id.btn_arriba);
        abajo = findViewById(R.id.btn_abajo);
        com = Comunicacion.getInstance();
        com.addObserver(this);
        Thread t = new Thread(com);
        t.start();

        arriba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("arriba");
            }
        });

        abajo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.enviar("abajo");
            }
        });

    }

    @Override
    public void update(Observable observable, Object o) {
        final String mensaje= (String) o;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT);
            }
        });
    }
}
