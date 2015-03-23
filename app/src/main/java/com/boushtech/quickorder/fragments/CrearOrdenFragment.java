package com.boushtech.quickorder.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.customlists.CrearOrdenCustomList;
import com.boushtech.quickorder.customlists.DetalleOrdenCustomList;
import com.boushtech.quickorder.customlists.OrdenesCustomList;
import com.boushtech.quickorder.customlists.ProductosCustomList;
import com.boushtech.quickorder.entities.CrearOrden;
import com.boushtech.quickorder.entities.DetalleOrden;
import com.boushtech.quickorder.entities.Orden;
import com.boushtech.quickorder.entities.Producto;
import com.boushtech.quickorder.events.AgregarProducto;
import com.boushtech.quickorder.libraries.CarritoManager;
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

public class CrearOrdenFragment extends Fragment {

    private SuperListview lvProductos;

    private String auth_token2;

    protected CrearOrdenCustomList adapter;

    private ProgressBarCircularIndeterminate pbLoading;

    private TextView totalOrden;




    private List<AgregarProducto> productos = new ArrayList<AgregarProducto>();

    private Gson gson;




    public CrearOrdenFragment() {
    }


    public void onEvent(final AgregarProducto ap){



        Toast.makeText(getActivity(), ap.getProducto()+", fue agregado con éxito.", Toast.LENGTH_SHORT).show();


        CarritoManager.addProducto(ap);

        productos = CarritoManager.getProductos();




        adapter.notifyDataSetChanged();


        totalOrden.setText(CarritoManager.getTotalCarrito().toString());


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {



            }
        });




    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gson = new Gson();

        Bundle args = getArguments();
        if (args != null) {
            auth_token2 = args.getString("auth_token2");
        }




        productos = CarritoManager.getProductos();



        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_crear_orden, container, false);


        totalOrden = (TextView) rootView.findViewById(R.id.totalOrden);
        lvProductos = (SuperListview) rootView.findViewById(R.id.lvProductos);
        pbLoading = (ProgressBarCircularIndeterminate) rootView.findViewById(R.id.pbLoading);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        adapter = new CrearOrdenCustomList(getActivity(), productos);


        lvProductos.setAdapter(adapter);




        adapter.notifyDataSetChanged();

        totalOrden.setText(CarritoManager.getTotalCarrito().toString());
        loaded();
        //getProductos(auth_token2);


    }

    public void loading(){
        pbLoading.setVisibility(View.VISIBLE);
        lvProductos.setVisibility(View.GONE);
    }

    public void loaded(){
        pbLoading.setVisibility(View.GONE);
        lvProductos.setVisibility(View.VISIBLE);
    }

    /*
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


    }*/


}
