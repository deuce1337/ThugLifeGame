package com.example.kamel.thuglifegame.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

import java.util.Random;

public class DiceGameActivity extends AppCompatActivity {

    private int diceThrow;
    private int myScore;
    private int oppScore;
    private short round;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_game);

        final TextView myResult = (TextView) findViewById(R.id.tvMyResult);
        final TextView oppResult = (TextView) findViewById(R.id.tvOppResult);
        final TextView message = (TextView) findViewById(R.id.tvMessage);

        final ImageView diceView = (ImageView) findViewById(R.id.ivDiceView);

        final Button bThrow = (Button) findViewById(R.id.bThrow);

        player = new Player();

        myScore = 0;
        oppScore = 0;
        round = 1;

        myResult.setText("Twój wynik to: " + Integer.toString(myScore) + " pkt");
        oppResult.setText("Wynik Wiesława to: " + Integer.toString(oppScore) + " pkt");


        bThrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                player.minusCash(1000);

                //KROK 1

                message.setText("Runda " + Integer.toString(round));
                bThrow.setEnabled(false);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Random generator = new Random();
                        diceThrow = generator.nextInt(6) + 1;

                        myScore += diceThrow;


                        showMessage(diceThrow, message, diceView);

                        myResult.setText("Twój wynik to: " + Integer.toString(myScore) + " pkt");
                    }
                }, 3000);


                //KROK 2

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        message.setText("Teraz rzuca Wiesław !");
                        diceView.setImageResource(0);
                    }
                }, 6000);

                //KROK 3

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        oppThrow(message, diceView);

                        oppResult.setText("Wynik Wiesława to: " + Integer.toString(oppScore) + " pkt");
                        round++;
                    }
                }, 9000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(round == 4)
                        {
                            bThrow.setEnabled(false);
                            message.setText("Gra zakończona");
                            diceView.setImageResource(0);

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(myScore > oppScore)
                                    {
                                        message.setText("$$ Gratulacje wygrałeś hajs $$");
                                        player.addCash(2000);
                                        player.addExp(50);
                                    }else if(oppScore > myScore)
                                    {
                                        message.setText("Serio myślałeś, że wygrasz z tym starym wyjadaczem ?");
                                    }else
                                    {
                                        message.setText("Remis, 1000$ wraca na twoje konto");
                                        player.addCash(1000);
                                        player.addExp(25);
                                    }
                                }
                            }, 2500);

                        }else {
                            bThrow.setEnabled(true);
                            message.setText("Aby rozpocząć runde " + Integer.toString(round) + " kliknij RZUT !");
                            diceView.setImageResource(0);
                        }
                    }
                }, 12000);

            }

        });

    }

    public void oppThrow(TextView message, ImageView dice)
    {
        Random generator = new Random();
        diceThrow = generator.nextInt(6) + 1;

        oppScore += diceThrow;

        showMessage(diceThrow, message, dice);

    }

    public void showMessage(int diceScore, TextView message, ImageView iv)
    {
        switch(diceScore) {
            case 1:
                message.setText("Wypadła jedynka !");
                iv.setImageResource(R.drawable.dice1);
                break;
            case 2:
                message.setText("Wypadła dwójka !");
                iv.setImageResource(R.drawable.dice2);
                break;
            case 3:
                message.setText("Wypadła trójka !");
                iv.setImageResource(R.drawable.dice3);
                break;
            case 4:
                message.setText("Wypadła czwórka !");
                iv.setImageResource(R.drawable.dice4);
                break;
            case 5:
                message.setText("Wypadła piątka !");
                iv.setImageResource(R.drawable.dice5);
                break;
            case 6:
                message.setText("Wypadła szóstka !");
                iv.setImageResource(R.drawable.dice6);
                break;
        }
    }

}
