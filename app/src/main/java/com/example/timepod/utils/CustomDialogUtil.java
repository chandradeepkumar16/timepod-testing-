package com.example.timepod.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.timepod.R;
import com.example.timepod.callback.ActionCallback;


public class CustomDialogUtil {

    public static String TAG=CustomDialogUtil.class.getSimpleName();
    public static String selectedDate="" , selectedTime="";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static  void showDatePickerDialog(Context context, ActionCallback.DatePickerCallback callback){
        AlertDialog.Builder dialog =new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.date_picker,null);
        CalendarView calendarView = dialogView.findViewById(R.id.calenderView);
        dialog.setView(dialogView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.e(TAG,"Year "+ year + "Month " + month + "Day " + dayOfMonth);
                selectedDate= dayOfMonth + "-" + (month+1) + "-" + year;
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callback.selectedDate(selectedDate);

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }

    public static void showTimePicker(Context context, ActionCallback.TimerPickerCallBack callBack){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View timeDialog = LayoutInflater.from(context).inflate(R.layout.time_picker_dialog,null);
        TimePicker timePicker =timeDialog.findViewById(R.id.time_picker);
        dialog.setView(timeDialog);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedTime=hourOfDay + " :"+ minute;
            }
        });
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                callBack.selectedTime(selectedTime);
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = dialog.create();
        alert.show();
    }
}
