package pro.abdiel.ciem.Adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;

import java.util.List;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.controller.DeleteMessages;
import pro.abdiel.ciem.models.NotificationsModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private  List<NotificationsModel> itemList1;
    private Context context;
    private Dialog dialog;
    private DeleteMessages deleteMessages = new DeleteMessages();
    public ItemAdapter(List<NotificationsModel> itemList, Context context) {
        this.itemList1=itemList;
        this.context=context;

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_notfications,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemImage.setImageResource(itemList1.get(position).getImage());
        holder.itemSubject.setText(itemList1.get(position).getAsunto());

        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getRootView().getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setMessage("Asunto: "+itemList1.get(position).getMensaje())
                        .setTitle("Mensaje: "+itemList1.get(position).getAsunto())
                        .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Logger.d(itemList1.get(position).getUuid());
                                deleteMessages.deleteMessage(itemList1.get(position).getUuid());

                            }
                        })
                        .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMessages.deleteMessage(itemList1.get(position).getUuid());
            }
        });

    }

    @Override
    public int getItemCount() {
        //COUNT BADGED
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemSubject,itemMessage;
        LinearLayout linearLayout;
        Button btnVer,btnDelete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.itemImg);
            itemSubject=itemView.findViewById(R.id.itemSubject);
            linearLayout=itemView.findViewById(R.id.vertical_linnear);
            btnVer=itemView.findViewById(R.id.buttonMessages);
            btnDelete=itemView.findViewById(R.id.buttonDeleteMesseges);

        }
    }



}