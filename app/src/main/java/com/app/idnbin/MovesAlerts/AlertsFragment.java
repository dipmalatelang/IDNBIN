package com.app.idnbin.MovesAlerts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AlertsFragment extends DialogFragment {

    private ArrayList<Alerts> alerts = new ArrayList<>();
    private AlertAdapter alertAdapter;
    private HomeActivity homeActivity;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerview_alerts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getActivity() instanceof HomeActivity) {
            homeActivity = (HomeActivity) getActivity();
        }
        View view = inflater.inflate(R.layout.fragment_alerts, container, false);

        ImageView IVAddAlert = view.findViewById(R.id.IVAddAlert);
        recyclerview_alerts = view.findViewById(R.id.recyclerview_alerts);
        recyclerview_alerts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null){
            databaseReference = FirebaseDatabase.getInstance().getReference("Android/Alerts").child(fuser.getUid());
        }

        displayAlerts();
        IVAddAlert.setOnClickListener(v -> homeActivity.setAlertVisibility());

        return view;
    }

    private void displayAlerts(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                alerts.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Alerts ud = dataSnapshot1.getValue(Alerts.class);
                    alerts.add(ud);
                    alertAdapter = new AlertAdapter(getActivity(), alerts);
                    recyclerview_alerts.setAdapter(alertAdapter);
                    alertAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}



