package com.example.pedro.chatsd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by pedro on 14/06/2016.
 */
public class CriaUsuario extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private EditText nome;
    private EditText sobrenome;
    private EditText email;
    private EditText numero;
    private Button cadastrar;

}
