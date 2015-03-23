package com.boushtech.quickorder.customlists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.entities.DetalleOrden;
import com.boushtech.quickorder.entities.Orden;

import java.util.List;

public class DetalleOrdenCustomList extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<DetalleOrden> detalles;



    public DetalleOrdenCustomList(Activity activity, List<DetalleOrden> detalles) {
        this.activity = activity;
        this.detalles = detalles;

    }

    @Override
    public int getCount() {
        return detalles.size();
    }

    @Override
    public Object getItem(int location) {
        return detalles.get(location);
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
            convertView = inflater.inflate(R.layout.list_single_detalle_orden, null);




        TextView codigo = (TextView) convertView.findViewById(R.id.codigo);

        TextView producto = (TextView) convertView.findViewById(R.id.producto);
        TextView precio_unitario = (TextView) convertView.findViewById(R.id.precio_unitario);
        TextView cantidad = (TextView) convertView.findViewById(R.id.cantidad);
        TextView sub_total= (TextView) convertView.findViewById(R.id.sub_total);
        /*
        A: pendiente
        B: finalizado
        C: anulado
                        */

        /*A: En Mesa, B: Para llevar, C: delivery

         */


        DetalleOrden o = detalles.get(position);

        codigo.setText(o.getCodigoProducto());
        producto.setText(o.getProducto());
        precio_unitario.setText(o.getPrecioUnitario());
        cantidad.setText(o.getCantidad());
        sub_total.setText(o.getTotal());












        return convertView;
    }



}