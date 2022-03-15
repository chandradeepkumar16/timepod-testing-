package com.example.timepod.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskItem.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    public static AppDatabase INSTANCE_DB;

    public static AppDatabase getDatabase(Context context){
        if(INSTANCE_DB==null){
            INSTANCE_DB= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"todolist_db").build();
        }

        return INSTANCE_DB;
    }
}
