package com.codepath.simpletodo.db;

import android.provider.BaseColumns;

/**
 * Created by manoj on 7/28/17.
 */

public class TaskContract {

    public static final String DB_NAME = "com.codepath.simpletodo.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
        public static final String COL_TASK_PRIORITY = "priority";
    }
}
