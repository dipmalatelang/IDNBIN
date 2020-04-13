package com.app.idnbin.HomeScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;
import com.app.idnbin.sqlite.SQLiteDatabaseHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.ItemViewHolder> {

    List<SymbolsData> urlList ;
    private Context context;
    private SQLiteDatabaseHandler db;

    private static final String TAG = "SymbolAdapter";
    public SymbolAdapter(Context context, List<SymbolsData> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_symbollist, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.Tv_DigitalSymbol.setText(urlList.get(position).getSymbolName());
        holder.tvsymbol.setText(urlList.get(position).getSymbolName());
        holder.TV_category.setText(urlList.get(position).getCategory());
        String imageUrl = urlList.get(position).getImgaeUrl();
        Log.d(TAG, "onBindViewHolder: "+urlList.get(position).getSymbolName());


        if (imageUrl.isEmpty()) {
            holder.CIV_symbol.setImageResource(R.drawable.bg);
        } else{
            Picasso.get().load(imageUrl).into(holder.CIV_symbol);
        }
        holder.IVDigitalClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(position);
                Log.i(TAG, "onClickdelete: "+position);

            }
        });
        holder.CVDigital.setOnClickListener(v -> {

            holder.Tv_DigitalSymbol.setVisibility(View.VISIBLE);
            holder.TV_category.setVisibility(View.VISIBLE);
            holder.IVDigitalClose.setVisibility(View.VISIBLE);
            holder.tvsymbol.setVisibility(View.GONE);

        });


    }

public void deleteRow(int position){ //removes the row
    db=new SQLiteDatabaseHandler(context);
    db.deleteData(urlList.get(position).getSymbolName(),urlList.get(position).getCategory());
    urlList.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, urlList.size());

}


    @Override
    public int getItemCount() {
        return urlList.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CircleImageView CIV_symbol;
        TextView Tv_DigitalSymbol,tvsymbol;
        TextView TV_category;
        LinearLayout LLDigitalClose,LLDigitalVisible;
        ImageView IVDigitalClose;
        CardView CVDigital;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            CVDigital = itemView.findViewById(R.id.CVDigital);
            CIV_symbol = itemView.findViewById(R.id.CIV_symbol);
            tvsymbol = itemView.findViewById(R.id.tvsymbol);
            IVDigitalClose = itemView.findViewById(R.id.IVDigitalClose);

            Tv_DigitalSymbol = itemView.findViewById(R.id.Tv_DigitalSymbol);
            TV_category = itemView.findViewById(R.id.TV_category);
            LLDigitalClose = itemView.findViewById(R.id.LLDigitalClose);
            LLDigitalVisible = itemView.findViewById(R.id.LLDigitalVisible);
        }

    }
}
