package com.example.scrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mTasks = new ArrayList<>();
    private ArrayList<String> mTaskImages = new ArrayList<>();
    private ArrayList<String> mTaskDate = new ArrayList<>();
    private Context mContext;

    public TaskRecyclerViewAdapter(ArrayList<String> mTasks, ArrayList<String> mTaskImages, ArrayList<String> mTaskDate, Context mContext) {
        this.mTasks = mTasks;
        this.mTaskImages = mTaskImages;
        this.mTaskDate = mTaskDate;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new TaskRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerViewAdapter.ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mTaskImages.get(position))
                .into(holder.image);
        holder.title.setText(mTasks.get(position));
        holder.date.setText(mTaskDate.get(position));
        holder.linearLayout.setBackground(mContext.getDrawable(R.color.grey));

    }

    @Override
    public int getItemCount() {
        return mTaskImages.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title;
        TextView date;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.taskImage);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            linearLayout = itemView.findViewById(R.id.taskItemLayout);
        }
    }
}
