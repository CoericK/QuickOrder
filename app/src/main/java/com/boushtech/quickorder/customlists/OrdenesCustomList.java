package com.boushtech.quickorder.customlists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.entities.Orden;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdenesCustomList extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Orden> ordenes;



    public OrdenesCustomList(Activity activity, List<Orden> ordenes) {
        this.activity = activity;
        this.ordenes = ordenes;

    }

    @Override
    public int getCount() {
        return ordenes.size();
    }

    @Override
    public Object getItem(int location) {
        return ordenes.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_single_orden, null);




        TextView codigo = (TextView) convertView.findViewById(R.id.codigo);

        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);
        TextView tipo_consumo = (TextView) convertView.findViewById(R.id.tipo_consumo);
        TextView estado= (TextView) convertView.findViewById(R.id.estado);
        /*
        A: pendiente
        B: finalizado
        C: anulado
                        */

        /*A: En Mesa, B: Para llevar, C: delivery

         */


        Orden o = ordenes.get(position);

        codigo.setText(o.getCodigo());
        Long l = Long.parseLong(o.getFechaRegistro());


        Date d = new Date(l);

        String newstring = new SimpleDateFormat("dd/MM/yyyy HH:mm a").format(d);


        fecha.setText(newstring);


        if(o.getTipoConsumo().equalsIgnoreCase("A")){
            tipo_consumo.setText("EN MESA");
        }else if(o.getTipoConsumo().equalsIgnoreCase("B")){

            tipo_consumo.setText("PARA LLEVAR");
        }else if(o.getTipoConsumo().equalsIgnoreCase("C")){
            tipo_consumo.setText("DELIVERY");

        }else{
            tipo_consumo.setText("--");
        }

        if(o.getEstado().equalsIgnoreCase("A")){
            estado.setText("PENDIENTE");
        }else if(o.getEstado().equalsIgnoreCase("B")){

            estado.setText("FINALIZADO");
        }else if(o.getEstado().equalsIgnoreCase("C")){
            estado.setText("ANULADO");

        }else{
            estado.setText("--");
        }











        return convertView;
    }



}