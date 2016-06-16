package com.example.pedro.chatsd;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by souzadomingues on 02/03/16.
 */
public class usuarioListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String[]> eventos;
    private TextView EventoNome;
    private TextView DataInicio;
    private TextView DataFim;
    private TextView DataAbr;
    private TextView local;

    public usuarioListAdapter(Activity context, ArrayList<String[]> eventos, ArrayList<String> eventosID) {
        super(context, R.layout.listaevento_layout, eventosID);

        this.context = context;
        this.eventos = eventos;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listaevento_layout, null, true);

        EventoNome = (TextView) rowView.findViewById(R.id.EventoNome);
        DataInicio = (TextView)  rowView.findViewById(R.id.DataInicio);
        DataFim = (TextView) rowView.findViewById(R.id.DataFim);
        DataAbr = (TextView) rowView.findViewById(R.id.dataabr);
        local = (TextView)  rowView.findViewById(R.id.local);

        String eventoAtual[] = eventos.get(position);


        EventoNome.setText(eventoAtual[1]);
        DataInicio.setText(eventoAtual[2]);
        DataFim.setText(eventoAtual[3]);
        DataAbr.setText(eventoAtual[4]);
        local.setText(eventoAtual[5]);

        return rowView;
    };


}
