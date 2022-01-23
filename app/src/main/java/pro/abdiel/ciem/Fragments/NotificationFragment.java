package pro.abdiel.ciem.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import pro.abdiel.ciem.Adapters.ItemAdapter;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.models.ModelNotifications;


public class NotificationFragment extends Fragment {

    RecyclerView recyclerView;
    List<ModelNotifications> itemList;

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

        //initData();
        recyclerView.setAdapter(new ItemAdapter(initData(),getContext()));
        return view;
    }

    private List<ModelNotifications> initData() {

        itemList=new ArrayList<>();
        itemList.add(new ModelNotifications(R.drawable.messaging,"Fuera de Ruta.","Por favor regresa a tu ruta..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Cambio de Camion","Porfavor tomas unidad..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Chequeo Mecanico ","Acudir a ofinas para..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Cambio de horario","Se notifica que apartir..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Renovacion de Contrato","Favor de acudir con RH..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Exceo de Velocidad","Porfavor manejar de manera correcta..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Renovacion de Contrato","Favor de acudir con RH..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Chequeo Mecanico ","Acudir a ofinas para..."));
        itemList.add(new ModelNotifications(R.drawable.messaging,"Fuera de Ruta.","Por favor regresa a tu ruta..."));
       

        return itemList;
    }
}