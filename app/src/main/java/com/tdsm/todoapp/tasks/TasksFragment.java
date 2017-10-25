package com.tdsm.todoapp.tasks;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tdsm.todoapp.R;
import com.tdsm.todoapp.data.Task;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {//@link TasksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class TasksFragment extends Fragment  implements TasksContract.View  {

    private static final String TAG = TasksFragment.class.getSimpleName();
    private TasksContract.Presenter mPresenter;

    public TasksFragment() {
        // Required empty public constructor
    }

    public static TasksFragment newInstance() {
        return new TasksFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false);
    }


    @Override
    public void setPresenter( @NonNull TasksContract.Presenter presenter) {
       mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showTasks(List<Task> tasks) {
        for ( Task item: tasks ) {
            Log.i(TAG, item.toString() );
        }
    }
}
