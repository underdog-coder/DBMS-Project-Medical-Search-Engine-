package com.example.rohit.dbmsproject;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {
    private Context context;
    private List<MedicalStoreData> data_list;

    public CustomAdapter2(Context context, List<MedicalStoreData> data_list) {
        this.context = context;
        this.data_list = data_list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.medical_card,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.medicalStoreName.setText(data_list.get(position).getMedicalStoreName());
        holder.medicalStoreAddress.setText(data_list.get(position).getMedicalStoreAddress());
    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicalStoreName;
        public TextView medicalStoreAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            medicalStoreName=itemView.findViewById(R.id.medical_store);
            medicalStoreAddress=itemView.findViewById(R.id.medical_store_add);
        }
    }
}
