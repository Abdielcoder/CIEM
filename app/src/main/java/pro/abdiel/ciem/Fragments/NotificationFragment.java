package pro.abdiel.ciem.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import pro.abdiel.ciem.Adapters.ItemAdapter;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.models.CardClientModel;
import pro.abdiel.ciem.models.NotificationsModel;


public class NotificationFragment extends Fragment {
    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private List<NotificationsModel> itemList;
    private String asunto;
    private String mensaje;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView=view.findViewById(R.id.recyclerNotification);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //FIREBASE
        db = FirebaseFirestore.getInstance();
        //initData();
        consulta();

        return view;
    }

    public void consulta(){
        db.collection("Messages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        itemList=new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NotificationsModel noti = document.toObject(NotificationsModel.class);
                                asunto = noti.getAsunto();
                                mensaje = noti.getMensaje();
                                Logger.d(document.getId());
                                itemList.add(new NotificationsModel(R.drawable.messaging, asunto, mensaje));



                            }
                            recyclerView.setAdapter(new ItemAdapter(itemList,getContext()));

                        } else {

                        }
                    }
                });
    }







}