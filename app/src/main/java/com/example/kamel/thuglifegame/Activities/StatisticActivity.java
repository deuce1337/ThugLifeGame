package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class StatisticActivity extends AppCompatActivity
{
    Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        TextView user = (TextView) findViewById(R.id.tvUsername);
//        final TextView Level = (TextView) findViewById(R.id.tvLevel);
        TextView exp= (TextView) findViewById(R.id.tvExperience);
        TextView str = (TextView) findViewById(R.id.tvStrength);
        TextView intel = (TextView) findViewById(R.id.tvIntelligence);
        TextView agi = (TextView) findViewById(R.id.tvAgility);
        TextView res = (TextView) findViewById(R.id.tvRespect);

        user.setText(player.getUsername());
        exp.setText("Doświadczenie: " + player.getExp());
        str.setText("Siła: " + player.getStrength());
        intel.setText("Inteligencja: " + player.getInteligence());
        agi.setText("Zręczność: " + player.getAgility());
        res.setText("Respekt: " + player.getRespect());

        Log.i("username", player.getUsername());
        Log.i("exp", String.valueOf(player.getExp()));
        Log.i("cash", String.valueOf(player.getCash()));
        Log.i("energy", String.valueOf(player.getEnergy()));
        Log.i("respect", String.valueOf(player.getRespect()));
    }
}