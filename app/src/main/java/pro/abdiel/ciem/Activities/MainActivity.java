package pro.abdiel.ciem.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.sagarkoli.chetanbottomnavigation.chetanBottomNavigation;
import pro.abdiel.ciem.Fragments.InfoFragment;
import pro.abdiel.ciem.Fragments.NotificationFragment;
import pro.abdiel.ciem.Fragments.ProfileFragment;
import pro.abdiel.ciem.Fragments.ReportFragment;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.controller.InsertCardMysql;
import pro.abdiel.ciem.controller.ReadDriver;
import pro.abdiel.ciem.controller.ReadMessagges;
import pro.abdiel.ciem.models.CardClientModel;
import pro.abdiel.ciem.utils.LoadDialogs;

public class MainActivity extends AppCompatActivity {
    //Scanner
    private CodeScanner mCodeScanner;
    private String credentialCode;
    //DATA FROM LOGIN
    private String username;
    private String profileUser;
    private String delegacionId;
    private String clienteID;
    private String usersId;
    private String municipio;
    //Bottom Navigation
    chetanBottomNavigation bottomNavigation;
    private static final int scanner = 1;
    private static final int day = 2;
    private static final int noti = 3;
    private static final int profile = 4;
    private static final int info = 5;
    //INICIALIZAR
    private ReadDriver readDriver;
    private InsertCardMysql insertDriverMysql;
    private LoadDialogs loadingDialogs;
    private ReadMessagges readMessagges;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.addLogAdapter(new AndroidLogAdapter());
        //PRGRESS DIALOG
        loadingDialogs = new LoadDialogs(MainActivity.this);

        readDriver = new ReadDriver();
        insertDriverMysql =  new InsertCardMysql();
        //Scanner Logic
        CodeScannerView scannerView = findViewById(R.id.scanner_view);

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        credentialCode = result.getText();
                        insertDriverMysql.uploadCardClient(MainActivity.this,profileUser,clienteID,delegacionId,usersId,username,credentialCode,municipio);
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

            }
        });

        bottomNavigation.setOnClickMenuListener(new chetanBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(chetanBottomNavigation.Model item) {

                //LOGIG NAVIGATION FRAGMENT
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

        //FIRTS OPTION SELECTED
        bottomNavigation.show(scanner,true);


        //Get Data from LoginActivity
        Bundle extras = getIntent().getExtras();
        String userName;

        if (extras != null) {
            username = extras.getString("username");
            profileUser = extras.getString("profile");
            delegacionId = extras.getString("delegacionId");
            usersId = extras.getString("usersId");
            clienteID = extras.getString("clienteID");
            municipio = extras.getString("municipio");
            //READ DRIVER IF EXISTS DONT ADD TO FB

            readDriver.obtainDriver(profileUser,usersId,delegacionId,username);

        }

        readMessagges = new ReadMessagges();
        readMessagges.consulta(MainActivity.this);
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


    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

}