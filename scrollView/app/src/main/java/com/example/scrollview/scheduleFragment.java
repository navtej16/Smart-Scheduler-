package com.example.scrollview;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;


import com.example.scrollview.modal.Schedule;
import com.example.scrollview.modal.Subject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class scheduleFragment extends Fragment {
    RecyclerView recyclerView;
    ConstraintLayout expandableView;
    Button arrowBtn;
    CardView cardView;
    TextView code;

    public scheduleFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_schedule, container, false);
        // Inflate the layout for this fragment
        expandableView = rootView.findViewById(R.id.expandable_schedule);
        arrowBtn = rootView.findViewById(R.id.arrow_btn);
        cardView = rootView.findViewById(R.id.schedule_card);
        recyclerView = rootView.findViewById(R.id.scheduleView);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        List<Schedule> schedules = new ArrayList<Schedule>();
        for(int i= 0 ; i<6;i++)
        {schedules.add(new Schedule());}


        scheduleAdapter adapter = new scheduleAdapter(getContext(),schedules);
        recyclerView.setAdapter(adapter);
        if(arrowBtn!=null) {

        }
        return rootView;
    }
}
