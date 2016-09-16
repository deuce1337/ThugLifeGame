package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamel.thuglifegame.MissionControl;
import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class TaxiActivity extends AppCompatActivity
{
    private int MSVal;
    private int cashGainVal;

    Player player = new Player();
    MissionControl mission = new MissionControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        TextView respVal = (TextView) findViewById(R.id.respVal);
        TextView expVal = (TextView) findViewById(R.id.expVal);
        TextView strVal = (TextView) findViewById(R.id.strVal);
        TextView intVal = (TextView) findViewById(R.id.intVal);
        TextView agiVal = (TextView) findViewById(R.id.agiVal);
        TextView energyCost = (TextView) findViewById(R.id.tvEnergyCost);
        final TextView result = (TextView) findViewById(R.id.tvResult);
        final TextView cashGain = (TextView) findViewById(R.id.tvCashGain);

        final Button bStart = (Button) findViewById(R.id.bStart);

        assert energyCost != null;
        energyCost.setText("10");
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

        MSVal = player.getStrength() + player.getInteligence();
        mission.setCashRewardGrade(8);
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

                if (mission.getSuccess())
                {
                    result.setText(mission.successCalc());
                    cashGain.setText("Zgarniasz " + cashGainVal + "$");
                    player.upToDB("cash", "add", String.valueOf(cashGainVal));
                    player.upToDB("energy", "sub", String.valueOf(10));
                }
                else
                {
                    result.setText(mission.successCalc());
                    cashGain.setText("Nie dla psa kasa");
                }
            }
        });
    }
}