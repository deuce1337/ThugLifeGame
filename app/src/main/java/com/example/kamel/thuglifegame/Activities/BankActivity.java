package com.example.kamel.thuglifegame.Activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BankActivity extends AppCompatActivity {

    private Player player;
    String JSON_STRING;

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

        final String sBank = bank.getText().toString();
        final String sMoney = money.getText().toString();

        new BackgroundTask().execute();

       /* Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if(success)
                    {
                        String cash = jsonResponse.getString("cash");
                        String bankBal = jsonResponse.getString("bank");


                        Log.i("kasa: ", cash);
                        Log.i("bank: ", bankBal);

                        money.setText("Hajs przy dupie: " + String.valueOf(cash) + " $");
                        bank.setText("Saldo: " + String.valueOf(bankBal) + " $");

                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BankActivity.this);
                        builder.setMessage("błąd")
                                .setNegativeButton("Ponów próbę !", null)
                                .create()
                                .show();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();;
                }
            }
        };

        StatisticRequest statsRequest = new StatisticRequest(sMoney, sBank, responseListener);
        RequestQueue queue = Volley.newRequestQueue(BankActivity.this);
        queue.add(statsRequest);*/


        player = new Player();

        player.setCash(20000.50);

        money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");
        bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");


        bDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = etDeposit.getText().toString();
                Float cash = Float.parseFloat(temp);

                if(player.getCash() > 0 && player.getCash() >= cash) {
                    player.addBank(cash);
                }else {
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

        bWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp = etWithdraw.getText().toString();
                Float cash = Float.parseFloat(temp);

                if(player.getBank() > 0 && player.getBank() >= cash) {
                    player.minusBank(cash);
                }else {
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

    public void GetJSON(View view)
    {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        String Url;

        @Override
        protected void onPreExecute() {
            Url = "http://thuglifegame.xyz/Stats.php";
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
            final TextView bank = (TextView) findViewById(R.id.tvTotal);
            bank.setText(result);
        }
    }
}
