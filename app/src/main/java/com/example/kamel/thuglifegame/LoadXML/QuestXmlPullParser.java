package com.example.kamel.thuglifegame.LoadXML;

/**
 * Created by Kamel on 2016-04-26.
 */
import android.content.Context;

import com.example.kamel.thuglifegame.LoadXML.QuestList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestXmlPullParser {

    static final String KEY_QUEST = "quest";
    static final String KEY_NAME = "name";
    static final String KEY_ABOUT = "about";
    static final String KEY_IMAGE_URL = "image";

    public static List<QuestList> getQuestListsFromFile(Context ctx) {

        // List of QuestLists that we will return
        List<QuestList> QuestLists;
        QuestLists = new ArrayList<QuestList>();

        // temp holder for current QuestList while parsing
        QuestList curQuestList = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("questList.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_QUEST)) {
                            // If we are starting a new <site> block we need
                            //a new QuestList object to represent it
                            curQuestList = new QuestList();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_QUEST)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            QuestLists.add(curQuestList);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            // if </name> use setName() on curSite
                            curQuestList.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_ABOUT)) {
                            // if </about> use setAbout() on curSite
                            curQuestList.setAbout(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_IMAGE_URL)) {
                            // if </image> use setImgUrl() on curSite
                            curQuestList.setImgUrl(curText);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the populated list.
        return QuestLists;
    }
}
