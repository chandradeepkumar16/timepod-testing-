package com.example.timepod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @BindView(R.id.btn_save)
    Button btn_save;

    private AppDatabase db;
    public TaskItem parmitem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        ButterKnife.bind(this);
        db=AppDatabase.getDatabase(this);

        parmitem= (TaskItem) getIntent().getSerializableExtra("item");

        if(parmitem!=null){
            ettitle.setText(parmitem.title);
            ettime.setText(parmitem.time);
            etdate.setText(parmitem.date);
        }
    }

    @OnClick(R.id.btn_save) void clickSave(){

        if(ettime.getText().toString().length()<=0 || ettime.getText().toString().length()<=0 ||
        etdate.getText().toString().length()<=0){
            Toast.makeText(this, "please fill the required field", Toast.LENGTH_SHORT).show();
        }else{

            if(parmitem!=null){
                parmitem.title=ettitle.getText().toString();
                parmitem.date=etdate.getText().toString();
                parmitem.time=ettime.getText().toString();
                new AddTask(parmitem).execute();
            }else{
                TaskItem taskItem=new TaskItem();
                taskItem.title= ettitle.getText().toString();
                taskItem.date=etdate.getText().toString();
                taskItem.time=ettime.getText().toString();
                new AddTask(taskItem).execute();
            }

        }

    }



    @OnClick(R.id.et_date) void chooseDate(){
        CustomDialogUtil.showDatePickerDialog(this,this);
    }

    @OnClick(R.id.et_time) void chooseTime(){
        CustomDialogUtil.showTimePicker(this,this);
    }


    @OnClick(R.id.btn_save) void save(){
        startActivity(new Intent(this,MainActivity.class));
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

            if(parmitem!=null)
                db.taskDao().updateTask(taskItem);
            else
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