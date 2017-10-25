package com.tdsm.todoapp.data.source;

import android.support.annotation.NonNull;
import com.tdsm.todoapp.data.Task;
import java.util.List;

public interface TasksDataSource {

    // Interface defining callbacks for loading tasks
   interface LoadTasksCallback {
       void onTasksLoaded(List<Task> tasks);
       void onDataNotAvailable();
   }

   // Interface defining callbacks for loading a task
   interface  GetTaskCallBack {
        void onTaskLoaded(Task task);
       void onDataNotAvailable();
    }

    // Define all the differents actions that can be executed against a task data source
    void getTasks(@NonNull LoadTasksCallback callback);
    void getTask(@NonNull String taskId,@NonNull GetTaskCallBack callBack);
    void saveTask(@NonNull Task task);
    void completeTask(@NonNull String taskId);
    void completeTask(@NonNull Task task);
    void activateTask(@NonNull Task task);
    void activateTask(@NonNull String taskId);
    void clearCompletedTasks();
    void refreshTask();
    void deleteAllTask();
    void deleteTask (@NonNull String taskId);

}
