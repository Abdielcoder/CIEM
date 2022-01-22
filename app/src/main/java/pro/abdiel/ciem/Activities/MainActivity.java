package pro.abdiel.ciem.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.sagarkoli.chetanbottomnavigation.chetanBottomNavigation;

import java.util.Hashtable;
import java.util.Map;

import pro.abdiel.ciem.Fragments.InfoFragment;
import pro.abdiel.ciem.Fragments.NotificationFragment;
import pro.abdiel.ciem.Fragments.ProfileFragment;
import pro.abdiel.ciem.Fragments.ReportFragment;
import pro.abdiel.ciem.R;

public class MainActivity extends AppCompatActivity {
    //Scanner
    private CodeScanner mCodeScanner;
    private String UPLOAD_URL;
    private String codigoBarras;

    //Bottom Navigation
    chetanBottomNavigation bottomNavigation;
    private static final int scanner = 1;
    private static final int day = 2;
    private static final int noti = 3;
    private static final int profile = 4;
    private static final int info = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Scanner Logic
        CodeScannerView scannerView = findViewById(R.id.scanner_view);
        UPLOAD_URL = getString(R.string.app_upload);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        codigoBarras = result.getText();
                        uploadTrabajador();
                        Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    50);
        };

        //Adding custom icons
        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new chetanBottomNavigation.Model(scanner,R.drawable.qr_black_24));
        bottomNavigation.add(new chetanBottomNavigation.Model(profile,R.drawable.person_black_24));
        bottomNavigation.add(new chetanBottomNavigation.Model(day,R.drawable.report_black_24));
        bottomNavigation.add(new chetanBottomNavigation.Model(noti,R.drawable.message_black_24));
        bottomNavigation.add(new chetanBottomNavigation.Model(info,R.drawable.info_black_24));

        //Bottom Navigation Logic
        bottomNavigation.setCount(noti,"10");

        bottomNavigation.setOnShowListener(new chetanBottomNavigation.ShowListener(){

            @Override
            public void onShowItem(chetanBottomNavigation.Model item) {
                String name;
                String  fragment;
                Fragment selectedFragment = null;
                switch (item.getId()){
                    case scanner:
                        name = "SCANNER";

                    break;

                    case profile:
                        name = "PERFIL";
                        selectedFragment = new ProfileFragment();
                        break;

                    case day:
                        name = "DIA";

                        break;

                    case noti:
                        name = "NOTIFICACION";

                        break;

                    case info:
                        name = "INFORMACION";

                        break;

                }
            }
        });

        bottomNavigation.setOnReselectListener(new chetanBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(chetanBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this,"Opcion de nuevo", Toast.LENGTH_SHORT);
            }
        });

        bottomNavigation.setOnClickMenuListener(new chetanBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(chetanBottomNavigation.Model item) {
                Toast.makeText(MainActivity.this,"Opcion seleccionada", Toast.LENGTH_SHORT);
                String  fragment;
                switch (item.getId()){
                    case scanner:
                        fragment = "SCANNER";
                        Intent intent = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;

                    case profile:
                        fragment = "PERFIL";
                        replace(new ProfileFragment());
                        break;

                    case day:
                        fragment = "DIA";
                        replace(new ReportFragment());
                        break;

                    case noti:
                        fragment = "NOTIFICACION";
                        replace(new NotificationFragment());
                        break;

                    case info:
                        fragment = "INFORMACION";
                        replace(new InfoFragment());
                        break;

                }

            }
        });


        bottomNavigation.show(scanner,true);


        //Get Data from LoginActivity
        Bundle extras = getIntent().getExtras();
        String userName;

        if (extras != null) {
            String username = extras.getString("username");
            String password = extras.getString("password");
            String profile = extras.getString("profile");
            String nombre = extras.getString("nombre");
            String delegacionId = extras.getString("delegacionId");
            String activo = extras.getString("activo");

            Log.d("2VOSS","DATOS 1"+username);
            Log.d("2VOSS","DATOS 1"+password);
            Log.d("2VOSS","DATOS 1"+profile);
            Log.d("2VOSS","DATOS 1"+nombre);
            Log.d("2VOSS","DATOS 1"+delegacionId);
            Log.d("2VOSS","DATOS 1"+activo);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    //Subir imagen
    public void uploadTrabajador() {//realiza_todo

        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(Infracciones.this, "REGISTRANDO", Toast.LENGTH_SHORT).show();
                        Log.d("respuesta_ws",response.substring(0,14));
                        Log.d("otra_r_ws",response);

                        //progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("respuesta_ws_error",
                        ""+error);
                Toast.makeText(MainActivity.this, "FALLO REGISTRO", Toast.LENGTH_SHORT).show();
                //progressDialog.hide();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();

                params.put("CLIENTEdb", "");
                params.put("profile", "oper");
                params.put("clienteID", "4");
                params.put("UsersID", "9");
                params.put("delegacionID", "3");
                params.put("username", "CALFIA1301");
                params.put("MUNICIPIO", "ENSENADA");
                params.put("codigo",codigoBarras );


                return params;

            }


        };


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

}