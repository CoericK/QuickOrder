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
import com.boushtech.quickorder.entities.Producto;

import java.util.List;

public class ProductosCustomList extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Producto> productos;



    public ProductosCustomList(Activity activity, List<Producto> productos) {
        this.activity = activity;
        this.productos = productos;

    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int location) {
        return productos.get(location);
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
            convertView = inflater.inflate(R.layout.list_single_producto, null);




        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView codigo = (TextView) convertView.findViewById(R.id.codigo);
        TextView producto = (TextView) convertView.findViewById(R.id.producto);
        TextView familia = (TextView) convertView.findViewById(R.id.familia);
        TextView precio_venta = (TextView) convertView.findViewById(R.id.precio_venta);


        Producto p = productos.get(position);

        id.setText(p.getId());
        codigo.setText(p.getCodigo());
        producto.setText(p.getNombre());
        familia.setText(p.getFamiliaProducto());
        precio_venta.setText(p.getPrecioVentaUnidad());












        return convertView;
    }



}