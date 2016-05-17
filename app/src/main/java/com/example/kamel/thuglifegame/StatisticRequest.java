package com.example.kamel.thuglifegame;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kamel on 2016-05-17.
 */
public class StatisticRequest extends StringRequest {

    private static final String STATS_REQUEST_URL = "http://thuglifegame.xyz/Stats.php";
    private Map<String, String> params;

    public StatisticRequest(String cash, String bank, Response.Listener<String> listener)
    {
        super(Request.Method.POST, STATS_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("cash", cash);
        params.put("bank", bank);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

