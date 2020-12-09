package com.example.scrollview;

import android.content.Context;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrollview.modal.Subject;
import com.example.scrollview.model.Attendence;

import java.util.List;

import me.tankery.lib.circularseekbar.CircularSeekBar;

class subjectAdapter1 extends RecyclerView.Adapter<subjectAdapter1.ViewHolder> {
    private Context context;
    private List<Attendence> subjects;
    final double[] a = {0,0,0,0,0,0};
    double[] b = {0,0,0,0,0,0};


    public subjectAdapter1 (Context context, List<Attendence> subjects) {
        this.context = context;
        this.subjects =  subjects;

    }

    @NonNull
    @Override
    public subjectAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item1,parent,false);

        return new subjectAdapter1.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final subjectAdapter1.ViewHolder holder, final int position) {
        Attendence subject = subjects.get(position);
        holder.name.setText(subject.getName());
        holder.code.setText(subject.getCode());

      holder.progressBar.setMax(100);


        holder.right.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                a[position]++;
                b[position]++;
                Log.i("msg", String.valueOf(a[position]));
                Log.i("position", String.valueOf(position));
               double c =(a[position] / b[position]) * 100;

               Log.i("msg", String.valueOf(c));
                holder.progressBar.setProgress((int) c,true);
                holder.percentage.setText(String.valueOf((int)c)+"%");
                holder.percent.setText(a[position]+"/"+b[position]);
            }
        });

        holder.cross.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                b[position]++;
                double c =(a[position] / b[position]) * 100;
                holder.percent.setText(a[position]+"/"+b[position]);
                Log.i("msg", String.valueOf(c));
                holder.progressBar.setProgress((int) c,true);
                holder.percentage.setText(String.valueOf((int)c)+"%");
            }
        });


        final ConstraintLayout layout = holder.expandableView;
        final Button button = holder.arrowBtn;
        final CardView view = holder.cardView;
        holder.arrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (layout.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(view, new AutoTransition());
                    layout.setVisibility(View.VISIBLE);
                    button.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(view, new AutoTransition());
                    layout.setVisibility(View.GONE);
                    button.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView code;
        private Button arrowBtn;
        private ConstraintLayout expandableView;
        private CardView cardView;
        private ProgressBar progressBar;
        public TextView attendance;
        private ImageView right;
        private ImageView cross;
        private TextView percentage;
        private TextView percent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sub_name);
            code = itemView.findViewById(R.id.desc);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            expandableView = itemView.findViewById(R.id.expandableView);
            cardView = itemView.findViewById(R.id.cardView);
            percentage = itemView.findViewById(R.id.textView6);
            attendance = itemView.findViewById(R.id.textView5);
            progressBar = itemView.findViewById(R.id.progress);
            right = itemView.findViewById(R.id.imageView);
            cross = itemView.findViewById(R.id.imageView3);
            percent = itemView.findViewById(R.id.textView7);
        }
    }
}
