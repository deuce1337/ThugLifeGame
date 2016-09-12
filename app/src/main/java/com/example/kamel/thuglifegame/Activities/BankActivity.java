package com.example.kamel.thuglifegame.Activities;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kamel.thuglifegame.Player;
import com.example.kamel.thuglifegame.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BankActivity extends AppCompatActivity {


    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private Player player = new Player();

    TextView money, bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        bank = (TextView) findViewById(R.id.tvTotal);
        money = (TextView) findViewById(R.id.tvCash);

        final Button bDeposit = (Button) findViewById(R.id.bDeposit);
        final Button bWithdraw = (Button) findViewById(R.id.bWithdraw);

        final EditText etDeposit = (EditText) findViewById(R.id.etDeposit);
        final EditText etWithdraw = (EditText) findViewById(R.id.etWithdraw);

        new BackgroundTask().execute();


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

            try {

                bank = (TextView) findViewById(R.id.tvTotal);
                money = (TextView) findViewById(R.id.tvCash);
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("server_response");

                int count = 0;

                String cash, banks;

                JSONObject JO = jsonArray.getJSONObject(count);


                cash = JO.getString("cash");
                banks = JO.getString("bank");

                double moneys = Double.parseDouble(cash);
                double bankBal = Double.parseDouble(banks);

//                Log.i("kasa: ", String.valueOf(moneys));
//                Log.i("bank: ", String.valueOf(bankBal));

                player.setCash(moneys);
                player.setBank(bankBal);

                money.setText("Hajs przy dupie: " + String.valueOf(player.getCash()) + " $");
                bank.setText("Saldo: " + String.valueOf(player.getBank()) + " $");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
