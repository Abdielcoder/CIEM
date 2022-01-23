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
import pro.abdiel.ciem.models.ReadCredentialsModel;

public class CredentialAdapter extends RecyclerView.Adapter<CredentialAdapter.ViewHolder> {

    List<ReadCredentialsModel> itemList1;
    private Context context;


    public CredentialAdapter(List<ReadCredentialsModel> itemList, Context context) {
        this.itemList1=itemList;
        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.credential_row_user,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.imgCrendential.setImageResource(itemList1.get(position).getProfileImage());
        holder.NameCredential.setText(itemList1.get(position).getName());
        holder.HourCredential.setText(itemList1.get(position).getHour());
        holder.dateCredential.setText(itemList1.get(position).getDate());
        holder.IdCredential.setText(itemList1.get(position).getId());
        holder.CompanyCredential.setText(itemList1.get(position).getCompany());


    }

    @Override
    public int getItemCount() {
        return itemList1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCrendential;
        TextView  NameCredential;
        TextView  HourCredential;
        TextView  dateCredential;
        TextView  IdCredential;
        TextView  CompanyCredential;
        LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCrendential=itemView.findViewById(R.id.imgCrendential);
            NameCredential=itemView.findViewById(R.id.tvNameCredential);
            HourCredential=itemView.findViewById(R.id.tvHourCredential);
            dateCredential=itemView.findViewById(R.id.tvDateCredential);
            IdCredential=itemView.findViewById(R.id.tvIdCredential);
            CompanyCredential=itemView.findViewById(R.id.tvCompanyCredential);
            linearLayout=itemView.findViewById(R.id.vertical_linnear_Credential);

        }
    }

}
