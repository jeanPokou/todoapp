package com.tdsm.todoapp.data.source;


import android.support.annotation.NonNull;

import com.tdsm.todoapp.data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksRepository implements TasksDataSource {

    // Factory Object for repository
    private static TasksRepository INSTANCE = null;

    // Defining the different source of data

    private final TasksDataSource mTasksRemoteDataSource;
    private final TasksDataSource mTasksLocalDataSource;

    // Marks cache as Invalid to force refresh next time data is requested
    boolean mCacheIsDirty = false;

    // Cached task with package visibility for it will be use in Testing
    Map<String,Task> mCachedTasks;

    // Private Constructor to prevent direct Instantiation
    private TasksRepository(@NonNull TasksDataSource tasksRemoteDataSource,@NonNull TasksDataSource tasksLocalDataSource){
        mTasksLocalDataSource = tasksLocalDataSource;
        mTasksRemoteDataSource = tasksRemoteDataSource;
    }

    // Return  Singleton Instance of this class, creating if necessary
    public static TasksRepository getInstance(TasksDataSource tasksRemoteDataSource,TasksDataSource tasksLocalDataSource) {
        if(INSTANCE == null) {
            INSTANCE = new TasksRepository(tasksRemoteDataSource,tasksLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getTasks(@NonNull final LoadTasksCallback callback) {

        // Respond immediately with cached if available
        if(mCachedTasks != null && !mCacheIsDirty) {
            callback.onTasksLoaded(new ArrayList<Task>(mCachedTasks.values()));
            return;
        }
        if(mCacheIsDirty) {
            // If cache is dirty fetch data from the  remote source again
            getTaskFromRemoteDataSource(callback);
        } else {
            // Query the Local Storage is available . If not query the network
            mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTasksLoaded(List<Task> tasks) {
                    refreshCache(tasks);
                    callback.onTasksLoaded(new ArrayList<Task>(mCachedTasks.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getTaskFromRemoteDataSource(callback);
                }
            });
        }

    }


    private void refreshCache(List<Task> tasks) {
        mCacheIsDirty = true;
    }

    private void getTaskFromRemoteDataSource(LoadTasksCallback callback) {
    }

    @Override
    public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallBack callBack) {
        checkNotNull(callBack);
        checkNotNull(taskId);

        final Task cachedTask  = getTaskWithId(taskId);
        if(cachedTask != null) {
            callBack.onTaskLoaded(cachedTask);
            return;
        }

        // Check if data is in the local data source, if not load from network
        mTasksLocalDataSource.getTask(taskId, new GetTaskCallBack() {
            @Override
            public void onTaskLoaded(Task task) {
                // Do in memory cache update to keep app UI to date
                if(mCachedTasks == null) {
                    mCachedTasks = new HashMap<String, Task>();
                }
                mCachedTasks.put(task.getId(),task);
                callBack.onTaskLoaded(task);

            }

            @Override
            public void onDataNotAvailable() {
                mTasksRemoteDataSource.getTask(taskId, new GetTaskCallBack(){

                    @Override
                    public void onTaskLoaded(Task task) {
                        // Do in memory cache update
                        if(mCachedTasks == null) {
                            mCachedTasks = new HashMap<String, Task>();
                            mCachedTasks.put(task.getId(),task);
                            callBack.onTaskLoaded(task);
                        }
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callBack.onDataNotAvailable();
                    }
                });
            }
        });


    }

    private Task getTaskWithId( @NonNull String taskId) {
        checkNotNull(taskId);
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        }else {
            return mCachedTasks.get(taskId);
        }
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
