package com.example.timepod.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskItem {

    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name="time")
    public String time;

    @ColumnInfo(name="date")
    public String date;


}
