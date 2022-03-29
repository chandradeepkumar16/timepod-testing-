package com.example.timepod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timepod.R;
import com.example.timepod.callback.ActionCallback;
import com.example.timepod.database.TaskItem;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {

    Context context;
    List<TaskItem> data;
    ActionCallback.TaskitemClicks callback;
    public TaskListAdapter(Context context, List<TaskItem> data , ActionCallback.TaskitemClicks callback) {
        this.context = context;
        this.data = data;
        this.callback=callback;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return new TaskListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull TaskListAdapter.TaskListViewHolder holder, int position) {
        holder.taskname.setText(data.get(position).title);
        holder.time.setText(data.get(position).time);


        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.clickItem(data.get(position), v);

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TaskListViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.task_name)
        TextView taskname;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.iv_more)
        ImageView more;
        public TaskListViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
