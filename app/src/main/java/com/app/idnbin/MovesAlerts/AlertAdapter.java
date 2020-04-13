package com.app.idnbin.MovesAlerts;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;


public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.Viewholder> {
    private Context context;
    private ArrayList<Alerts> alertList;

    AlertAdapter(Context context, ArrayList<Alerts> alertList) {
        this.context = context;
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alerts_list, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        holder.Tv_Currency.setText(alertList.get(position).getAlertPrice());
        holder.Tv_Time.setText(alertList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        TextView Tv_Currency, Tv_Time;

        Viewholder(@NonNull View itemView) {
            super(itemView);
            Tv_Currency = itemView.findViewById(R.id.Tv_Currency);
            Tv_Time = itemView.findViewById(R.id.Tv_Time);
        }
    }
}

