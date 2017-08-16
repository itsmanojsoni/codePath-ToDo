package com.codepath.simpletodo;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity
        implements TaskListFragment.OnListFragmentInteractionListener,TaskDetailFragment.OnFragmentInteractionListener{

    private final String TODO_LIST_FRAGMENT_TAG = "TODO_LIST_FRAGMENT_TAG";
    private  final  String TODO_DETAIL_FRAGMENT_TAG = "TODO_DETAIL_FRAGMENT_TAG";
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);


        TaskListFragment listFragment =
                (TaskListFragment) getSupportFragmentManager().findFragmentByTag(TODO_LIST_FRAGMENT_TAG);

        if (listFragment == null) {
            Log.d("MainActivity", "gListFragment is null");
            listFragment = TaskListFragment.newInstance(1);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {

            Log.d("MainActivity", "list Fragment not null");
        }
    }

    @Override
    public void onListFragmentInteraction(Task task) {

        Log.d(TAG, "Item Clicked");
        TaskDetailFragment taskDetailFragment =
                (TaskDetailFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_FRAGMENT_TAG);

        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance(null, null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, taskDetailFragment, TODO_LIST_FRAGMENT_TAG);
            fragmentTransaction.commit();
            taskDetailFragment.setData(task);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d(TAG, "On Fragment Interaction Detail View");
    }
}
