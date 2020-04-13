package com.app.idnbin.Assets.Digital;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.app.idnbin.sqlite.SQLiteDatabaseHandler;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class DigitalAdapter extends RecyclerView.Adapter<DigitalAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SymbolsData> symbolsDataList;
    private ArrayList<SymbolsData> symbolsDataArrayList = new ArrayList<>();
    private SQLiteDatabaseHandler db;
    private static final String TAG = "DigitalAdapter";

    DigitalAdapter(Context context, ArrayList<SymbolsData> symbolsDataList) {
        this.context = context;
        this.symbolsDataList = symbolsDataList;
        this.symbolsDataArrayList.addAll(symbolsDataList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_digital_symbol, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        db = new SQLiteDatabaseHandler(context);
        holder.Tv_AssetName.setText(symbolsDataList.get(position).getSymbolName());
        holder.LL_Header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addSymbol(symbolsDataList.get(position),"DIGITAL");
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);


            }
        });
        holder.Iv_Info.setOnClickListener(v -> {
            String bundle = "info_click";
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("info", bundle);
            context.startActivity(intent);
        });
        holder.Iv_Alert.setOnClickListener(v -> {
            String bundle1 = "alert_click";
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("alert", bundle1);
            context.startActivity(intent);
        });
        holder.Iv_Fav.setOnClickListener(v -> {
            swapeItem(position, 0);
        });
        Glide.with(context)
                .load(symbolsDataList.get(position).getImgaeUrl())
                .into(holder.Cv_Symbol);
    }

    @Override
    public int getItemCount() {
        return symbolsDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Tv_AssetName, Tv_Hour, Tv_Profit;
        ImageView Iv_Info, Iv_Alert, Iv_Fav;
        CircleImageView Cv_Symbol;
        LinearLayout LL_Header;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Tv_AssetName = itemView.findViewById(R.id.Tv_AssetName);
            Tv_Hour = itemView.findViewById(R.id.Tv_Hour);
            Tv_Profit = itemView.findViewById(R.id.Tv_Profit);
            Iv_Info = itemView.findViewById(R.id.Iv_Info);
            Iv_Alert = itemView.findViewById(R.id.Iv_Alert);
            Iv_Fav = itemView.findViewById(R.id.Iv_Fav);
            Cv_Symbol = itemView.findViewById(R.id.Cv_Symbol);
            LL_Header = itemView.findViewById(R.id.LL_Header);
        }
    }

    private void swapeItem(int fromPosition, int toPosition){
        Collections.swap(symbolsDataList, fromPosition, 0);
        notifyItemMoved(fromPosition, toPosition);
    }

    void filter(String s) {
        s = s.toLowerCase();
        symbolsDataList.clear();
        if (s.length() == 0) {
            symbolsDataList.addAll(symbolsDataArrayList);
        } else {
            for (SymbolsData symbolsData : symbolsDataArrayList) {
                if (symbolsData.getSymbolName().toLowerCase().contains(s)) {
                    symbolsDataList.add(symbolsData);
                }
            }
        }
        notifyDataSetChanged();
    }

    void sort(String type) {
        if (type.equalsIgnoreCase("asc")) {
            Collections.sort(symbolsDataList, (o1, o2) -> {
                String symbol1 = o1.getSymbolName();
                String symbol2 = o2.getSymbolName();
                return symbol1.compareTo(symbol2);
            });
        } else {
            Collections.sort(symbolsDataList, (o1, o2) -> {
                String symbol1 = o1.getSymbolName();
                String symbol2 = o2.getSymbolName();
                return symbol2.compareTo(symbol1);
            });
        }
        notifyDataSetChanged();
    }
}
