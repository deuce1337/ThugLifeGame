package com.example.kamel.thuglifegame;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);


        SQLiteDatabase mydatabase = openOrCreateDatabase("db_list",MODE_PRIVATE,null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS questsList(name VARCHAR,description VARCHAR);");
        mydatabase.execSQL("INSERT INTO questsList VALUES('Staruszka','Ukradnij portfel babci');");

        final ListView questList = (ListView) findViewById(R.id.lvQuest);

        Cursor resultSet = mydatabase.rawQuery("Select * from questsList",null);
        resultSet.moveToFirst();
        String name = resultSet.getString(1);
        String description = resultSet.getString(2);


        arrayList = new ArrayList<String>();
        arrayList.add(name);
        arrayList.add(description);

        //adapter = new ArrayAdapter<String>(this,arrayList);


    }
}
