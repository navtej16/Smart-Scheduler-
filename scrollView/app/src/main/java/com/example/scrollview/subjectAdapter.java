package com.example.scrollview;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrollview.modal.Subject;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

class subjectAdapter extends RecyclerView.Adapter<subjectAdapter.ViewHolder> {
    private Context context;
    private List<Subject> subjects;

    public subjectAdapter(Context context, List<Subject> subjects) {
     this.context = context;
     this.subjects =  subjects;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subject_item,parent,false);


        return new subjectAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull subjectAdapter.ViewHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.name.setText(subject.getName());
        holder.code.setText(subject.getCode());

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

        public ConstraintLayout expandableView;
        public TextView name;
        public TextView code;

        public Button arrowBtn;

        public CardView cardView;
        public Object progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sub_name);
            code = itemView.findViewById(R.id.desc);
            arrowBtn = itemView.findViewById(R.id.arrowBtn);
            expandableView = itemView.findViewById(R.id.expandableView);
            cardView = itemView.findViewById(R.id.cardView);



        }
    }

}
