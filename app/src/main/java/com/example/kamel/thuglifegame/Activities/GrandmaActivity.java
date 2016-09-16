package com.example.kamel.thuglifegame.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kamel.thuglifegame.MissionControl;
import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class GrandmaActivity extends AppCompatActivity
{
    private int MSVal;
    private int cashGainVal;

    Player player = new Player();
    MissionControl mission = new MissionControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grandma);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        final TextView respVal = (TextView) findViewById(R.id.respVal);
        final TextView expVal = (TextView) findViewById(R.id.expVal);
        TextView strVal = (TextView) findViewById(R.id.strVal);
        TextView intVal = (TextView) findViewById(R.id.intVal);
        TextView agiVal = (TextView) findViewById(R.id.agiVal);
        TextView energyCost = (TextView) findViewById(R.id.tvEnergyCost);
        final TextView energyVal = (TextView) findViewById(R.id.tvEnergyVal);
        final TextView result = (TextView) findViewById(R.id.tvResult);
        final TextView cashGain = (TextView) findViewById(R.id.tvCashGain);

        final Button bStart = (Button) findViewById(R.id.bStart);

        assert energyCost != null;
        energyCost.setText("10");
        assert energyVal != null;
        energyVal.setText(String.valueOf(player.getEnergy()));
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

        MSVal = player.getAgility() + player.getInteligence();
        mission.setCashRewardGrade(3);
        mission.difficultyCalc();

        assert bStart != null;
        bStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                player.GetJSON();
//                Log.i("energy", String.valueOf(player.getEnergy()));

                mission.setEnergy(player.getEnergy());
                mission.setRespect(player.getRespect());
                mission.setExp(player.getExp());
                mission.setStrength(player.getStrength());
                mission.setIntelligence(player.getInteligence());
                mission.setAgility(player.getAgility());

                if (player.getEnergy() > 24)
                {
                    mission.setMSVal(MSVal);
                    cashGainVal = mission.cashCalc();

                    if (mission.getSuccess())
                    {
//                        Log.i("result", String.valueOf(mission.getSuccess()));
                        assert result != null;
                        result.setText(mission.successCalc());
                        assert cashGain != null;
                        cashGain.setText("Zgarniasz " + cashGainVal + "$");
                        player.upToDB("cash", "add", String.valueOf(cashGainVal));
                        player.upToDB("energy", "sub", String.valueOf(25));
                        player.upToDB("respect", "add", String.valueOf(6));
                        player.upToDB("exp", "add", String.valueOf(35));
                        player.GetJSON();
                        energyVal.setText(String.valueOf(player.getEnergy()));
                        assert respVal != null;
                        respVal.setText(String.valueOf(player.getRespect()));
                        assert expVal != null;
                        expVal.setText(String.valueOf(player.getExp()));
                    }

                    else
                    {
//                        Log.i("result", String.valueOf(mission.getSuccess()));
                        result.setText(mission.successCalc());
                        cashGain.setText("Nie dla psa kasa");
                        player.upToDB("energy", "sub", String.valueOf(25));
                        player.GetJSON();
                        energyVal.setText(String.valueOf(player.getEnergy()));
                    }
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GrandmaActivity.this);
                    builder.setMessage("Nie masz tyle energii aby rozpocząć tę akcję!")
                            .setNegativeButton("Wróć", null)
                            .create()
                            .show();
                }
            }
        });
    }
}