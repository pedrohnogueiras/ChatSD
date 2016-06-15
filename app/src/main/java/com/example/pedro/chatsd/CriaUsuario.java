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

/**
 * Created by pedro on 14/06/2016.
 */
public class CriaUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    EditText nome = (EditText)findViewById(R.id.nome);
    EditText sobrenome = (EditText)findViewById(R.id.sobrenome);
    EditText email = (EditText)findViewById(R.id.email);
    EditText numero = (EditText)findViewById(R.id.numero);
    Button cadastrar = (Button)findViewById(R.id.cadastrar);

    /*cadastrar.OnClickListener(new View.OnClickListener())

    {
        @Override
        public void onClick (View v){
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
                        String[] dadosUsuarios = output.split("/");
                        SharedPreferences dadosPessoais = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = dadosPessoais.edit();
                        editor.putString("Email", dadosUsuarios[0]);
                        editor.putString("Nome", dadosUsuarios[1]);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), getString(R.string.cadastrosuccessful), Toast.LENGTH_SHORT).show();

                        finish();
                    }
                }
            }).execute(nome.getText().toString(), email.getText().toString(), sobrenome.getText().toString(), numero.getText().toString());
        }
    }
    }

    );
    }*/
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
