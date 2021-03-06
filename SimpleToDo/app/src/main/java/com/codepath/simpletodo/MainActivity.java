package com.codepath.simpletodo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codepath.simpletodo.data.Task;
import com.codepath.simpletodo.fragments.TaskDetailEditFragment;
import com.codepath.simpletodo.fragments.TaskDetailNoEditFragment;
import com.codepath.simpletodo.fragments.TaskListFragment;


public class MainActivity extends AppCompatActivity
        implements TaskListFragment.OnListFragmentInteractionListener,TaskDetailEditFragment.OnTaskEditFragmentInteractionListener,
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
            listFragment = TaskListFragment.newInstance(1);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Task task) {

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

            TaskDetailEditFragment taskDetailEditFragment =
                    (TaskDetailEditFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_FRAGMENT_TAG);

            if (taskDetailEditFragment == null) {
                taskDetailEditFragment = TaskDetailEditFragment.newInstance(null, null);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailEditFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailEditFragment.setData(null);
            } else {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailEditFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailEditFragment.setData(null);

            }
        }
    }

    @Override
    public void onTaskEditFragmentInteraction(Task currTask, int action) {

        if (action == SAVE_TASK) {

            TaskListFragment listFragment =
                    (TaskListFragment) getSupportFragmentManager().findFragmentByTag(TODO_LIST_FRAGMENT_TAG);

            if (listFragment == null) {
                listFragment = TaskListFragment.newInstance(1);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            } else {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }

        } else if (action == DELETE_TASK) {

            TaskListFragment listFragment =
                    (TaskListFragment) getSupportFragmentManager().findFragmentByTag(TODO_LIST_FRAGMENT_TAG);

            if (listFragment == null) {
                listFragment = TaskListFragment.newInstance(1);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            } else {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }

        }
    }

    @Override
    public void onTaskNoEditFragmentInteraction(Task currTask, int action) {

        if (action == EDIT_TASK) {
            TaskDetailEditFragment taskDetailEditFragment =
                    (TaskDetailEditFragment) getSupportFragmentManager().findFragmentByTag(TODO_DETAIL_FRAGMENT_TAG);

            if (taskDetailEditFragment == null) {
                taskDetailEditFragment = TaskDetailEditFragment.newInstance(null, null);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailEditFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailEditFragment.setData(currTask);
            } else {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, taskDetailEditFragment, TODO_DETAIL_FRAGMENT_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                taskDetailEditFragment.setData(currTask);

            }
        } else {
            // delete Task
            TaskListFragment listFragment =
                    (TaskListFragment) getSupportFragmentManager().findFragmentByTag(TODO_LIST_FRAGMENT_TAG);

            if (listFragment == null) {
                listFragment = TaskListFragment.newInstance(1);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            } else {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
                fragmentTransaction.commit();
            }
        }


    }
}
