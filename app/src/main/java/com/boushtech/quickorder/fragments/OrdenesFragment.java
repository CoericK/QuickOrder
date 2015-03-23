package com.boushtech.quickorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.customlists.OrdenesCustomList;
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

public class OrdenesFragment extends Fragment {

    private SuperListview lvOrdenes;

    private String auth_token2;

    protected OrdenesCustomList adapter;

    private ProgressBarCircularIndeterminate pbLoading;


    List<Orden> ordenes = new ArrayList<Orden>();

    private Gson gson;


    public OrdenesFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_ordenes, container, false);


        lvOrdenes = (SuperListview) rootView.findViewById(R.id.lvOrdenes);
        pbLoading = (ProgressBarCircularIndeterminate) rootView.findViewById(R.id.pbLoading);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adapter = new OrdenesCustomList(getActivity(), ordenes);


        lvOrdenes.setAdapter(adapter);

        lvOrdenes.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrdenes(auth_token2);
            }
        });


        getOrdenes(auth_token2);


    }

    public void loading(){
        pbLoading.setVisibility(View.VISIBLE);
        lvOrdenes.setVisibility(View.GONE);
    }

    public void loaded(){
        pbLoading.setVisibility(View.GONE);
        lvOrdenes.setVisibility(View.VISIBLE);
    }

    public void getOrdenes(String at2) {

        loading();
        Ion.with(getActivity())
                .load(Config.HOST_URI + "/restaurante/ordenes?auth_token=" + at2)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        JsonArray data = result.getAsJsonArray("data");


                        if (data != null) {
                            for (int i = 0; i < data.size(); i++) {
                                JsonElement ordenTMP = data.get(i);


                                Orden o = gson.fromJson(ordenTMP.toString(), Orden.class);

                                ordenes.add(o);


                            }

                            adapter.notifyDataSetChanged();


                        }
                        loaded();



                    }
                });


    }


}
