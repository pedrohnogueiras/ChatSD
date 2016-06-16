package com.example.pedro.chatsd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class mostrarUsuario extends AppCompatActivity {

    private TextView email;
    private TextView nome;
    private TextView sobrenome;
    private TextView numero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuario);
        email = (TextView) findViewById(R.id.nomeEvento);
        nome = (TextView) findViewById(R.id.dataI);
        sobrenome = (TextView) findViewById(R.id.dataT);
        numero = (TextView) findViewById(R.id.local);

        Intent intent = getIntent();
        email.setText(intent.getStringExtra("nome"));
        nome.setText(intent.getStringExtra("sobrenome"));
        sobrenome.setText(intent.getStringExtra("email"));
        numero.setText(intent.getStringExtra("numero"));
    }
}
