package com.example.kamel.thuglifegame.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class StatisticActivity extends AppCompatActivity
{
    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    private Player player = new Player();
    TextView user, level, exp, str, intel, agi, res;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        user = (TextView) findViewById(R.id.tvUsername);
//        level = (TextView) findViewById(R.id.tvLevel);
        exp = (TextView) findViewById(R.id.tvExperience);
        str = (TextView) findViewById(R.id.tvStrength);
        intel = (TextView) findViewById(R.id.tvIntelligence);
        agi = (TextView) findViewById(R.id.tvAgility);
        res = (TextView) findViewById(R.id.tvRespect);

        new BackgroundTask().execute();
    }
    public void GetJSON(View view)
    {
        new BackgroundTask().execute();
    }
    class BackgroundTask extends AsyncTask<Void,Void,String>
    {
        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        String Url;

        @Override
        protected void onPreExecute()
        {
            Url = "http://thuglifegame.xyz/api.php?username=" + username;
        }

        @Override
        protected String doInBackground(Void... params)
        {
            try
            {
                URL url = new URL(Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((JSON_STRING = bufferedReader.readLine()) != null)
                {
                    stringBuilder.append(JSON_STRING + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {

            try
            {
                JSONObject JO = new JSONObject(result);

                String userStr = JO.getString("username");
//                String levelStr = JO.getString("bank");
                String expStr = JO.getString("exp");
                String strStr = JO.getString("strengh");
                String intelStr = JO.getString("inteligence");
                String agiStr = JO.getString("agility");
                String resStr = JO.getString("respect");

                user = (TextView) findViewById(R.id.tvUsername);
//                level = (TextView) findViewById(R.id.tvLevel);
                exp = (TextView) findViewById(R.id.tvExperience);
                str = (TextView) findViewById(R.id.tvStrength);
                intel = (TextView) findViewById(R.id.tvIntelligence);
                agi = (TextView) findViewById(R.id.tvAgility);
                res = (TextView) findViewById(R.id.tvRespect);

                user.setText(userStr);
                exp.setText(expStr);
                str.setText(strStr);
                intel.setText(intelStr);
                agi.setText(agiStr);
                res.setText(resStr);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}