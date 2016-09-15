package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamel.thuglifegame.MissionControl;
import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class MarketActivity extends AppCompatActivity
{
    private int MSVal;
    private int cashGainVal;

    Player player = new Player();
    MissionControl mission = new MissionControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        TextView respVal = (TextView) findViewById(R.id.respVal);
        TextView expVal = (TextView) findViewById(R.id.expVal);
        TextView strVal = (TextView) findViewById(R.id.strVal);
        TextView intVal = (TextView) findViewById(R.id.intVal);
        TextView agiVal = (TextView) findViewById(R.id.agiVal);
        final TextView result = (TextView) findViewById(R.id.tvResult);
        final TextView cashGain = (TextView) findViewById(R.id.tvCashGain);

        final Button bStart = (Button) findViewById(R.id.bStart);

        assert respVal != null;
        respVal.setText(String.valueOf(player.getRespect()));
        assert expVal != null;
        expVal.setText(String.valueOf(player.getExp()));
        assert strVal != null;
        strVal.setText(String.valueOf(player.getStrength()));
        assert intVal != null;
        intVal.setText(String.valueOf(player.getInteligence()));
        assert agiVal != null;
        agiVal.setText(String.valueOf(player.getAgility()));

        MSVal = player.getInteligence() * 2;
        mission.setCashRewardGrade(2);
        mission.difficultyCalc();

        assert bStart != null;
        bStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mission.setEnergy(player.getEnergy());
                mission.setRespect(player.getRespect());
                mission.setExp(player.getExp());
                mission.setStrength(player.getStrength());
                mission.setIntelligence(player.getInteligence());
                mission.setAgility(player.getAgility());

                mission.setMSVal(MSVal);

                cashGainVal = mission.cashCalc();

                result.setText(mission.successCalc());

                cashGain.setText("Zgarniasz " + cashGainVal + "$");

                Log.i("result", mission.successCalc());
                Log.i("cash", String.valueOf(cashGainVal));
                Log.i("diff", String.valueOf(mission.difficultyCalc()));
            }
        });
    }
}