package pro.abdiel.ciem.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.logger.Logger;

import pro.abdiel.ciem.Activities.MainActivity;

public class DeleteMessages {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DeleteMessages() {

    }
    //DELETE MESSAGES
    public void deleteMessage(String id){

        db = FirebaseFirestore.getInstance();
        CollectionReference itemsRef = db.collection("Messages");
        Query query = itemsRef.whereEqualTo("uuid", id);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        itemsRef.document(document.getId()).delete();
                    }
                } else {

                }
            }
        });

    }

}
