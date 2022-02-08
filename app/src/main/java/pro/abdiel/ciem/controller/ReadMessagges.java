package pro.abdiel.ciem.controller;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import pro.abdiel.ciem.Activities.MainActivity;
import pro.abdiel.ciem.R;


public class ReadMessagges {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //NOTIFICATION.
    private static final String SKULL_CHANNER = "skull";

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
        Bitmap bitmap= BitmapFactory.decodeResource(mainActivity.getResources(),R.drawable.bitlabs_messaging);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mainActivity,SKULL_CHANNER)
                .setSmallIcon(R.drawable.bitlabs_p)
                .setContentTitle("Tellego un mensaje")
                .setContentText("Te acaba de llegar un nuevo mensaje")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bitmap))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(mainActivity);
        managerCompat.notify(1,builder.build());
    }
}
