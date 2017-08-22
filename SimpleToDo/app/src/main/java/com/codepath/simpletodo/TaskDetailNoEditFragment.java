package com.codepath.simpletodo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnTaskNoEditFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskDetailNoEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailNoEditFragment extends Fragment implements  OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Task workingTask;
    private TextView summary;
    private TextView description;
    private TextView taskStatus;
    private TextView priority;

    private Button addButton;

    private static final String TAG = "TaskDetailFragment";
    private OnTaskNoEditFragmentInteractionListener mListener;

    public TaskDetailNoEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetailNoEditFragment newInstance(String param1, String param2) {
        TaskDetailNoEditFragment fragment = new TaskDetailNoEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setData(Task task) {
        Log.d(TAG, "Task is being set in the detail Fragment");
        this.workingTask = task;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_task_detail_no_edit, container, false);
        setHasOptionsMenu(true);

//        Toolbar topToolBar = (Toolbar) view.findViewById(R.id.toolbar);
//        topToolBar.setVisibility(View.GONE);
//        Button addButton = (Button) view.findViewById(R.id.addItem);
//        addButton.setVisibility(View.GONE);

//        Button editButton = (Button) view.findViewById(R.id.editButton);
//        editButton.setVisibility(View.VISIBLE);
//
//        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);
//        deleteButton.setVisibility(view.VISIBLE);



        summary = (TextView) view.findViewById(R.id.summary);
        description = (TextView) view.findViewById(R.id.description);
        priority = (TextView) view.findViewById(R.id.prioritySpinner);
        taskStatus = (TextView) view.findViewById(R.id.taskStatusSpinner);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTaskNoEditFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskNoEditFragmentInteractionListener) {
            mListener = (OnTaskNoEditFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTaskNoEditFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(TAG, "Task is already set: task "+workingTask.summary);

        String summaryText = workingTask.getSummary();
        String descriptionText = workingTask.getDescription();
        String priorityText = workingTask.getPriority();
        String taskStatusText = workingTask.getTaskStatus();


        summary.setText(summaryText);
        if (workingTask.description != null) {
            description.setText(descriptionText);
        } else {

        }

        priority.setText(priorityText);
        taskStatus.setText(taskStatusText);


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {


        Log.d(TAG, "View is : "+view);
        Log.d(TAG, "Parent is :"+parent);
        Log.d(TAG, "Id is :"+id);
        String selectedPriority = (String)parent.getItemAtPosition(pos);

        Log.d(TAG, "Selected Priority is : "+selectedPriority);
        AppDatabase database = AppDatabase.getDatabase(getContext());

        Task newTask = database.taskModel().getTask(workingTask.id);
        newTask.setPriority(selectedPriority);
        database.taskModel().updateTask(newTask);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTaskNoEditFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTaskNoEditFragmentInteraction(Uri uri);
    }

    private void updateDatabaseWithNewSummary(String summary) {
        AppDatabase database = AppDatabase.getDatabase(getContext());
        Task newTask = database.taskModel().getTask(workingTask.id);
        newTask.setSummary(summary);
        database.taskModel().updateTask(newTask);
    }

    private void updateDatabaseWithNewDescription(String description) {
        AppDatabase database = AppDatabase.getDatabase(getContext());
        Task newTask = database.taskModel().getTask(workingTask.id);
        newTask.setDescription(description);
        database.taskModel().updateTask(newTask);
    }


}