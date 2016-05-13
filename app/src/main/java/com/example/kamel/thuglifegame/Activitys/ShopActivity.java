package com.example.kamel.thuglifegame.Activitys;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.kamel.thuglifegame.LoadXML.Downloader;
import com.example.kamel.thuglifegame.LoadXML.Shop.ShopAdapter;
import com.example.kamel.thuglifegame.LoadXML.Shop.ShopXmlPullParser;
import com.example.kamel.thuglifegame.R;

import java.io.FileNotFoundException;

public class ShopActivity extends AppCompatActivity {

    private ShopAdapter sAdapter;
    private ListView shopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        shopList = (ListView) findViewById(R.id.lvGuns);

        /*
		 * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if(isNetworkAvailable()){
            Log.i("shopList", "starting download Task");
            SitesDownloadTask downloader = new SitesDownloadTask();
            downloader.execute();
        }else{
            sAdapter = new ShopAdapter(getApplicationContext(), -1, ShopXmlPullParser.getShopListsFromFile(ShopActivity.this));
            shopList.setAdapter(sAdapter);
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
                Downloader.DownloadFromUrl("http://thuglifegame.xyz/shop/shopList.xml", openFileOutput("shopList.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            sAdapter = new ShopAdapter(ShopActivity.this, -1, ShopXmlPullParser.getShopListsFromFile(ShopActivity.this));
            shopList.setAdapter(sAdapter);
            Log.i("shopList", "adapter size = "+ sAdapter.getCount());
        }
    }
}
