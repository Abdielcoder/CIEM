package pro.abdiel.ciem.Adapters;

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
import pro.abdiel.ciem.models.NotificationsModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<NotificationsModel> itemList1;
    private Context context;
    private Dialog dialog;

    public ItemAdapter(List<NotificationsModel> itemList, Context context) {
        this.itemList1=itemList;
        this.context=context;

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_notfications,parent,false);
       // View view2 = inflater.inflate(R.layout.week_day_dialog, container, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, final int position) {

        holder.itemImage.setImageResource(itemList1.get(position).getImage());
        holder.itemSubject.setText(itemList1.get(position).getAsunto());
        //holder.itemMessage.setText(itemList1.get(position).getMensaje());

      /*  holder.tvTitle.setText(itemList1.get(position).getMensaje());
        holder.tvMessage.setText(itemList1.get(position).getMensaje());*/
      //  Logger.d(position);


        holder.btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(v.getRootView().getContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setMessage("Asunto: "+itemList1.get(position).getMensaje())
                        .setTitle("Mensaje: "+itemList1.get(position).getAsunto())
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemSubject,itemMessage;
        LinearLayout linearLayout;
        Button btnVer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.itemImg);
            itemSubject=itemView.findViewById(R.id.itemSubject);
            //itemMessage=itemView.findViewById(R.id.itemMenssage);
            linearLayout=itemView.findViewById(R.id.vertical_linnear);
            btnVer=itemView.findViewById(R.id.buttonMessages);



        }
    }

}