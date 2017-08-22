package com.codepath.simpletodo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;


import android.widget.AdapterView.OnItemSelectedListener;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnTaskEditFragmentInteractionListener} interface
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
    private static int taskId;
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

    private class TaskStatusSelection implements OnItemSelectedListener{
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Log.d(TAG, "View is : "+view);
            Log.d(TAG, "Parent is :"+parent);
            Log.d(TAG, "Id is :"+id);
            String taskStatusSelection = (String)parent.getItemAtPosition(pos);

            Log.d(TAG, "Selected task Status is : "+taskStatusSelection);
            AppDatabase database = AppDatabase.getDatabase(getContext());

            Task newTask = database.taskModel().getTask(workingTask.id);
            newTask.setPriority(taskStatusSelection);
            database.taskModel().updateTask(newTask);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class PrioritySelection implements OnItemSelectedListener{


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


    }
    private static final String TAG = "TaskDetailFragment";

    private OnTaskEditFragmentInteractionListener mListener;

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
        if (task != null) {
            this.workingTask = task;
        } else {

            AppDatabase database = AppDatabase.getDatabase(getContext());
            Log.d(TAG, "Button Clicked and summary is set to : "+task);
            Task build = Task.builder().setId(taskId++).build();
            database.taskModel().addTask(build);
            this.workingTask = build;

        }
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

        Button saveBtn = view.findViewById(R.id.btnSave);

        Button deleteBtn = view.findViewById(R.id.btnDelete);

        summary = (EditText) view.findViewById(R.id.summary);
        description = (EditText) view.findViewById(R.id.description);
        priority = (Spinner) view.findViewById(R.id.prioritySpinner);
        taskStatus = (Spinner) view.findViewById(R.id.taskStatusSpinner);


//        summary.requestFocus();

        summary.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                summary.setFocusableInTouchMode(true);
                return false;
            }
        });

        summary.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String text = v.getText().toString();
                    updateDatabaseWithNewSummary(text);
                    summary.setFocusable(false);
                    hide_keyboard();
                    return true;
                }
                return false;
            }
        });


        description.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                description.setFocusableInTouchMode(true);
                return false;
            }
        });
        description.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String text = v.getText().toString();
                    updateDatabaseWithNewDescription(text);
                    description.setFocusable(false);
                    hide_keyboard();
                    return true;
                }
                return false;
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    Log.d(TAG, "save button on click called");
                    mListener.onTaskEditFragmentInteraction(workingTask, MainActivity.SAVE_TASK);
                }

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onTaskEditFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTaskEditFragmentInteractionListener) {
            mListener = (OnTaskEditFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTaskNoEditFragmentInteractionListener");
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

        String priority = workingTask.getPriority();
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
    public interface OnTaskEditFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTaskEditFragmentInteraction(Task currTask, int action);
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

    private void addSpinnerForPriority() {
        // Spinner click listener
        priority.setOnItemSelectedListener(new PrioritySelection() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String selectedPriority = (String)parent.getItemAtPosition(pos);

                Log.d(TAG, "Finally, Selected Priority is : "+selectedPriority);
                AppDatabase database = AppDatabase.getDatabase(getContext());

                Task newTask = database.taskModel().getTask(workingTask.id);
                newTask.setPriority(selectedPriority);
                database.taskModel().updateTask(newTask);
            }
        });
        List<String> priorityList  = new ArrayList<>();
        priorityList.add(PriorityLevel.HIGH.name());
        priorityList.add(PriorityLevel.MEDIUM.name());
        priorityList.add(PriorityLevel.LOW.name());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, priorityList);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        boolean found = false;
        int position = -1;
        String priorityThing = workingTask.getPriority();
        for (int i = 0; i < priorityList.size(); i++) {
            if (priorityList.get(i).equals(priorityThing)) {
                found = true;
                position = i;
                break;
            }
        }

        // attaching data adapter to spinner
        priority.setAdapter(dataAdapter);

        if (found) {
            priority.setSelection(position);
        }

    }

    private void hide_keyboard() {
        InputMethodManager inputManager =
                (InputMethodManager) getActivity().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    private void addSpinnerForStatus() {
        taskStatus.setOnItemSelectedListener(new TaskStatusSelection() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String taskStatusSelection = (String)parent.getItemAtPosition(pos);
                Log.d(TAG, "Finally, Selected task Status is : "+taskStatusSelection);
                AppDatabase database = AppDatabase.getDatabase(getContext());

                Task newTask = database.taskModel().getTask(workingTask.id);
                newTask.setTaskStatus(taskStatusSelection);
                database.taskModel().updateTask(newTask);
            }
        });
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

        boolean found = false;
        int position = -1;
        String workingTaskStatus = workingTask.getTaskStatus();
        for (int i = 0; i < taskStatusList.size(); i++) {
            if (taskStatusList.get(i).equals(workingTaskStatus)) {
                found = true;
                position = i;
                break;
            }
        }
        if (found) {
            taskStatus.setSelection(position);
        }
    }
}
