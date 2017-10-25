package com.tdsm.todoapp.data.source.remote;

import android.support.annotation.NonNull;
import android.support.v4.view.AbsSavedState;

import com.google.common.collect.Lists;
import com.tdsm.todoapp.data.Task;
import com.tdsm.todoapp.data.source.TasksDataSource;

import java.util.LinkedHashMap;
import java.util.Map;
import android.os.Handler;


public class TasksRemoteDataSource implements TasksDataSource {

    // Task Remote Singleton Instance
    private static TasksRemoteDataSource INSTANCE;

   // Fake remote data source
   private final static Map<String,Task> TASKS_SERVICE_DATA;
   // latency  simulation interval interval
   private final static int SERVICE_LATENCY_IN_MILLIS=5000;

   static {
      TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
      addTask("Build tower in Pisa", "Ground looks good, no foundation work required.");
      addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!");
   }

   // Creating Singleton Instance of TaskRemote
   public static TasksRemoteDataSource getInstance() {
      if(INSTANCE == null)
      {
         INSTANCE = new TasksRemoteDataSource();
      }
      return INSTANCE;

   }

   private static void addTask(String title, String description) {
      Task task = new Task(title,description);
      TASKS_SERVICE_DATA.put(title, task);
   }

   // private Constructor to prevent Direct Instantiation
 private TasksRemoteDataSource() {}

    @Override
        public void getTasks(final @NonNull LoadTasksCallback callback) {
        //Simulate network request using
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onTasksLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
            }
        },SERVICE_LATENCY_IN_MILLIS);

        }

        @Override
        public void getTask(@NonNull String taskId, @NonNull GetTaskCallBack callBack) {

        }

        @Override
        public void saveTask(@NonNull Task task) {

        }

        @Override
        public void completeTask(@NonNull String taskId) {

        }

        @Override
        public void completeTask(@NonNull Task task) {

        }

        @Override
    public void activateTask(@NonNull Task task) {

    }

    @Override
    public void activateTask(@NonNull String taskId) {

    }

    @Override
    public void clearCompletedTasks() {

    }

    @Override
    public void refreshTask() {

    }

    @Override
    public void deleteAllTask() {

    }

    @Override
    public void deleteTask(@NonNull String taskId) {

    }
}
