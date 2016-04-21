package com.example.kamel.thuglifegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final TextView Username = (TextView) findViewById(R.id.tvUsername);

        final ImageButton ibBank = (ImageButton) findViewById(R.id.ibBank);
        final ImageButton ibWhore = (ImageButton) findViewById(R.id.ibWhore);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Username.setText(username);

        ibBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bankIntent = new Intent(GameActivity.this, BankActivity.class);
                GameActivity.this.startActivity(bankIntent);
            }
        });
        ibWhore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whoreIntent = new Intent(GameActivity.this, WhoresActivity.class);
                GameActivity.this.startActivity(whoreIntent);
            }
        });
    }
}
