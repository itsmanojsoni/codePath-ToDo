package com.codepath.simpletodo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.simpletodo.TaskListFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {


    private final OnListFragmentInteractionListener mListener;
    private List<Task> taskList = new ArrayList<>();
    private final String TAG = "MyItemRecyclerViewAdapter";

    public MyItemRecyclerViewAdapter(OnListFragmentInteractionListener listener) {

        mListener = listener;
    }


    public void updateData(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Task task = taskList.get(position);
        String summary = taskList.get(position).summary;

        if (summary != null && !summary.isEmpty()) {
            holder.titleView.setText(summary);
        }

        String priority = taskList.get(position).priority;

        if (priority != null && !priority.isEmpty()) {
            holder.priority.setText(priority);
            TaskDetailEditFragment.PriorityLevel priorityLevel =  TaskDetailEditFragment.PriorityLevel.valueOf(priority);

            switch (priorityLevel) {
                case HIGH:
                    holder.priority.setTextColor(0xFFFF0000);
                    break;
                case MEDIUM:
                    holder.priority.setTextColor(0xFFFFA500);
                    break;
                case LOW:
                    holder.priority.setTextColor(0xFF00BFFF);
                    break;
                default:
                    Log.d(TAG,"Color not set");
                    break;
            }
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        Log.d(TAG, "Get Item Count and Size is :"+taskList.size());
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView titleView;
        public TextView descriptionView;
        public TextView priority;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            titleView = (TextView) view.findViewById(R.id.task);
            priority = (TextView) view.findViewById(R.id.priority);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + descriptionView.getText() + "'";
        }
    }
}
