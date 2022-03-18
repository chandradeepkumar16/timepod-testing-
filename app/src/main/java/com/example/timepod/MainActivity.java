package com.example.timepod;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.timepod.adapter.TaskListAdapter;
import com.example.timepod.callback.ActionCallback;
import com.example.timepod.config.Dataconfig;
import com.example.timepod.database.AppDatabase;
import com.example.timepod.database.TaskItem;
import com.example.timepod.utils.CustomDialogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ActionCallback.DatePickerCallback {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

//    @BindView(R.id.toolbar_title)
//    TextView toolbartitle;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.tv_no_result)
    TextView no_result;

    @BindView(R.id.task_count)
    TextView taskcount;

    @BindView(R.id.today_title)
    TextView todayTitle;

    private TaskListAdapter adapter;
    private List<TaskItem> alltasks;
    private AppDatabase db;
    private String chooseDate;

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        getSupportActionBar().setTitle(" ");
//        toolbartitle.setText("ToDo list");

        db=AppDatabase.getDatabase(this);
        chooseDate=Dataconfig.getCurrentDate(this);
        alltasks= new ArrayList<>();
        alltasks.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TaskListAdapter(this, alltasks);
        recyclerView.setAdapter(adapter);

        Log.e(TAG, "Current Date " + Dataconfig.getCurrentDate(this));
//        new FetchTask(Dataconfig.getCurrentDate(this)).execute();

    }


    @Override
    protected void onResume() {
        super.onResume();
        new FetchTask(chooseDate).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CustomDialogUtil.showDatePickerDialog(this,this);
        return true;
    }

    @OnClick(R.id.btn_add_task) void clickAddTask(){
        startActivity(new Intent(this,AddEditTask.class));
    }

    @Override
    public void selectedDate(String dateString) {
        if(dateString.equalsIgnoreCase(Dataconfig.getCurrentDate(this))){
            todayTitle.setText("Today");
        }else{
            todayTitle.setText(Dataconfig.formatDate(this, dateString));
        }
        chooseDate=dateString;
        new FetchTask(dateString).execute();
    }

    class FetchTask extends AsyncTask<Void , Void , Void>{
        String dateString;

        public FetchTask(String dateString) {
            this.dateString = dateString;
            alltasks.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            alltasks.addAll(Arrays.asList( db.taskDao().getTaskByDate(dateString)));
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            taskcount.setText(alltasks.size() + "  tasks");
            Log.e(TAG, "all tasks" + alltasks.size());
            super.onPostExecute(unused);
            if(alltasks.size()>0){
                no_result.setVisibility(View.GONE);
            }else{
                no_result.setVisibility(View.VISIBLE);
                alltasks.clear();
            }

            adapter.notifyDataSetChanged();

        }
    }


}