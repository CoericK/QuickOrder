package com.boushtech.quickorder.activities;

import android.content.Intent;
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
import com.boushtech.quickorder.events.NuevaOrden;
import com.boushtech.quickorder.fragments.OrdenesFragment;
import com.boushtech.quickorder.libraries.SessionManagement;
import de.greenrobot.event.EventBus;

public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private SessionManagement oSM;

    private FrameLayout flLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        oSM = new SessionManagement(getApplicationContext());


        Intent i;

        if(!oSM.isLoggedIn()){
            i = new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        }

        flLoader = (FrameLayout) findViewById(R.id.flLoader);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.flLoader, getOrdenesFragment(oSM.getKeyAuthToken2()));
        transaction.commit();



    }



    public Fragment getOrdenesFragment(String at2){

        OrdenesFragment f = new OrdenesFragment();
        Bundle args = new Bundle();
        args.putString("auth_token2", at2);
        f.setArguments(args);
        return f;



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.crear_orden) {
            Intent i = new Intent(this, CrearOrdenActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.logout) {
            oSM.logoutUser();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
