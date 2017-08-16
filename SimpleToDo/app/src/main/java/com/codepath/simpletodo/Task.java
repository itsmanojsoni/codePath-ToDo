package com.codepath.simpletodo;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

import java.util.Date;

/**
 * Created by manoj on 8/13/17.
 */

@Entity
public class Task {

    @PrimaryKey
    public final long id;
    public String summary;
    public String description;
    public boolean done;
    public String priority;

//    public Date dueDate;

    public Task(long id, String summary, String description, boolean done, String priority) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.done = done;
        this.priority = priority;
//        this.dueDate = dueDate;
    }


    public void setPriority (String priority) {
        this.priority = priority;
    }


    public static TaskBuilder builder(){
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private long id;
        private String summary = "";
        private String description = "";
        private boolean done = false;
        private String priority = "LOW";
//        private Date dueDate;

        public TaskBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public TaskBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public TaskBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder setDone(boolean done) {
            this.done = done;
            return this;
        }

        public TaskBuilder setPriority (String priority) {
            this.priority = priority;
            return this;
        }

//        public TaskBuilder setDueDate(Date dueDate) {
//            this.dueDate = new Date(dueDate.getTime());
//            return this;
//        }

        public Task build() {
            return new Task(id, summary, description, done, priority);
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
