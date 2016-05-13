package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.kamel.thuglifegame.R;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        final TextView Username = (TextView) findViewById(R.id.tvUsername);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Username.setText(username);

    }
}
