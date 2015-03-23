package com.boushtech.quickorder.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.activities.DetalleOrdenActivity;
import com.boushtech.quickorder.customlists.OrdenesCustomList;
import com.boushtech.quickorder.entities.Orden;
import com.boushtech.quickorder.events.NuevaOrden;
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

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(NuevaOrden no){

        getOrdenes(auth_token2);



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



        getOrdenes(auth_token2);

        lvOrdenes.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getOrdenes(auth_token2);
            }
        });



        lvOrdenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo = ((TextView) view.findViewById(R.id.codigo)).getText().toString();

                Intent i = new Intent(getActivity(), DetalleOrdenActivity.class);
                i.putExtra("codigo", codigo);
                i.putExtra("auth_token2", auth_token2);
                startActivity(i);
            }
        });


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

                            ordenes.clear();
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
