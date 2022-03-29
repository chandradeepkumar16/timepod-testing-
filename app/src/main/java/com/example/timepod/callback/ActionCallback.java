package com.example.timepod.callback;

import android.view.View;

import com.example.timepod.database.TaskItem;

public interface ActionCallback {

    interface DatePickerCallback{
        void selectedDate(String dateString);
    }

    interface TimerPickerCallBack{
        void selectedTime(String timeString);
    }

    interface TaskitemClicks{
        void clickItem(TaskItem taskItem , View view);
    }
}
