package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.kamel.thuglifegame.R;

public class CasinoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casino);

        final ImageButton ibDice = (ImageButton) findViewById(R.id.ibDice);


        ibDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diceIntent = new Intent(CasinoActivity.this, DiceGameActivity.class);
                CasinoActivity.this.startActivity(diceIntent);
            }
        });
    }
}
