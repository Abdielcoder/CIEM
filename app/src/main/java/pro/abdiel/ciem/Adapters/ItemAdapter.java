package pro.abdiel.ciem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import pro.abdiel.ciem.R;
import pro.abdiel.ciem.models.NotificationsModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<NotificationsModel> itemList1;
    private Context context;

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
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, final int position) {

        holder.itemImage.setImageResource(itemList1.get(position).getImage());
        holder.itemSubject.setText(itemList1.get(position).getName());
        holder.itemMessage.setText(itemList1.get(position).getMessage());


    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView itemSubject;
        TextView itemMessage;
        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage=itemView.findViewById(R.id.itemImg);
            itemSubject=itemView.findViewById(R.id.itemSubject);
            itemMessage=itemView.findViewById(R.id.itemMenssage);
            linearLayout=itemView.findViewById(R.id.vertical_linnear);

        }
    }
}