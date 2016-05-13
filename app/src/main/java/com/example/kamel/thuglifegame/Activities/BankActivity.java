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

        final TextView total = (TextView) findViewById(R.id.tvTotal);

        final Button bdeposit = (Button) findViewById(R.id.bDeposit);

        final EditText etdeposit = (EditText) findViewById(R.id.etDeposit);

        player = new Player();


        bdeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = etdeposit.getText().toString();
                Float cash = Float.parseFloat(temp);

                player.setCash(cash);
            }
        });

        total.setText("Saldo: " + String.valueOf(player.getCash()));


    }
}
