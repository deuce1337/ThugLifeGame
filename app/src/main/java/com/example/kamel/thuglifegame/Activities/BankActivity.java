package com.example.kamel.thuglifegame.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

public class BankActivity extends AppCompatActivity {

    private Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        final TextView bank = (TextView) findViewById(R.id.tvTotal);
        final TextView money = (TextView) findViewById(R.id.tvCash);

        final Button bDeposit = (Button) findViewById(R.id.bDeposit);
        final Button bWithdraw = (Button) findViewById(R.id.bWithdraw);

        final EditText etDeposit = (EditText) findViewById(R.id.etDeposit);
        final EditText etWithdraw = (EditText) findViewById(R.id.etWithdraw);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        player.setUsername(username);

        player.GetJSON();

        bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");
        money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");

        assert bDeposit != null;
        bDeposit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String temp = etDeposit.getText().toString();
                Float cash = Float.parseFloat(temp);

                if(player.getCash() > 0 && player.getCash() >= cash)
                {
                    player.addBank(cash);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BankActivity.this);
                    builder.setMessage("Nie masz tyle hajsu przy dupie aby wpłacic do bankomatu, pewnie wydałeś na browara")
                            .setNegativeButton("Wróć", null)
                            .create()
                            .show();
                }

                bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");
                money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");

            }
        });

        assert bWithdraw != null;
        bWithdraw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String temp = etWithdraw.getText().toString();
                Float cash = Float.parseFloat(temp);

                if(player.getBank() > 0 && player.getBank() >= cash)
                {
                    player.minusBank(cash);
                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BankActivity.this);
                    builder.setMessage("Nie masz tyle hajsu w banku aby wypłacić, czyżby stara była tu przed Tobą ?")
                            .setNegativeButton("Wróć", null)
                            .create()
                            .show();
                }

                bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");
                money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");
            }
        });
    }
}