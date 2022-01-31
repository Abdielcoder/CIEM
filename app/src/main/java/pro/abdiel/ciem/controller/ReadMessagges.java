package pro.abdiel.ciem.controller;

import android.app.PendingIntent;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import pro.abdiel.ciem.Activities.MainActivity;
import pro.abdiel.ciem.Adapters.ItemAdapter;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.models.NotificationsModel;

public class ReadMessagges {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //NOTIFICATION.
    private static final String SKULL_CHANNER = "skull";
    Context context;
    public ReadMessagges() {
    }

    //EVENT LISTENER TO DATA IN REAL TIME
    public void consulta(MainActivity mainActivity){

        db.collection("Messages")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshots,
                                        @Nullable FirebaseFirestoreException e) {

                        if (e != null) {

                            return;
                        }
                        for (DocumentSnapshot doc : snapshots) {

                            if (doc.get("asunto") != null) {

                                showNewnoti(mainActivity);
                            }
                        }


                    }
                });
    }

    private void showNewnoti(MainActivity mainActivity) {
        /* setpendingIntent(MainActivity.class);*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mainActivity,SKULL_CHANNER)
                .setSmallIcon(R.drawable.idg_alfa)
                .setContentTitle("Tellego un mensaje")
                .setContentText("Te acaba de llegar un nuevo mensaje")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(mainActivity);
        managerCompat.notify(1,builder.build());
    }
}
