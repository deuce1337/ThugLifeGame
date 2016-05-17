package com.example.kamel.thuglifegame.LoadXML.Shop;

/**
 * Created by Kamel on 2016-04-26.
 */
import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ShopXmlPullParser {

    static final String KEY_GUN = "gun";
    static final String KEY_NAME = "name";
    static final String KEY_ABOUT = "about";
    static final String KEY_IMAGE_URL = "image";
    static final String KEY_PRICE = "price";
    static final String KEY_ACTIVITY = "activity";


    public static List<ShopList> getShopListsFromFile(Context ctx) {

        // List of ShopLists that we will return
        List<ShopList> ShopLists;
        ShopLists = new ArrayList<ShopList>();

        // temp holder for current ShopList while parsing
        ShopList curShopList = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis = ctx.openFileInput("shopList.xml");
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
                        if (tagname.equalsIgnoreCase(KEY_GUN)) {
                            // If we are starting a new <site> block we need
                            //a new ShopList object to represent it
                            curShopList = new ShopList();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_GUN)) {
                            // if </site> then we are done with current Site
                            // add it to the list.
                            ShopLists.add(curShopList);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            // if </name> use setName() on curSite
                            curShopList.setName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_ABOUT)) {
                            // if </about> use setAbout() on curSite
                            curShopList.setAbout(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_IMAGE_URL)) {
                            // if </image> use setImgUrl() on curSite
                            curShopList.setImgUrl(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_PRICE)) {
                            // if </price> use setPrice() on curSite
                            curShopList.setPrice(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_ACTIVITY)) {
                            // if </activity> use setActivity() on curSite
                            curShopList.setActivity(curText);
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
        return ShopLists;
    }
}