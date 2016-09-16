package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kamel.thuglifegame.R;
import com.example.kamel.thuglifegame.Player;

public class GameActivity extends AppCompatActivity
{
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView user = (TextView) findViewById(R.id.tvUsername);
        TextView cash = (TextView) findViewById(R.id.tvCash);
        TextView energy = (TextView) findViewById(R.id.tvEnergy);
        TextView respect = (TextView) findViewById(R.id.tvRespect);
        TextView high = (TextView) findViewById(R.id.tvHigh);

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
        final Button bStatistic = (Button) findViewById(R.id.bStat);

        bStatistic.setVisibility(View.VISIBLE);
        bStatistic.setBackgroundColor(Color.TRANSPARENT);

        //Pobieranie username z bazy danych
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        user.setText(player.getUsername());
        cash.setText("Kasa: "+ player.getCash());
        energy.setText("Energia: "+ player.getEnergy());
        respect.setText("Respekt: "+ player.getRespect());
        high.setText("Bania: " + player.getHigh());

        Log.i("username", player.getUsername());
        Log.i("cash", String.valueOf(player.getCash()));
        Log.i("energy", String.valueOf(player.getEnergy()));
        Log.i("respect", String.valueOf(player.getRespect()));
        Log.i("high", String.valueOf(player.getHigh()));

        bStatistic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent statIntent = new Intent(GameActivity.this, StatisticActivity.class);
                statIntent.putExtra("username", username);

                GameActivity.this.startActivity(statIntent);
            }
        });

        //Button BANK
        ibBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bankIntent = new Intent(GameActivity.this, BankActivity.class);
                bankIntent.putExtra("username", username);
                GameActivity.this.startActivity(bankIntent);
            }
        });
        //Button Dziwki
        ibWhores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whoresIntent = new Intent(GameActivity.this, WhoresActivity.class);
                GameActivity.this.startActivity(whoresIntent);
            }
        });
        //Button Sklep
        ibShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shopIntent = new Intent(GameActivity.this, ShopActivity.class);
                GameActivity.this.startActivity(shopIntent);
            }
        });
        //Button Dealer
        ibDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dealerIntent = new Intent(GameActivity.this, DealerActivity.class);
                dealerIntent.putExtra("username", username);
                GameActivity.this.startActivity(dealerIntent);
            }
        });
        //Button Szpital
        ibHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hospitalIntent = new Intent(GameActivity.this, HospitalActivity.class);
                GameActivity.this.startActivity(hospitalIntent);
            }
        });
        //Button Misje
        ibQuests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questsIntent = new Intent(GameActivity.this, QuestActivity.class);
                questsIntent.putExtra("username", username);
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