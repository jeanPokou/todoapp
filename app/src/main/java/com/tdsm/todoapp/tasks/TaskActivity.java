package com.tdsm.todoapp.tasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tdsm.todoapp.R;

public class TaskActivity extends AppCompatActivity {

    private TasksPresenter mTasksPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        // Instantiate TasksFragment contentFrame
        TasksFragment tasksFragment = (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(tasksFragment == null) {
            // Create the fragment
            tasksFragment = TasksFragment.newInstance();
            // Load Fragment into
            getSupportFragmentManager().beginTransaction().add(R.id.contentFrame,tasksFragment).commit();
        }

    }
}
