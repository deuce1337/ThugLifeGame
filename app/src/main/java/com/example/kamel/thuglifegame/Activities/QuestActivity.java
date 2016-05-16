package com.example.kamel.thuglifegame.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kamel.thuglifegame.LoadXML.Downloader;
import com.example.kamel.thuglifegame.LoadXML.Quests.QuestAdapter;
import com.example.kamel.thuglifegame.LoadXML.Quests.QuestXmlPullParser;
import com.example.kamel.thuglifegame.R;

import java.io.FileNotFoundException;

public class QuestActivity extends AppCompatActivity {

    private QuestAdapter mAdapter;
    private ListView questList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);


        questList = (ListView) findViewById(R.id.lvQuest);

        questList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String activity = mAdapter.getItem(position).getActivity();

                Intent quest = new Intent();
                quest.setClassName("com.example.kamel.thuglifegame", activity);

                QuestActivity.this.startActivity(quest);



                Log.i("staruszka", activity);

                /*Class<?> c = null;
                if(activity != null) {
                    try {
                        c = Class.forName(activity );
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch blockx
                        e.printStackTrace();
                    }
                }

                Intent quest = new Intent(QuestActivity.this, c);
                QuestActivity.this.startActivity(quest);*/
            }
        });

      /*
		 * If network is available download the xml from the Internet.
		 * If not then try to use the local file from last time.
		 */
        if(isNetworkAvailable()){
            Log.i("questList", "starting download Task");
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }else{
            mAdapter = new QuestAdapter(getApplicationContext(), -1, QuestXmlPullParser.getQuestListsFromFile(QuestActivity.this));
            questList.setAdapter(mAdapter);
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
                Downloader.DownloadFromUrl("http://thuglifegame.xyz/quests/questList.xml", openFileOutput("questList.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            mAdapter = new QuestAdapter(QuestActivity.this, -1, QuestXmlPullParser.getQuestListsFromFile(QuestActivity.this));
            questList.setAdapter(mAdapter);
            Log.i("questList", "adapter size = "+ mAdapter.getCount());
        }
    }

}
