package pro.abdiel.ciem.controller;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.logger.Logger;

import pro.abdiel.ciem.models.CardClientModel;

public class ReadDriver {
    private  InsertDriver insertaDriver = new InsertDriver();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String documentFB= "null";
    private String usernamefromFB;


    public ReadDriver() {
    }

    public void obtainDriver(String profileUser,String usersId, String delegacionId, String username) {
        Logger.d("I AM HERE...");

        db.collection("Drivers").whereEqualTo("username",username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public synchronized void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                documentFB = "notNull";
                                CardClientModel card = document.toObject(CardClientModel.class);
                                usernamefromFB = card.getUsername();
                                int tiempo = card.getTimeStamp();
                                Logger.d(tiempo);


                            }
                            if(documentFB.equals("null")){
                                insertaDriver.addDriver(profileUser,usersId,delegacionId,username);
                            }
                        } else {
                            Logger.d(task.getException());
                        }
                        notify();
                    }

                });

    }



}
