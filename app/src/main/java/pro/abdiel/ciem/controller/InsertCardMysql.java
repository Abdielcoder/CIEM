package pro.abdiel.ciem.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Hashtable;
import java.util.Map;
import pro.abdiel.ciem.R;

public class InsertCardMysql {
    private String UPLOAD_URL;
    private InsertClientCard insertaDriver = new InsertClientCard();
    public InsertCardMysql() {
    }

    //UPLOAD TO MYSQL
    public void uploadCardClient(Context context,String profileUser,String clienteID, String usersId, String delegacionId,
                                 String username, String credentialCode,String municipio) {
        insertaDriver.addCard(profileUser,usersId,delegacionId,username,credentialCode,municipio);
        //realiza_todo
        UPLOAD_URL = context.getString(R.string.app_upload);
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
                Toast.makeText(context.getApplicationContext(), "FALLO REGISTRO", Toast.LENGTH_SHORT).show();
                //progressDialog.hide();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();

                //SEND DATA TO THE SERVER
                params.put("CLIENTEdb", "0");
                params.put("profile", profileUser);
                params.put("clienteID", clienteID);
                params.put("UsersID", usersId);
                params.put("delegacionID", delegacionId);
                params.put("username", username);
                params.put("MUNICIPIO", municipio);
                params.put("codigo",credentialCode );


                return params;

            }


        };

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(stringRequest);
    }


}
