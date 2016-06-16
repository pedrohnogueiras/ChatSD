package com.example.pedro.chatsd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.pedro.chatsd.conexaoPHP.getEventosActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class listaUsuarios extends AppCompatActivity {

    private ListView usuariosDisponiveis;
    private TextView debug;
    private ArrayList<String> eventosID;
    private int eventosIndex = 0;
    private ArrayList<String[]> usuarios_detalhados;
    private ArrayList<String> months;
    private ArrayList<String> monthsAbr;




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_usuarios);
        fillMonthsContent();
        usuarios_detalhados = new ArrayList<>();
        usuariosDisponiveis = (ListView) findViewById(R.id.LVusuarios);
        debug = (TextView) findViewById(R.id.debug);
        getEventosActivity eventos = (getEventosActivity) new getEventosActivity(new getEventosActivity.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                debug.setVisibility(View.INVISIBLE);
                try {
                    usuarios_detalhados = parseJSON(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                preencherEventos(output);
                startAdapter();
            }
        }).execute();





    }

    private void startAdapter(){
        usuarioListAdapter adapter = new usuarioListAdapter(this, usuarios_detalhados, eventosID);
        usuariosDisponiveis.setAdapter(adapter);

        usuariosDisponiveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), mostrarUsuario.class);
                intent.putExtra("ID", eventosID.get(position));
                intent.putExtra("nome", usuarios_detalhados.get(position)[1]);
                intent.putExtra("sobrenome", usuarios_detalhados.get(position)[2]);
                intent.putExtra("email", usuarios_detalhados.get(position)[3]);
                intent.putExtra("numero", usuarios_detalhados.get(position)[5]);

                startActivity(intent);
            }
        });
    }



    private void preencherEventos(String entrada){
        //String[] eventos = entrada.split();




    //        String[] eventos = entrada.split("[***]");
    //        eventos_detalhados = new ArrayList<>();
    //        for(String evento: eventos){
    //            Log.v("Eventos2: ", evento);
    //            String[] evento_detalhado = evento.split("[*]");
    //            for(int i = 0; i < evento_detalhado.length; i++) {
    //                evento_detalhado[i] = evento_detalhado[i].replace("[", "");
    //                evento_detalhado[i] = evento_detalhado[i].replace("]", "");
    //            }
    //            eventos_detalhados.add(evento_detalhado);
    //        }
    //        eventosID = new ArrayList<>();
    //        for(String[] evento: eventos_detalhados){
    //            eventosID.add(evento[0]);
    //        }
    //        Log.v("eventos5:" , eventos_detalhados.get(0)[2]);
    //        Log.v("eventos7:", eventosID.get(0));

//              String[] e1 = new String[] {"12", "Simposio", humanizeDate("12-12-1912"), humanizeDate("13-12-1912"), abreviateDate("12-12-1912"), "UFV Campus Florestal"};
//              String[] e2 = new String[] {"13", "Simposio2",humanizeDate("12-08-1912"), humanizeDate("13-12-1912"),abreviateDate("12-08-1912"), "UFV Campus Viçosa"};
//              String[] e3 = new String[] {"14", "Simposio3", humanizeDate("08-12-1912"), humanizeDate("13-12-1912"),abreviateDate("08-12-1912"), "UFMG"};
//              String[] e4 = new String[] {"15", "Simposio4", humanizeDate("12-12-1912"), humanizeDate("13-12-1912"),abreviateDate("12-12-1912"), "Expominas"};
//              String[] e5 = new String[] {"16", "Simposio5", humanizeDate("1-12-1912"), humanizeDate("13-12-1912"),abreviateDate("1-12-1912"), "CEFET-BH"};
//        String[] e6 = new String[] {"17", "Simposio6", humanizeDate("09-09-1912"), humanizeDate("13-12-1912"),abreviateDate("09-09-1912"), "UFV Campus Florestal"};
//        String[] e7 = new String[] {"18", "Simposio7", humanizeDate("12-12-1912"), humanizeDate("13-12-1912"),abreviateDate("12-12-1912"), "UFV Rio Paranaíba"};
//        String[] e8 = new String[] {"19", "Simposio8", humanizeDate("12-12-1912"), humanizeDate("13-12-1912"),abreviateDate("12-12-1912"), "UFV Campus Florestal"};
//        String[] e9 = new String[] {"20", "Simposio9", humanizeDate("12-12-1912"), humanizeDate("13-12-1912"),abreviateDate("12-12-1912"), "UFMG"};
//        eventos_detalhados = new ArrayList<>();
//        eventos_detalhados.add(e1);
//        eventos_detalhados.add(e2);
//        eventos_detalhados.add(e3);
//        eventos_detalhados.add(e4);
//        eventos_detalhados.add(e5);
//        eventos_detalhados.add(e6);
//        eventos_detalhados.add(e7);
//        eventos_detalhados.add(e8);
//        eventos_detalhados.add(e9);
//        eventosID = new ArrayList<>();
//            eventosID.add("12");
//            eventosID.add("13");
//            eventosID.add("14");
//            eventosID.add("15");
//            eventosID.add("16");
//        eventosID.add("17");
//        eventosID.add("18");
//        eventosID.add("19");
//        eventosID.add("20");

    }


    private String humanizeDate (String date){
        String formatedDate = "";
        String[] pieces = date.split("-");


        if(Locale.getDefault().getDisplayLanguage().equals("English")){
            formatedDate += months.get(Integer.parseInt(pieces[1]) - 1) + " " + pieces[2];


        }
        else {
            formatedDate += pieces[2] + " de " + months.get(Integer.parseInt(pieces[1]) - 1);
        }


        return formatedDate;
    }

    private String abreviateDate(String date){
        String abrDate = "";
        String[] pieces  = date.split("-");
        abrDate = monthsAbr.get(Integer.parseInt(pieces[1])) + "\n" + pieces[2];

        return abrDate;
    }

    private void fillMonthsContent() {
        months = new ArrayList<>();
        months.add(0, getString(R.string.janeiro));
        months.add(1, getString(R.string.fevereiro));
        months.add(2, getString(R.string.marco));
        months.add(3, getString(R.string.abril));
        months.add(4, getString(R.string.maio));
        months.add(5, getString(R.string.junho));
        months.add(6, getString(R.string.julho));
        months.add(7, getString(R.string.agosto));
        months.add(8, getString(R.string.setembro));
        months.add(9, getString(R.string.outubro));
        months.add(10, getString(R.string.novembro));
        months.add(11, getString(R.string.dezembro));

        monthsAbr = new ArrayList<>();
        monthsAbr.add(0, null);
        monthsAbr.add(1, getString(R.string.jan));
        monthsAbr.add(2, getString(R.string.feb));
        monthsAbr.add(3, getString(R.string.mar));
        monthsAbr.add(4, getString(R.string.abr));
        monthsAbr.add(5, getString(R.string.mai));
        monthsAbr.add(6, getString(R.string.jun));
        monthsAbr.add(7, getString(R.string.jul));
        monthsAbr.add(8, getString(R.string.ago));
        monthsAbr.add(9, getString(R.string.set));
        monthsAbr.add(10, getString(R.string.out));
        monthsAbr.add(11,getString(R.string.nov));
        monthsAbr.add(12,getString(R.string.dez));
    }


    private ArrayList<String[]> parseJSON(String JSONinput) throws JSONException {
        JSONObject jsonObject = new JSONObject(JSONinput);
        JSONArray eventos = jsonObject.getJSONArray("evento");
        ArrayList<String[]> evento_completo = new ArrayList<>();
        for(int i = 0 ; i < eventos.length(); i++){
            JSONObject jsonObject1 = eventos.getJSONObject(i);
            String[] valores = new String[]{jsonObject1.getString("ID"), jsonObject1.getString("nome"), humanizeDate(jsonObject1.getString("Data_inicio")), humanizeDate(jsonObject1.getString("Data_fim")),abreviateDate(jsonObject1.getString("Data_inicio")), jsonObject1.getString("local_principal")};
            evento_completo.add(valores);
        }
        return evento_completo;

        }

}
