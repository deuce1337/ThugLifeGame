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
        final ImageButton ibWhores = (ImageButton) findViewById(R.id.ibHookers);
        final ImageButton ibShop = (ImageButton) findViewById(R.id.ibShop);
        final ImageButton ibDealer = (ImageButton) findViewById(R.id.ibDealer);
        final ImageButton ibHospital = (ImageButton) findViewById(R.id.ibHospital);
        final ImageButton ibQuests = (ImageButton) findViewById(R.id.ibQuests);
        final ImageButton ibCasino = (ImageButton) findViewById(R.id.ibCasino);
        final ImageButton ibPrison = (ImageButton) findViewById(R.id.ibPrison);
        final ImageButton ibVIP = (ImageButton) findViewById(R.id.ibVIP);
        final ImageButton ibHscore = (ImageButton) findViewById(R.id.ibHighscore);
        final ImageButton ibAchieve = (ImageButton) findViewById(R.id.ibAchievement);

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

        ibWhores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whoresIntent = new Intent(GameActivity.this, WhoresActivity.class);
                GameActivity.this.startActivity(whoresIntent);
            }
        });

        ibShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopIntent = new Intent(GameActivity.this, ShopActivity.class);
                GameActivity.this.startActivity(shopIntent);
            }
        });

        ibDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dealerIntent = new Intent(GameActivity.this, DealerActivity.class);
                GameActivity.this.startActivity(dealerIntent);
            }
        });

        ibHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hospitalIntent = new Intent(GameActivity.this, HospitalActivity.class);
                GameActivity.this.startActivity(hospitalIntent);
            }
        });

        ibQuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questsIntent = new Intent(GameActivity.this, QuestActivity.class);
                GameActivity.this.startActivity(questsIntent);
            }
        });
        ibCasino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent casinoIntent = new Intent(GameActivity.this, CasinoActivity.class);
                GameActivity.this.startActivity(casinoIntent);
            }
        });
        ibPrison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prisonIntent = new Intent(GameActivity.this, PrisonActivity.class);
                GameActivity.this.startActivity(prisonIntent);
            }
        });
        ibVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vipIntent = new Intent(GameActivity.this, VIPActivity.class);
                GameActivity.this.startActivity(vipIntent);
            }
        });
        ibHscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hscoreIntent = new Intent(GameActivity.this, HscoreActivity.class);
                GameActivity.this.startActivity(hscoreIntent);
            }
        });
        ibAchieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent achieveIntent = new Intent(GameActivity.this, AchieveActivity.class);
                GameActivity.this.startActivity(achieveIntent);
            }
        });
    }
}
