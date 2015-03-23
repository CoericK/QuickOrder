package com.boushtech.quickorder.customlists;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.entities.DetalleOrden;
import com.boushtech.quickorder.entities.Orden;
import com.boushtech.quickorder.events.AgregarProducto;

import java.util.List;

public class CrearOrdenCustomList extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<AgregarProducto> detalles;



    public CrearOrdenCustomList(Activity activity, List<AgregarProducto> detalles) {
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
            convertView = inflater.inflate(R.layout.list_single_crear_orden, null);




        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView codigo = (TextView) convertView.findViewById(R.id.codigo);
        TextView familia = (TextView) convertView.findViewById(R.id.familia);
        TextView producto = (TextView) convertView.findViewById(R.id.producto);
        TextView cantidad = (TextView) convertView.findViewById(R.id.cantidad);
        TextView precio_venta = (TextView) convertView.findViewById(R.id.precio_venta);
        TextView sub_total = (TextView) convertView.findViewById(R.id.sub_total);


        AgregarProducto ap = detalles.get(position);


        id.setText(ap.getId());
        codigo.setText(ap.getCodigo());
        familia.setText(ap.getFamilia());
        producto.setText(ap.getProducto());
        cantidad.setText(ap.getCantidad());
        precio_venta.setText(ap.getPrecio());
        sub_total.setText(ap.getSub_total());
















        return convertView;
    }



}