package com.example.kamel.thuglifegame.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class BankActivity extends AppCompatActivity {

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        final TextView bank = (TextView) findViewById(R.id.tvTotal);
        final TextView money = (TextView) findViewById(R.id.tvCash);

        final Button bDeposit = (Button) findViewById(R.id.bDeposit);
        final Button bWithdraw = (Button) findViewById(R.id.bWithdraw);

        final EditText etDeposit = (EditText) findViewById(R.id.etDeposit);
        final EditText etWithdraw = (EditText) findViewById(R.id.etWithdraw);

        player = new Player();

        player.setCash(20000.50);

        money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");
        bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");


        bDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = etDeposit.getText().toString();
                Float cash = Float.parseFloat(temp);

                player.addBank(cash);

                bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");
                money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");

            }
        });

        bWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = etWithdraw.getText().toString();
                Float cash = Float.parseFloat(temp);

                player.minusBank(cash);

                bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");
                money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");
            }
        });




    }
}
