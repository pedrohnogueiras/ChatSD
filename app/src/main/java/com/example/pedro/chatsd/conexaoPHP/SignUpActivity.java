package com.example.pedro.chatsd.conexaoPHP;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by souzadomingues on 29/02/16.
 */
public class SignUpActivity extends AsyncTask<String, Void, String> {


    public interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;


    public SignUpActivity(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... params){
        try{
            String nome = (String)params[0];
            String sobrenome = (String)params[1];
            String email = (String)params[2];
            String numero = (String)params[3];

            String link = "http://10.0.2.2/chat/postUsuario.php";
            String dados = URLEncoder.encode("nome", "UTF-8") + "=" + URLEncoder.encode(nome, "UTF-8");
            dados += "&" + URLEncoder.encode("sobrenome", "UTF-8") + "=" + URLEncoder.encode(sobrenome, "UTF-8");
            dados += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email,"UTF-8");
            dados += "&" + URLEncoder.encode("numero" , "UTF-8") +  "=" + URLEncoder.encode(numero, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter((conn.getOutputStream()));

            wr.write(dados);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            Log.v("SB:", sb.toString());
            return sb.toString();



        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }


        return "2";
    }

    @Override
    protected void onPostExecute(String result){
        if(result.equals("1"))
            delegate.processFinish("1");
        else if(result.equals("2"))
            delegate.processFinish("2");
        else
            delegate.processFinish(result);
    }


}
