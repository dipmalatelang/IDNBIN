package com.app.idnbin.Profile.History.Trading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.ItemViewHolder>{

    private Context context;
    private ArrayList<SymbolsData> symbolsData;

    AssetAdapter(Context context, ArrayList<SymbolsData> symbolsData) {
        this.context = context;
        this.symbolsData = symbolsData;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asset,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.CbAsset.setText(symbolsData.get(position).getSymbolName());
        holder.CbAsset.setChecked(symbolsData.get(position).isIschecked());

        holder.CbAsset.setOnCheckedChangeListener((buttonView, isChecked) -> {symbolsData.get(position).setIschecked(isChecked);
        });
        if (symbolsData.get(position).isTitleVisible()){
            holder.TvHeader.setText(symbolsData.get(position).getCategory());
            holder.TvHeader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return symbolsData.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView TvHeader;
        CheckBox CbAsset;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            TvHeader = itemView.findViewById(R.id.TvHeader);
            CbAsset = itemView.findViewById(R.id.CbAsset);
        }
    }

}
