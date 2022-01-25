package pro.abdiel.ciem.controller;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.logger.Logger;

public class ReadDriver {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String documentFB= "null";
    private InsertDriver insertaDriver = new InsertDriver();
    public ReadDriver() {
    }

    public String obtainDriver(String profileUser,String usersId, String delegacionId, String username) {
        Logger.d("I AM HERE...");
        db.collection("driverUsers")
                .whereEqualTo("username", username) // <-- This line
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Logger.d("I AM HERE2...");
                        if (task.isSuccessful()) {
                            Logger.d("I AM HERE3...");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                documentFB = document.getId();
                                Logger.d(documentFB);
                            }
                            if (documentFB.equals("null")){
                                insertaDriver.addDriver(profileUser,usersId,delegacionId,username);
                            }
                        } else {

                            Logger.w(String.valueOf(task.getException()));
                        }
                    }
                });
        return documentFB;
    }
}
