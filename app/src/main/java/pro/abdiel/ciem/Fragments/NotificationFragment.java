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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView=view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initData();

        recyclerView.setAdapter(new ItemAdapter(initData(),getContext()));



        return view;
    }

    private List<ModelNotifications> initData() {

        itemList=new ArrayList<>();
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));
        itemList.add(new ModelNotifications(R.drawable.mensaje,"video 1"));

        return itemList;
    }
}