package com.tdsm.todoapp.tasks;

import com.tdsm.todoapp.BasePresenter;
import com.tdsm.todoapp.BaseView;

import java.util.List;

/**
 *  Specify Contract between View and Presenter
 */
public interface TasksContract {

    interface View extends BaseView<Presenter> {
        void showTasks(List<String> tasks);
    }

    interface Presenter extends BasePresenter {
        void loadTasks();
    }
}
