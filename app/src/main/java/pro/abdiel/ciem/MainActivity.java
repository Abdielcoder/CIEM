package pro.abdiel.ciem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    String UPLOAD_URL;
    String codigoBarras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    50); }

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


}