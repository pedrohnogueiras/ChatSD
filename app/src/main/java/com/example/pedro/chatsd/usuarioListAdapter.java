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
    private TextView email;


    public usuarioListAdapter(Activity context, ArrayList<String[]> eventos, ArrayList<String> eventosID) {
        super(context, R.layout.listaevento_layout,eventosID);

        this.context = context;
        this.eventos = eventos;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listaevento_layout, null, true);


        email = (TextView) rowView.findViewById(R.id.Email);


        String eventoAtual[] = eventos.get(position);


        email.setText(eventoAtual[1]);

        return rowView;
    };


}
