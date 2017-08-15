package com.codepath.simpletodo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.codepath.simpletodo.dummy.DummyContent;


public class MainActivity extends AppCompatActivity  implements ItemFragment.OnListFragmentInteractionListener{

    private final String TODO_LIST_FRAGMENT_TAG = "TODO_LIST_FRAGMENT_TAG";
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);


        ItemFragment listFragment =
                (ItemFragment) getSupportFragmentManager().findFragmentByTag(TODO_LIST_FRAGMENT_TAG);

        if (listFragment == null) {
            Log.d("MainActivity", "gListFragment is null");
            listFragment = ItemFragment.newInstance(1);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.listContainer, listFragment, TODO_LIST_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {

            Log.d("MainActivity", "list Fragment not null");
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

        Log.d(TAG, "Item Clicked");
    }
}
