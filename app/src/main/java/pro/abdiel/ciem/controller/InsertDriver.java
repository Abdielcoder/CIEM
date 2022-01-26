package pro.abdiel.ciem.controller;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

public class InsertDriver {
    //FIRESTORE
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Long tsLong = System.currentTimeMillis()/1000;

    public void addDriver(String profile,String usersId, String delegacionId,String username){

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("timeStamp", tsLong);
        user.put("profile", profile);
        user.put("usersId", usersId);
        user.put("delegacionId",delegacionId);
        user.put("username", username);

        // Add a new document with  generated ID
        db.collection("Drivers")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Logger.d(documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.w(String.valueOf(e));
                    }
                });
    }

}
