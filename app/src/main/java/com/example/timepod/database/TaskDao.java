package com.example.timepod.database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TaskDao {

    @Insert
    public void insertTask(TaskItem taskItem);

}
