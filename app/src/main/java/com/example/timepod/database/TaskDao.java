package com.example.timepod.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Insert
    public void insertTask(TaskItem taskItem);

    @Query("SELECT * FROM taskitem WHERE date==:dateString" )
    public TaskItem[] getTaskByDate(String dateString);

    @Update
    public void updateTask(TaskItem taskItem);

    @Delete
    public  void deleteTask(TaskItem taskItem);
}
