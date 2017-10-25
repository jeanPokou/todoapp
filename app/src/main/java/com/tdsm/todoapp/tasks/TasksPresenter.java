package com.tdsm.todoapp.tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tdsm.todoapp.data.Task;
import com.tdsm.todoapp.data.source.TasksDataSource;
import com.tdsm.todoapp.data.source.TasksRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class TasksPresenter implements TasksContract.Presenter {

    private static final String TAG = TasksPresenter.class.getSimpleName() ;
    private final TasksRepository mTasksRepository;
    private final TasksContract.View mTasksView;

    public TasksPresenter(@NonNull TasksRepository tasksRepository,@NonNull TasksContract.View taskView) {

        // Getting Repository
        mTasksRepository = checkNotNull(tasksRepository,"task repository can not be null");
        mTasksView = checkNotNull(taskView,"tasksView can not be null");

        mTasksView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadTasks() {
        // Loading Task
        loadTask();
    }

    private  void loadTask() {
        mTasksRepository.getTasks(new TasksDataSource.LoadTasksCallback() {
            @Override
            public void onTasksLoaded(List<Task> tasks) {
                mTasksView.showTasks(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                Log.i(TAG, "Data not availabele");
            }
        });

    }


}
