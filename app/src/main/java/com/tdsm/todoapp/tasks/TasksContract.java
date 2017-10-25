package com.tdsm.todoapp.tasks;

import com.tdsm.todoapp.BasePresenter;
import com.tdsm.todoapp.BaseView;
import com.tdsm.todoapp.data.Task;

import java.util.List;

/**
 *  Specify Contract between View and Presenter
 */
public interface TasksContract {

    interface View extends BaseView<Presenter> {
        void showTasks(List<Task> tasks);
    }

    interface Presenter extends BasePresenter {
        void loadTasks();
    }
}
