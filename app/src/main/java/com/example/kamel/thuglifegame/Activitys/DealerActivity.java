package com.example.kamel.thuglifegame.Activitys;

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

        /*
		 * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if(isNetworkAvailable()){
            Log.i("drugList", "starting download Task");
            SitesDownloadTask downloader = new SitesDownloadTask();
            downloader.execute();
        }else{
            sAdapter = new DealerAdapter(getApplicationContext(), -1, DealerXmlPullParser.getDealerListsFromFile(DealerActivity.this));
            dealerList.setAdapter(sAdapter);
        }


    }

    //Helper method to determine if Internet connection is available.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*
	 * AsyncTask that will download the xml file for us and store it locally.
	 * After the download is done we'll parse the local file.
	 */
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("http://thuglifegame.xyz/dealer/drugList.xml", openFileOutput("drugList.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            sAdapter = new DealerAdapter(DealerActivity.this, -1, DealerXmlPullParser.getDealerListsFromFile(DealerActivity.this));
            dealerList.setAdapter(sAdapter);
            Log.i("drugList", "adapter size = "+ sAdapter.getCount());
        }
    }
}
