package com.example.timepod.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TaskDao {

    @Insert
    public void insertTask(TaskItem taskItem);

    @Query("SELECT * FROM taskitem WHERE date==:dateString" )
    public TaskItem[] getTaskByDate(String dateString);

}
