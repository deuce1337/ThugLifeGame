package com.example.kamel.thuglifegame;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Player
{
    private String Username;
    private String JSON_STRING;

    private double Cash = 0;
    private double Bank = 0;

    private int Energy = 0;
//    public int Level = 0;
    private int High = 0;
    private int Exp = 0;
    private int Strength = 0;
    private int Inteligence = 0;
    private int Agility = 0;
    private int Respect = 0;

    public StringBuilder stringBuilder=null;

    public String getUsername()
    {
        return Username;
    }

    public void setUsername(String username)
    {
        Username = username;
    }

    public double getCash()
    {
        return this.Cash;
    }

    public void setCash(double cash)
    {
        this.Cash = cash;
    }

    public void addCash(double cash)
    {
        Cash += cash;
    }

    public void minusCash(double cash)
    {
        Cash -= cash;
    }

    public double getBank()
    {
        return Bank;
    }

    public void setBank(double bank)
    {
        Bank = bank;
    }

    public void addBank (double bank)
    {
        Cash -= bank;
        Bank += bank;
    }

    public void minusBank (double bank)
    {
        Cash += bank;
        Bank -= bank;
    }

    public int getEnergy()
    {
        return this.Energy;
    }

    public void setEnergy(int energy)
    {
        this.Energy = energy;
    }

//    public int getLevel()
//    {
//        return Level;
//    }
//
//    public void setLevel(int level)
//    {
//        Level = level;
//    }

    public int getHigh()
    {
        return High;
    }

    public void setHigh(int high)
    {
        High = high;
    }

    public int getExp()
    {
        return Exp;
    }

    public void setExp(int exp)
    {
        Exp = exp;
    }

    public void addExp(int exp)
    {
        Exp = exp;
    }

    public int getStrength()
    {
        return Strength;
    }

    public void setStrength(int strength)
    {
        Strength = strength;
    }

    public int getInteligence()
    {
        return Inteligence;
    }

    public void setInteligence(int inteligence)
    {
        Inteligence = inteligence;
    }

    public int getAgility()
    {
        return Agility;
    }

    public void setAgility(int agility)
    {
        Agility = agility;
    }

    public int getRespect()
    {
        return Respect;
    }

    public void setRespect(int respect)
    {
        Respect = respect;
    }

    public void GetJSON()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final String username = getUsername();
        String Url;
        Url = "http://thuglifegame.xyz/api.php?username=" + username;

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

            String SB = stringBuilder.toString().trim();
            JSONObject JO = new JSONObject(SB);

            setUsername(JO.getString("username"));

            String cashStr = JO.getString("cash");
            setCash(Integer.parseInt(cashStr));

            String bankStr = JO.getString("bank");
            setBank(Integer.parseInt(bankStr));

            String energyStr = JO.getString("energy");
            setEnergy(Integer.parseInt(energyStr));

//            String levelStr = JO.getString("####");

            String highStr = JO.getString("high");
            setHigh(Integer.parseInt(highStr));

            String expStr = JO.getString("exp");
            setExp(Integer.parseInt(expStr));

            String strStr = JO.getString("strengh");
            setStrength(Integer.parseInt(strStr));

            String intelStr = JO.getString("inteligence");
            setInteligence(Integer.parseInt(intelStr));

            String agiStr = JO.getString("agility");
            setAgility(Integer.parseInt(agiStr));

            String resStr = JO.getString("respect");
            setRespect(Integer.parseInt(resStr));
        }

        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}