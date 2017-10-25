package com.tdsm.todoapp.data;

import  android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;
import com.google.common.base.Objects;

public final class Task {
    @NonNull
    private final String mId;

    @Nullable
    private final String mTitle;

    @Nullable
    private final String mDescription;

    private final boolean mCompleted;

    // Constructor for creating a new task
    public Task(@Nullable String title,@Nullable String description) {
       this(title,description,UUID.randomUUID().toString(),false);
    }

    // Constructor for creating a task with and id already provided
    public Task(@Nullable String title, @Nullable String description,@NonNull String id, boolean completed){
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }
    // Constructor for creating  a new completed Task
    public Task(@Nullable String title,@Nullable String description,boolean completed) {
        this( title,description,UUID.randomUUID().toString(),completed);
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @Nullable
    public String getTitle(){
        return mTitle;
    }
    @Nullable
    public String getDescription(){
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    @Override
    public boolean equals(Object obj) {
        // Self check
        if(this == obj ) return true;
        // Null Check
        if (obj == null) return false;
        // Type Check
        if(getClass() != obj.getClass()) return false;
        // Cast
        Task task =(Task) obj;
        return Objects.equal(mId,task.mId) &&
                Objects.equal(mTitle,task.mTitle) &&
                Objects.equal(mDescription,task.mDescription) &&
                 Objects.equal(mCompleted,task.mCompleted) ;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mId,mTitle,mDescription);
    }

    @Override
    public String toString() {
        return "Task title " + mTitle + "description " + mDescription;
    }
}
