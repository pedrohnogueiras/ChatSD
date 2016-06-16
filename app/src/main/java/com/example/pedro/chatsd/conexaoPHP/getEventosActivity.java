package com.example.pedro.chatsd.conexaoPHP;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by souzadomingues on 02/03/16.
 */
public class getEventosActivity extends AsyncTask<String, Void, String> {

    public interface AsyncResponse{
        void processFinish(String output);
    }

    public AsyncResponse delegate = null;

    public getEventosActivity(AsyncResponse delegate){
        this.delegate = delegate;
    }

    protected void onPreExecute(){

    }


    @Override
    protected String doInBackground(String... params) {
        try {
            String link = "http://10.0.2.2/chat/getUsuarios.php";

            //String dados = URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("scape20132015appKey#", "UTF-8");
            Log.v("TEste","teste1");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            //conn.setDoOutput(true);

            Log.v("TEste", "teste2");
    /*
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(dados);
            wr.flush();
*/
            Log.v("TEste", "teste3");

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


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "2";
    }

    @Override
    protected void onPostExecute(String result){
        if(result.equals("2"))
            delegate.processFinish("2");
        else
            delegate.processFinish(result);
    }


}
