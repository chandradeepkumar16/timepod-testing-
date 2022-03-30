package com.example.timepod;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.OnClick;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    TextView settings;
    TextView timer;
    TextView pomodoro;
    TextView help;
    TextView dark;
    TextView notes;
    TextView friend;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        settings= (TextView)findViewById(R.id.settingstext);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, SettingsActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on settings", Toast.LENGTH_SHORT).show();
            }
        });

        timer= (TextView)findViewById(R.id.setatimertext);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, TimerActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on set a timer", Toast.LENGTH_SHORT).show();
            }
        });

        pomodoro= (TextView)findViewById(R.id.pomodorotext);
        pomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, PomodoroActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on pomodoro", Toast.LENGTH_SHORT).show();
            }
        });

        help= (TextView)findViewById(R.id.helptext);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, HelpActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on help", Toast.LENGTH_SHORT).show();
            }
        });

        dark= (TextView)findViewById(R.id.darkmodetext);
        dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, DarkModeActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on dark mode", Toast.LENGTH_SHORT).show();
            }
        });

        notes= (TextView)findViewById(R.id.addnotestext);
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, AddNotesActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on add notes", Toast.LENGTH_SHORT).show();
            }
        });

        friend= (TextView)findViewById(R.id.tellyourfriendtext);
        friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProfileActivity.this, TellYourFriendActivity.class);
                startActivity(intent);

                Toast.makeText(ProfileActivity.this, "clicked on tell your friend", Toast.LENGTH_SHORT).show();
            }
        });
    }
}