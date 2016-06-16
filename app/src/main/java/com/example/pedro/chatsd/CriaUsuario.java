package com.example.pedro.chatsd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pedro.chatsd.conexaoPHP.SignUpActivity;

/**
 * Created by pedro on 14/06/2016.
 */
public class CriaUsuario extends AppCompatActivity {

    private static final String PREFS_NAME = "DadosPessoais";

    EditText nome;
    EditText sobrenome;
    EditText email;
    EditText numero;
    Button cadastrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    nome = (EditText)findViewById(R.id.nome);
    sobrenome = (EditText)findViewById(R.id.sobrenome);
    email = (EditText)findViewById(R.id.email);
    numero = (EditText)findViewById(R.id.numero);
    cadastrar = (Button)findViewById(R.id.cadastrar);

    cadastrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean fieldsOK = checkFields();
            if (fieldsOK) {
                SignUpActivity signUp = (SignUpActivity) new SignUpActivity(new SignUpActivity.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        Log.v("Dados:", "Dados2 = " + output);
                        if (output.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Email em uso", Toast.LENGTH_SHORT).show();
                        } else if (output.equals("2")) {
                            Toast.makeText(getApplicationContext(), getString(R.string.noconnection), Toast.LENGTH_SHORT).show();
                        } else {
                            /*String[] dadosUsuarios = output.split("/");
                            SharedPreferences dadosPessoais = getSharedPreferences(PREFS_NAME, 0);
                            SharedPreferences.Editor editor = dadosPessoais.edit();
                            editor.putString("nome", dadosUsuarios[0]);
                            editor.putString("sobrenome", dadosUsuarios[1]);
//                            editor.putString("email", dadosUsuarios[2]);
  //                          editor.putString("numero", dadosUsuarios[3]);
                            editor.commit();*/
                            Toast.makeText(getApplicationContext(), getString(R.string.cadastrosuccessful), Toast.LENGTH_SHORT).show();

                            //finish();
                        }
                    }
                }).execute(nome.getText().toString(), sobrenome.getText().toString(), email.getText().toString(), numero.getText().toString());
            }
        }
    });

    }
    private boolean checkFields(){
        String number = numero.getText().toString();
        String lastnameCheck = sobrenome.getText().toString();
        String emailCheck = email.getText().toString();
        String nameCheck = nome.getText().toString();
        if((nameCheck.equals("")) || (emailCheck.equals("")) || (lastnameCheck.equals("")) || (number.equals(""))){
            Toast.makeText(getApplicationContext(), getString(R.string.completetodos), Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailCheck.contains("@") == false){
            Toast.makeText(getApplicationContext(), getString(R.string.emailinvalido), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}
