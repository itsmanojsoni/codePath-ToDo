package com.codepath.simpletodo.data;

import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Entity;

/**
 * Created by manoj on 8/13/17.
 */

@Entity
public class Task {

    @PrimaryKey
    public final long id;
    public String summary;
    public String description;
    public String done;
    public String priority;

    public Task(long id, String summary, String description, String done, String priority) {
        this.id = id;
        this.summary = summary;
        this.description = description;
        this.done = done;
        this.priority = priority;
    }


    public String getPriority () {
        return priority;
    }

    public String getTaskStatus() {
        return done;
    }

    public String getSummary () {
        return summary;
    }

    public String getDescription () {
        return description;
    }

    public void setPriority (String priority) {
        this.priority = priority;
    }

    public void setSummary (String summary) {
        this.summary = summary;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public void setTaskStatus (String taskStatus) {
        this.done = taskStatus;
    }



    public static TaskBuilder builder(){
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private long id;
        private String summary = "";
        private String description = "";
        private String done = "NotStarted";
        private String priority = "LOW";

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

        public TaskBuilder setDone(String done) {
            this.done = done;
            return this;
        }

        public TaskBuilder setPriority (String priority) {
            this.priority = priority;
            return this;
        }

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
