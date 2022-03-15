package com.example.timepod.callback;

public interface ActionCallback {

    interface DatePickerCallback{
        void selectedDate(String dateString);
    }

    interface TimerPickerCallBack{
        void selectedTime(String timeString);
    }
}
