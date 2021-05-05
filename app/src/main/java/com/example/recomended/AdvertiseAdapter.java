package com.example.recomended;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdvertiseAdapter extends RecyclerView.Adapter<AdvertiseAdapter.MyViewHolder> {

    private List<Advertise> advertiseList;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,description,date;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.createdDate);
        }
    }

    AdvertiseAdapter(List<Advertise> advertiseList) {
        this.advertiseList = advertiseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advertisement, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Advertise advertise = advertiseList.get(position);
        holder.title.setText(advertise.getTitle());
        holder.description.setText(advertise.getDescription());
        holder.date.setText(advertise.getDate());
    }

    @Override
    public int getItemCount() {
        return advertiseList.size();
    }
}
