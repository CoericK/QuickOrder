package com.boushtech.quickorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.customlists.ProductosCustomList;
import com.boushtech.quickorder.entities.Producto;
import com.boushtech.quickorder.events.AgregarProducto;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.quentindommerc.superlistview.SuperListview;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ProductosFragment extends Fragment {

    private SuperListview lvProductos;

    private String auth_token2;

    protected ProductosCustomList adapter;

    private ProgressBarCircularIndeterminate pbLoading;


    private List<Producto> productos = new ArrayList<Producto>();

    private Gson gson;


    public ProductosFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();

        Bundle args = getArguments();
        if (args != null) {
            auth_token2 = args.getString("auth_token2");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_productos, container, false);


        lvProductos = (SuperListview) rootView.findViewById(R.id.lvProductos);
        pbLoading = (ProgressBarCircularIndeterminate) rootView.findViewById(R.id.pbLoading);


        return rootView;
    }


    private TextView codigoView;
    private TextView familiaView;
    private TextView productoView;
    private TextView precioView;

    private EditText cantidadInput;

    private View positiveAction;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adapter = new ProductosCustomList(getActivity(), productos);


        lvProductos.setAdapter(adapter);


        getProductos(auth_token2);


        lvProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long idLong) {

                final TextView id = (TextView) v.findViewById(R.id.id);
                final TextView codigo = (TextView) v.findViewById(R.id.codigo);
                final TextView familia = (TextView) v.findViewById(R.id.familia);
                final TextView producto = (TextView) v.findViewById(R.id.producto);
                final TextView precio_venta = (TextView) v.findViewById(R.id.precio_venta);


                MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                        .title("AGREGAR A PRODUCTO")
                        .customView(R.layout.dialog_customview, true)
                        .positiveText("AGREGAR")
                        .negativeText("CANCELAR")
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                String cant = cantidadInput.getText().toString();
                                Double sub_total = Double.parseDouble(cant) * Double.parseDouble(precio_venta.getText().toString());


                                EventBus.getDefault().post(new AgregarProducto(id.getText().toString(), codigo.getText().toString(), familia.getText().toString(), producto.getText().toString(), cantidadInput.getText().toString(), precio_venta.getText().toString(), sub_total.toString()));

                            }

                            @Override
                            public void onNegative(MaterialDialog dialog) {
                            }
                        }).build();

                positiveAction = dialog.getActionButton(DialogAction.POSITIVE);
                codigoView = (TextView) dialog.getCustomView().findViewById(R.id.codigo);
                familiaView = (TextView) dialog.getCustomView().findViewById(R.id.familia);
                productoView = (TextView) dialog.getCustomView().findViewById(R.id.producto);
                precioView = (TextView) dialog.getCustomView().findViewById(R.id.precio);
                cantidadInput = (EditText) dialog.getCustomView().findViewById(R.id.cantidad);

                codigoView.setText(codigo.getText());
                familiaView.setText(familia.getText());
                productoView.setText(producto.getText());
                precioView.setText(precio_venta.getText());


                cantidadInput.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        positiveAction.setEnabled(s.toString().trim().length() > 0);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });


                dialog.show();
                positiveAction.setEnabled(false); // disabled by default

            }
        });


    }

    public void loading() {
        pbLoading.setVisibility(View.VISIBLE);
        lvProductos.setVisibility(View.GONE);
    }

    public void loaded() {
        pbLoading.setVisibility(View.GONE);
        lvProductos.setVisibility(View.VISIBLE);
    }

    public void getProductos(String at2) {

        loading();

        Ion.with(getActivity())
                .load(Config.HOST_URI + "/restaurante/productos?limit=20&auth_token=" + at2)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        JsonArray dataArray = result.getAsJsonArray("data");


                        if (dataArray != null) {
                            productos.clear();


                            for (int i = 0; i < dataArray.size(); i++) {
                                JsonElement productoTMP = dataArray.get(i);


                                Producto p = gson.fromJson(productoTMP.toString(), Producto.class);

                                productos.add(p);


                            }


                            adapter.notifyDataSetChanged();


                        }
                        loaded();


                    }
                });


    }


}
