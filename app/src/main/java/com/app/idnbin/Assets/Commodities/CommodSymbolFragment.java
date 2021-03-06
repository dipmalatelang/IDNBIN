package com.app.idnbin.Assets.Commodities;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.Assets.Base.Commodities;
import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class CommodSymbolFragment extends Fragment implements SearchView.OnQueryTextListener {
    private CommoditiesAdapter commoditiesAdapter;
    TextView Tv_Volume, Tv_Today, Tv_Spread, Tv_Leverage;
    private Commodities symbols = new Commodities();
    private boolean status = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commod_symbol, container, false);
        RecyclerView Rv_Commod = v.findViewById(R.id.Rv_Commod);
        SearchView searchView = v.findViewById(R.id.SearchView);
        int options = searchView.getImeOptions();
        searchView.setImeOptions(options| EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        Tv_Volume = v.findViewById(R.id.Tv_Volume);
        Tv_Today = v.findViewById(R.id.Tv_Today);
        Tv_Spread = v.findViewById(R.id.Tv_Spread);
        Tv_Leverage = v.findViewById(R.id.Tv_Leverage);

        LinearLayout first = (LinearLayout) searchView.getChildAt(0);
        LinearLayout second = (LinearLayout) first.getChildAt(2);
        LinearLayout third = (LinearLayout) second.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete) third.getChildAt(0);
        autoComplete.setTextColor(Color.WHITE);
        autoComplete.setTextSize(12);

        ArrayList<SymbolsData> symlist = symbols.getCommoditieslist();

        Rv_Commod.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Rv_Commod.setItemAnimator(new DefaultItemAnimator());
        commoditiesAdapter = new CommoditiesAdapter(getContext(), symlist);
        Rv_Commod.setAdapter(commoditiesAdapter);

        searchView.setOnQueryTextListener(this);

        Tv_Volume.setOnClickListener(v1 -> {
            if (status) {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_up, 0, 0, 0);
                commoditiesAdapter.sort("asc");
                status = false;
            } else {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_drop_down, 0, 0, 0);
                commoditiesAdapter.sort("des");
                status = true;
            }
        });
        return v;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        commoditiesAdapter.filter(newText);
        return false;
    }
}
