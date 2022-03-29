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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements ActionCallback.DatePickerCallback , ActionCallback.TaskitemClicks {

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);
        ButterKnife.bind(this);

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));


        db=AppDatabase.getDatabase(this);
        chooseDate=Dataconfig.getCurrentDate(this);
        alltasks= new ArrayList<>();
        alltasks.clear();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new TaskListAdapter(this, alltasks , this);
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
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CustomDialogUtil.showDatePickerDialog(this,this);
        return true;
    }

    @OnClick(R.id.home) void clickAddTask(){
        startActivity(new Intent(this,AddEditTask.class));
    }

    @OnClick(R.id.progress) void clickProgressActivity(){
        startActivity(new Intent(this, ProgressActivity.class));
    }

    @OnClick(R.id.profile) void clickProfileActivity(){
        startActivity(new Intent(this, ProfileActivity.class));
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



    @Override
    public void clickItem(TaskItem taskItem, View view) {
        PopupMenu popupMenu = new PopupMenu(this,view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,  popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        startActivity(new Intent(MainActivity.this , AddEditTask.class)
                        .putExtra("item ", taskItem));
                        break;
                    case R.id.menu_delete:
                        new DeleteTask(taskItem).execute();
                        break;
//                    case R.id.completed:
////                        mytasklayout.setBackgroundResource(R.color.taskbg);
//                        break;
                }
                return true;
            }
        });
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

    class DeleteTask extends AsyncTask<Void , Void , Void>{
        TaskItem taskItem;

        public DeleteTask(TaskItem taskItem){
            this.taskItem=taskItem;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.taskDao().deleteTask(taskItem);
            return null;
        }


        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            alltasks.clear();
            new FetchTask(chooseDate).execute();
        }
    }


}