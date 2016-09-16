package com.example.kamel.thuglifegame.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.kamel.thuglifegame.LoadXML.Dealer.DealerAdapter;
import com.example.kamel.thuglifegame.LoadXML.Dealer.DealerXmlPullParser;
import com.example.kamel.thuglifegame.LoadXML.Downloader;
import com.example.kamel.thuglifegame.R;

import java.io.FileNotFoundException;

public class DealerActivity extends AppCompatActivity {

    private DealerAdapter sAdapter;
    private ListView dealerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer);

        dealerList = (ListView) findViewById(R.id.lvDrugs);

        if(isNetworkAvailable()){
            Log.i("drugList", "starting download Task");
            SitesDownloadTask downloader = new SitesDownloadTask();
            downloader.execute();
        }else{
            sAdapter = new DealerAdapter(getApplicationContext(), -1, DealerXmlPullParser.getDealerListsFromFile(DealerActivity.this));
            dealerList.setAdapter(sAdapter);
        }


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                Downloader.DownloadFromUrl("http://thuglifegame.xyz/dealer/drugList.xml", openFileOutput("drugList.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){

            sAdapter = new DealerAdapter(DealerActivity.this, -1, DealerXmlPullParser.getDealerListsFromFile(DealerActivity.this));
            dealerList.setAdapter(sAdapter);
            Log.i("drugList", "adapter size = "+ sAdapter.getCount());
        }
    }
}
