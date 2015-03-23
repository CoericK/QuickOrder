package com.boushtech.quickorder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.andexert.library.RippleView;
import com.boushtech.quickorder.R;
import com.boushtech.quickorder.libraries.SessionManagement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class LoginActivity extends ActionBarActivity {

    private EditText usuario;
    private EditText password;
    private TextView statusMsg;
    private RippleView btnLogin;
    private RelativeLayout rlLoading;
    private RelativeLayout rlLoginActivity;

    private SessionManagement oSM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        rlLoginActivity = (RelativeLayout) findViewById(R.id.rlLoginActivity);
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        statusMsg = (TextView) findViewById(R.id.statusMsg);
        btnLogin = (RippleView) findViewById(R.id.btnLogin);
        rlLoading = (RelativeLayout) findViewById(R.id.loading);

        oSM = new SessionManagement(this);

        if(oSM.isLoggedIn()){

            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();

        }





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading();
                String u = usuario.getText().toString();
                String p = password.getText().toString();



                Ion.with(getApplicationContext())
                        .load("http://54.186.141.203:8080/sysempresa/api/auth/get_token")
                        .setBodyParameter("usuario", u)
                        .setBodyParameter("password", p)
                        .asJsonObject()
                        .setCallback(new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                // do stuff with the result or error
                                String message = "";
                                JsonObject auth_token = result.getAsJsonObject("auth_token");
                                if(auth_token != null){
                                    //Log.d("LoginActivity Token", "Token: "+ auth_token.toString());
                                    message = "Datos correctos, iniciando sesión...";

                                    String token = cleanString(auth_token.get("token").toString());
                                    JsonObject persona = result.getAsJsonObject("persona");

                                    String nombres = cleanString(persona.get("nombres").toString());
                                    String apaterno = cleanString(persona.get("apPaterno").toString());
                                    String amaterno = cleanString(persona.get("apMaterno").toString());
                                    String nombre_completo = cleanString(persona.get("nombreCompleto").toString());
                                    String sexo = cleanString(persona.get("sexo").toString());
                                    String imagen = cleanString(persona.get("imagenUri").toString());

                                    oSM.createLoginSession(token, nombres, apaterno, amaterno, nombre_completo, sexo, imagen);


                                    //Toast.makeText(getApplicationContext(), "Token: "+token,Toast.LENGTH_SHORT).show();
                                    statusMsg.setText(message);


                                    Ion.with(getApplicationContext())
                                            .load("http://54.186.141.203:8080/sysempresa/api/auth/get_token2?empresa_id=1&auth_token=" + token)
                                            .asJsonObject()
                                            .setCallback(new FutureCallback<JsonObject>() {
                                                @Override
                                                public void onCompleted(Exception e, JsonObject result2) {
                                                    // do stuff with the result or error

                                                    JsonObject auth_token2 = result2.getAsJsonObject("auth_token");

                                                    if(auth_token2 != null){

                                                        String token2 = cleanString(auth_token2.get("token").toString());


                                                        oSM.setAuth_Token2(token2);

                                                        //Toast.makeText(getApplicationContext(), "Token 2: "+token2,Toast.LENGTH_SHORT).show();

                                                        loadedOk();
                                                    }


                                                }
                                            });










                                }else {
                                    message = cleanString(result.get("message").toString());


                                    statusMsg.setText(message);
                                    loaded();
                                }








                            }
                        });


            }
        });


    }



    public void loading(){
        btnLogin.setVisibility(View.GONE);
        rlLoading.setVisibility(View.VISIBLE);

    }

    public void loadedOk(){

        statusMsg.setVisibility(View.VISIBLE);
        rlLoading.setVisibility(View.GONE);


        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(i);


        finish();

    }

    public void loaded(){
        statusMsg.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.VISIBLE);
        rlLoading.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public String cleanString(String s){

        s = s.substring(1, s.length());
        s =  s.substring(0, s.length() - 1);

        return s;


    }
}
