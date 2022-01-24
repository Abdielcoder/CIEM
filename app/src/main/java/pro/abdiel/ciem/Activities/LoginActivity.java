package pro.abdiel.ciem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import android.widget.RelativeLayout;

import pro.abdiel.ciem.R;
import pro.abdiel.ciem.utils.Dialogo;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    private EditText edtUser;
    private EditText edtPassword;
    private Button   btnLogin;
    private SharedPreferences sp;
    private String username;
    private String password;
    private String profile;
    private String nombre;
    private String delegacionId;
    private String activo;
    private String usersId;
    private Dialogo dialogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        //Dialog.
        dialogo = new Dialogo();

        //View
        setContentView(R.layout.activity_login);

        String URL = getString(R.string.app_url_login);
        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 2000); //2000 is the timeo
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        edtUser.setText("CALFIA1301");
        edtPassword.setText("123");
        //Ger store User
        sp = getSharedPreferences("credenciales",Context.MODE_PRIVATE);
        Logger.d(sp);


        Bundle bundle  = getIntent().getExtras();

        //Validate inputs no empty
        if (bundle != null) {

            String userOut  = bundle.getString("userOut");
            String passOut  = bundle.getString("passOut");
           /* edtUser.setText(userOut);
            edtPassword.setText(passOut);*/
            edtUser.setText("CALFIA1301");
            edtPassword.setText("123");
        }else{
            /*sp.edit().putBoolean("logged",true).apply();
            SharedPreferences preferences = getSharedPreferences("credenciales",Context.MODE_PRIVATE);
            String estUsuario = preferences.getString("miUsuario","");
            String estPass = preferences.getString("miPassword","");
            edtUser.setText(estUsuario);
            edtPassword.setText(estPass);
            validarUsuario(URL);*/
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUser(URL);
            }
        });

    }

    //WS LOGIN USER
    private void LoginUser(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){

                    try {
                        JSONObject obj = new JSONObject(response);
                        String usersId = obj.getString("UsersID");
                        Logger.d(usersId);
                        username = obj.getString("username");
                        password = obj.getString("password");
                        profile = obj.getString("profile");
                        nombre = obj.getString("comment");
                        delegacionId = obj.getString("delegacionID");
                        activo = obj.getString("Activo");

                        Logger.d(username);
                        Logger.d(password);
                        Logger.d(profile);
                        Logger.d(nombre);
                        Logger.d(delegacionId);
                        Logger.d(activo);


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("usersId",usersId);
                        Logger.d(usersId);
                        intent.putExtra("username",username);
                        intent.putExtra("password",password);
                        intent.putExtra("profile",profile);
                        intent.putExtra("nombre",nombre);
                        intent.putExtra("delegacionId",delegacionId);
                        intent.putExtra("activo",activo);

                        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                        String usuario = username;
                        String pass = password;

                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putString("miUsuario",usuario);
                        editor.putString("miPassword",pass);

                        edtUser.setText("CALFIA1301");
                        edtPassword.setText("123");
                        editor.commit();

                        startActivity(intent);
                        finish();

                    } catch (JSONException e) {
                        Log.d("error_usuario",""+e);
                        e.printStackTrace();
                    }
                }else{

                   Toast.makeText(LoginActivity.this,"DATOS INCORRECTOS",Toast.LENGTH_LONG).show();
                   dialogo.Mensaje(LoginActivity.this, "Los datos son incorrectos, revisa tu Usuario o Contraseña para continuar." );
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario",edtUser.getText().toString());
                parametros.put("password",edtPassword.getText().toString());

                return parametros;
            }
        };
        RequestQueue requesrQueue   = Volley.newRequestQueue(this);
        requesrQueue.add(stringRequest);
    }
}