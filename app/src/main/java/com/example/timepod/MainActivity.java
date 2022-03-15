package com.example.timepod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

//    @BindView(R.id.toolbar_title)
//    TextView toolbartitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        getSupportActionBar().setTitle(" ");
//        toolbartitle.setText("ToDo list");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return true;
    }

    @OnClick(R.id.btn_add_task) void clickAddTask(){
        startActivity(new Intent(this,AddEditTask.class));
    }


}