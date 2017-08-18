package com.codepath.simpletodo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.webkit.WebSettings.RenderPriority.HIGH;
import static com.codepath.simpletodo.R.id.prioritySpinner;
import static com.codepath.simpletodo.R.id.task;
import static com.codepath.simpletodo.R.id.taskStatusSpinner;
import android.widget.AdapterView.OnItemSelectedListener;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TaskDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TaskDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailFragment extends Fragment implements  OnItemSelectedListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Task workingTask;
    private EditText summary;
    private EditText description;
    private Spinner taskStatus;
    private Spinner priority;

    public enum PriorityLevel {
        HIGH,
        MEDIUM,
        LOW
    }

    public enum TaskStatus {
        NotStarted,
        InProgress,
        Done
    }

    private static final String TAG = "TaskDetailFragment";

    private OnFragmentInteractionListener mListener;

    public TaskDetailFragment() {
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
    public static TaskDetailFragment newInstance(String param1, String param2) {
        TaskDetailFragment fragment = new TaskDetailFragment();
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
        View view =  inflater.inflate(R.layout.fragment_task_detail, container, false);

        summary = (EditText) view.findViewById(R.id.summary);
        description = (EditText) view.findViewById(R.id.description);
        priority = (Spinner) view.findViewById(R.id.prioritySpinner);
        taskStatus = (Spinner) view.findViewById(R.id.taskStatusSpinner);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(TAG, "Task is already set: task "+workingTask.summary);
        summary.setText(workingTask.summary);
        if (workingTask.description != null) {
            description.setText(workingTask.description);
        } else {

        }

        // set the Spinner and Dates here
        addSpinnerForPriority();

        addSpinnerForStatus();

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void addSpinnerForPriority() {
        // Spinner click listener
        priority.setOnItemSelectedListener(this);
        List<String> priorityList  = new ArrayList<>();
        priorityList.add(PriorityLevel.HIGH.name());
        priorityList.add(PriorityLevel.MEDIUM.name());
        priorityList.add(PriorityLevel.LOW.name());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, priorityList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        priority.setAdapter(dataAdapter);

    }

    private void addSpinnerForStatus() {
        priority.setOnItemSelectedListener(this);
        List<String> taskStatusList  = new ArrayList<>();
        taskStatusList.add(TaskStatus.NotStarted.name());
        taskStatusList.add(TaskStatus.InProgress.name());
        taskStatusList.add(TaskStatus.Done.name());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, taskStatusList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        taskStatus.setAdapter(dataAdapter);

    }
}
