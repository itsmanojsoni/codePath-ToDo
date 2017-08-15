package com.codepath.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.simpletodo.db.TaskContract;
import com.codepath.simpletodo.db.TaskDbHelper;
import com.codepath.simpletodo.dummy.DummyContent;
import com.codepath.simpletodo.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private static final String TAG = "ItemFrgament";
    private TaskDbHelper mHelper;
    private List<String> taskList = new ArrayList<>();
    MyItemRecyclerViewAdapter adapter;
    private int taskId;
    private List<Task> allTaskList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        mHelper = new TaskDbHelper(getContext());


//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
//                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
//                null, null, null, null, null);
//        while(cursor.moveToNext()) {
//            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
//            Log.d(TAG, "Reading Task: " + cursor.getString(idx));
//            taskList.add(cursor.getString(idx));
//        }




//        cursor.close();
//        db.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        Log.d(TAG, "Button was clicked");
        final  EditText taskEditText = (EditText) view.findViewById(R.id.etNewItem);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        Context context = view.getContext();
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        adapter = new MyItemRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);

        Button button = (Button) view.findViewById(R.id.btnAddItem);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String task = String.valueOf(taskEditText.getText());
                Log.d(TAG, "Task = "+task);

                AppDatabase database = AppDatabase.getDatabase(getContext());
//                Task build = Task.builder().setId(i).setSummary("Testing " + i).setDescription("More ..." + i).build();

                Log.d(TAG, "Button Clicked and summary is set to : "+task);
                Task build = Task.builder().setId(taskId++).setSummary(task).build();
                database.taskModel().addTask(build);

                allTaskList = database.taskModel().getAllTasks();
                Log.d(TAG, "Adapter Update Data and allTaskList is : "+allTaskList.size());
                adapter.updateData(allTaskList);

                InputMethodManager inputManager =
                        (InputMethodManager) getActivity().
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(
                        getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                taskEditText.setText("");

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppDatabase database = AppDatabase.getDatabase(getContext());
        allTaskList = database.taskModel().getAllTasks();

        Log.d(TAG, "OnResume and allTaskList Size is : "+allTaskList.size());
        adapter.updateData(allTaskList);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
