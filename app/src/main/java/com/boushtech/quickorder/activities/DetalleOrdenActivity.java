package com.boushtech.quickorder.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.fragments.DetalleOrdenFragment;
import com.boushtech.quickorder.fragments.OrdenesFragment;
import com.boushtech.quickorder.libraries.SessionManagement;

public class DetalleOrdenActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private FrameLayout flLoader;

    private String codigo;
    private String auth_token2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_orden);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            codigo = extras.getString("codigo");
            auth_token2 = extras.getString("auth_token2");

        }


        flLoader = (FrameLayout) findViewById(R.id.flLoader);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.flLoader, getDetalleOrdenFragment(codigo, auth_token2));

        transaction.commit();








    }


    public Fragment getDetalleOrdenFragment(String cod, String at2){

        DetalleOrdenFragment f = new DetalleOrdenFragment();
        Bundle args = new Bundle();
        args.putString("codigo", codigo);
        args.putString("auth_token2", at2);
        f.setArguments(args);
        return f;



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalle_orden, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
