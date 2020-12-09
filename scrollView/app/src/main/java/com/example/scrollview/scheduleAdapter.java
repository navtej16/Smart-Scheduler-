package com.example.scrollview;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrollview.modal.Schedule;

import java.util.ArrayList;
import java.util.List;

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.ViewHolder> {


    private List<Schedule> list;
    private Context mContext;

    public scheduleAdapter(Context context, List<Schedule> schedules) {
       this.list= (ArrayList<Schedule>) schedules;
       this.mContext=context;
    }

    @NonNull
    @Override
    public scheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_row, parent, false);
        return new scheduleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull scheduleAdapter.ViewHolder holder, int position) {
        Schedule schedule = list.get(position);
        holder.subjectCode.setText(schedule.getCode());
        holder.subjectName.setText(schedule.getName());
        holder.profName.setText(schedule.getProfName());
        holder.venue.setText(schedule.getVenue());
        holder.time.setText(schedule.getTime());

        final Button button= holder.arrowBtn;
        final ConstraintLayout constraintLayout= holder.expandableLayout;
        final CardView cardView = holder.cardView;
        final TextView subjectCode = holder.subjectCode;

        holder.arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constraintLayout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    subjectCode.setVisibility(View.VISIBLE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    button.animate().setDuration(500).rotationXBy(180);
                } else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    subjectCode.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.GONE);
                    button.animate().setDuration(500).rotationXBy(-180);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView subjectName;
        TextView venue;
        TextView time;
        ConstraintLayout expandableLayout;
        CardView cardView;
        Button arrowBtn;
        TextView subjectCode;
        TextView profName;

        public ViewHolder(View itemView) {
            super(itemView);
            subjectName =itemView.findViewById(R.id.schedule_subject);
            venue = itemView.findViewById(R.id.schedule_venue);
            time = itemView.findViewById(R.id.schedule_time);
            expandableLayout = itemView.findViewById(R.id.expandable_schedule);
            cardView = itemView.findViewById(R.id.schedule_card);
            arrowBtn = itemView.findViewById(R.id.arrow_btn);
            subjectCode = itemView.findViewById(R.id.subjectCode);
            profName = itemView.findViewById(R.id.prof_name);
        }
    }
}
