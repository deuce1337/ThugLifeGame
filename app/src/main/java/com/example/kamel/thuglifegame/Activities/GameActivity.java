package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kamel.thuglifegame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GameActivity extends AppCompatActivity {

    String JSON_STRING;

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
        final Button bStatistic = (Button) findViewById(R.id.bStat);

        bStatistic.setVisibility(View.VISIBLE);
        bStatistic.setBackgroundColor(Color.TRANSPARENT);

        //Pobieranie username z bazy danych i wyświetlanie go za pomocą TextView
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");

        Username.setText(username);

        //Pobieranie parametrów statystyk z bazy danych
        new BackgroundTask().execute();

        bStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    //Klasa pobierająca z bazy danych wszystkie informacje i prezentująca to w odpowiednich TextView
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String Url;

        @Override
        protected void onPreExecute() {
            Url = "http://thuglifegame.xyz/Stats2.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            final TextView cash = (TextView) findViewById(R.id.tvCash);
            cash.setText(result);
        }
    }

}
