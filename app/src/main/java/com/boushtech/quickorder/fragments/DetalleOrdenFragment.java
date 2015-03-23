package com.boushtech.quickorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.customlists.DetalleOrdenCustomList;
import com.boushtech.quickorder.customlists.OrdenesCustomList;
import com.boushtech.quickorder.entities.DetalleOrden;
import com.boushtech.quickorder.entities.Orden;
import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.quentindommerc.superlistview.SuperListview;

import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenFragment extends Fragment {

    private SuperListview lvDetalle;

    private String auth_token2;
    private String codigo;

    protected DetalleOrdenCustomList adapter;

    private ProgressBarCircularIndeterminate pbLoading;

    private TextView codigoOrden;
    private TextView totalOrden;


    List<DetalleOrden> detalle = new ArrayList<DetalleOrden>();

    private Gson gson;

    private Double total = 0.0;


    public DetalleOrdenFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();

        Bundle args = getArguments();
        if (args != null) {
            codigo = args.getString("codigo");
            auth_token2 = args.getString("auth_token2");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detalle_orden, container, false);


        codigoOrden = (TextView) rootView.findViewById(R.id.codigoOrden);
        totalOrden = (TextView) rootView.findViewById(R.id.totalOrden);

        lvDetalle = (SuperListview) rootView.findViewById(R.id.lvDetalle);
        pbLoading = (ProgressBarCircularIndeterminate) rootView.findViewById(R.id.pbLoading);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adapter = new DetalleOrdenCustomList(getActivity(), detalle);


        lvDetalle.setAdapter(adapter);




        getDetalle(codigo, auth_token2);


    }

    public void loading(){
        pbLoading.setVisibility(View.VISIBLE);
        lvDetalle.setVisibility(View.GONE);
    }

    public void loaded(){
        pbLoading.setVisibility(View.GONE);
        lvDetalle.setVisibility(View.VISIBLE);
    }

    public void getDetalle(String cod, String at2) {

        loading();

        total = 0.0;
        codigoOrden.setText(cod);
        Ion.with(getActivity())
                .load(Config.HOST_URI + "/restaurante/ordenes/filtro?codigo="+cod+"&auth_token=" + at2)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        JsonArray detalleArray = result.getAsJsonArray("detalle");


                        if (detalleArray != null) {
                            detalle.clear();


                            for (int i = 0; i < detalleArray.size(); i++) {
                                JsonElement ordenTMP = detalleArray.get(i);


                                DetalleOrden o = gson.fromJson(ordenTMP.toString(), DetalleOrden.class);
                                total = total + Double.parseDouble(o.getTotal());
                                detalle.add(o);


                            }

                            totalOrden.setText(total.toString());

                            adapter.notifyDataSetChanged();


                        }
                        loaded();


                    }
                });


    }


}
