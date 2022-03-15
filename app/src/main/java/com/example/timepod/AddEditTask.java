package com.example.timepod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.timepod.callback.ActionCallback;
import com.example.timepod.database.AppDatabase;
import com.example.timepod.database.TaskItem;
import com.example.timepod.utils.CustomDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("ALL")
public class AddEditTask extends AppCompatActivity implements ActionCallback.DatePickerCallback , ActionCallback.TimerPickerCallBack {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    @BindView(R.id.et_title)
    EditText ettitle;

    @BindView(R.id.et_date)
    EditText etdate;

    @BindView(R.id.et_time)
    EditText ettime;

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        ButterKnife.bind(this);
        db=AppDatabase.getDatabase(this);


    }

    @OnClick(R.id.btn_save) void clickSave(){

        if(ettime.getText().toString().length()<=0 || ettime.getText().toString().length()<=0 ||
        etdate.getText().toString().length()<=0){
            Toast.makeText(this, "please fill the required field", Toast.LENGTH_SHORT).show();
        }else{
            TaskItem taskItem=new TaskItem();
            taskItem.title= ettitle.getText().toString();
            taskItem.date=etdate.getText().toString();
            taskItem.time=ettime.getText().toString();
            new AddTask(taskItem).execute();
        }

    }

    @OnClick(R.id.et_date) void chooseDate(){
        CustomDialogUtil.showDatePickerDialog(this,this);
    }

    @OnClick(R.id.et_time) void chooseTime(){
        CustomDialogUtil.showTimePicker(this,this);
    }

    @Override
    public void selectedDate(String dateString) {
        etdate.setText(dateString);
    }

    @Override
    public void selectedTime(String timeString) {
        ettime.setText(timeString);
    }

    class AddTask extends AsyncTask<Void , Void , Void> {

        TaskItem taskItem;

        public AddTask(TaskItem taskItem){
            this.taskItem=taskItem;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.taskDao().insertTask(taskItem);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            ettime.setText("");
            etdate.setText("");
            ettitle.setText("");
        }
    }
}