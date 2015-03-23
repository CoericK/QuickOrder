package com.boushtech.quickorder.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.boushtech.quickorder.Config;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.events.NuevaOrden;
import com.boushtech.quickorder.fragments.CrearOrdenFragment;
import com.boushtech.quickorder.fragments.DetalleOrdenFragment;
import com.boushtech.quickorder.fragments.OrdenesFragment;
import com.boushtech.quickorder.fragments.ProductosFragment;
import com.boushtech.quickorder.libraries.CarritoManager;
import com.boushtech.quickorder.libraries.SessionManagement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import de.greenrobot.event.EventBus;

public class CrearOrdenActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private FrameLayout flLoader;


    private SessionManagement oSM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_orden);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        oSM = new SessionManagement(this);



        flLoader = (FrameLayout) findViewById(R.id.flLoader);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.flLoader, getCrearOrdenFragment(oSM.getKeyAuthToken2()));

        transaction.commit();






    }



    public Fragment getCrearOrdenFragment(String at2){

        CrearOrdenFragment f = new CrearOrdenFragment();
        Bundle args = new Bundle();
        args.putString("auth_token2", at2);
        f.setArguments(args);
        return f;



    }

    public String cleanString(String s){

        s = s.substring(1, s.length());
        s =  s.substring(0, s.length() - 1);

        return s;


    }



    private SweetAlertDialog pDialog;
    public void sendOrden(JsonObject json){

        Ion.with(getApplicationContext())
                .load(Config.HOST_URI+"/restaurante/ordenes?auth_token="+oSM.getKeyAuthToken2())
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error
                        pDialog.cancel();
                        String status = result.get("status").toString();
                        if(status != null){
                            if(status.equalsIgnoreCase("400")){
                                String message = cleanString(result.get("message").toString());
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }


                        }else{
                            Toast.makeText(getApplicationContext(), "La orden ha sido registrada con éxito.", Toast.LENGTH_LONG).show();

                            CarritoManager.clear();
                            EventBus.getDefault().post(new NuevaOrden());
                            finish();

                        }

                    }
                });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_orden, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_products) {
            Intent i = new Intent(this, ProductosActivity.class);
            i.putExtra("auth_token2", oSM.getKeyAuthToken2());
            startActivity(i);

        }

        if (id == R.id.send_order) {
            if(CarritoManager.getProductos().size() > 0){

                pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                pDialog.setTitleText("Cargando...");
                pDialog.setCancelable(false);
                pDialog.show();
                sendOrden(CarritoManager.getRequest());

            }else{
                Toast.makeText(getApplicationContext(), "No puede enviar una orden, si no tiene productos.",Toast.LENGTH_LONG).show();
            }

        }

        return super.onOptionsItemSelected(item);
    }
}
