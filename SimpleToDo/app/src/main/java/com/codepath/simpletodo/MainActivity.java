package com.codepath.simpletodo;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;



public class MainActivity extends AppCompatActivity
        implements TaskListFragment.OnListFragmentInteractionListener,TaskDetailFragment.OnTaskEditFragmentInteractionListener,
TaskDetailNoEditFragment.OnTaskNoEditFragmentInteractionListener{

    private final String TODO_LIST_FRAGMENT_TAG = "TODO_LIST_FRAGMENT_TAG";
    private  final  String TODO_DETAIL_FRAGMENT_TAG = "TODO_DETAIL_FRAGMENT_TAG";
    private  final  String TODO_DETAIL_NO_EDIT_FRAGMENT_TAG = "TODO_DETAIL_NO_EDIT_FRAGMENT_TAG";

    public final static int EDIT_TASK = 1;
    public final static int SAVE_TASK = 2;
    public final static int DELETE_TASK = 3;
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

        if (task != null) {
            TaskDetailNoEditFragment taskDetailNoEditFragment =
                    (TaskDetailNoEditFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_NO_EDIT_FRAGMENT_TAG);

            if (taskDetailNoEditFragment == null) {
                taskDetailNoEditFragment = TaskDetailNoEditFragment.newInstance(null, null);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailNoEditFragment, TODO_DETAIL_NO_EDIT_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailNoEditFragment.setData(task);
            } else {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailNoEditFragment, TODO_DETAIL_NO_EDIT_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailNoEditFragment.setData(task);
            }


        } else {

            TaskDetailFragment taskDetailFragment =
                    (TaskDetailFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_FRAGMENT_TAG);

            if (taskDetailFragment == null) {
                taskDetailFragment = TaskDetailFragment.newInstance(null, null);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailFragment.setData(null);
            } else {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailFragment.setData(null);

            }
        }
    }

    @Override
    public void onTaskEditFragmentInteraction(Task currTask, int action) {
        Log.d(TAG, "On Fragment Interaction Detail View");
        if (action == SAVE_TASK) {

            Log.d(TAG, "Save Task Call back is in action");
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
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }

        } else if (action == DELETE_TASK) {

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
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }

        }
    }

    @Override
    public void onTaskNoEditFragmentInteraction(Task currTask) {
        Log.d(TAG, "OnTaskNoEditFragmentInteraction");

        TaskDetailFragment taskDetailFragment =
        (TaskDetailFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_FRAGMENT_TAG);

        if (taskDetailFragment == null) {
            taskDetailFragment = TaskDetailFragment.newInstance(null, null);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, taskDetailFragment, TODO_DETAIL_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            taskDetailFragment.setData(currTask);
        } else {

            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, taskDetailFragment, TODO_DETAIL_FRAGMENT_TAG);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            taskDetailFragment.setData(currTask);

        }


    }
}
