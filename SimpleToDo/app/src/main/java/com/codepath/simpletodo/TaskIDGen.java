package com.codepath.simpletodo;

/**
 * Created by manoj on 8/22/17.
 */

public class TaskIDGen {

    public static int taskID;

    public static int getTaskId () {
        return ++taskID;
    }

}
