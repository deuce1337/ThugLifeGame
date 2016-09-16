package com.example.kamel.thuglifegame;

import android.util.Log;

import java.util.Random;

public class MissionControl
{
    private int Energy;
    private int Respect;
    private int Exp;
    private int Strength;
    private int Intelligence;
    private int Agility;
    private int MSVal;
    private int RandResult;
    private int RandResult2;
    private int cashRewardGrade;
    private int Difficulty;
    private boolean Success;

    public String result;

    public void setEnergy(int energy)
    {
        Energy = energy;
    }

    public void setRespect(int respect)
    {
        Respect = respect;
    }

    public void setExp(int exp)
    {
        Exp = exp;
    }

    public void setStrength(int strength)
    {
        Strength = strength;
    }

    public void setIntelligence(int intelligence)
    {
        Intelligence = intelligence;
    }

    public void setAgility(int agility)
    {
        Agility = agility;
    }

    public void setMSVal(int msval)
    {
        MSVal = msval;
    }

    public void setCashRewardGrade(int cashreward)
    {
        cashRewardGrade = cashreward;
    }

    public void setDifficulty(int difficulty)
    {
        Difficulty = difficulty;
    }

    public void setSuccess (boolean success)
    {
        Success = success;
    }

    public boolean getSuccess()
    {
        return Success;
    }

    public int difficultyCalc()
    {
        if (Exp < 100)
        {
            Difficulty = 1;
        }
        if (Exp > 100 && Exp < 250)
        {
            Difficulty = 2;
        }
        if (Exp > 250 && Exp < 750)
        {
            Difficulty = 3;
        }
        return Difficulty;
    }

    public String successCalc()
    {
        int val1 = (Respect + Exp);
        int val2 = val1 * MSVal;

        Log.i("msval=", String.valueOf(val2));

        Random generator = new Random();

        if(Difficulty == 1)
        {
            RandResult = generator.nextInt(10) + 1;
        }
        if(Difficulty == 2)
        {
            RandResult = generator.nextInt(30) + 1;
        }
        if(Difficulty == 3)
        {
            RandResult = generator.nextInt(70) + 1;
        }
        Log.i("rand", String.valueOf(RandResult));
        if(val2 < RandResult)
        {
            setSuccess(false);
            return "Akcja Nieudana!";
        }
        if(val2 > RandResult)
        {
            setSuccess(true);
            return "Akcja Udana!";
        }
        if(val2 == RandResult)
        {
            setSuccess(true);
            return "Akcja Udana!";
        }
        else
        {
            setSuccess(false);
            return "Błąd!";
        }
    }

    public int cashCalc()
    {
        Random generator2 = new Random();

        if(cashRewardGrade == 1)
        {
            RandResult2 = generator2.nextInt(20) + 1;
        }
        if(cashRewardGrade == 2)
        {
            RandResult2 = generator2.nextInt(50) + 10;
        }
        if(cashRewardGrade == 3)
        {
            RandResult2 = generator2.nextInt(100) + 20;
        }
        if(cashRewardGrade == 4)
        {
            RandResult2 = generator2.nextInt(200) + 40;
        }
        if(cashRewardGrade == 5)
        {
            RandResult2 = generator2.nextInt(300) + 80;
        }
        if(cashRewardGrade == 6)
        {
            RandResult2 = generator2.nextInt(500) + 160;
        }
        if(cashRewardGrade == 7)
        {
            RandResult2 = generator2.nextInt(750) + 320;
        }
        if(cashRewardGrade == 8)
        {
            RandResult2 = generator2.nextInt(1100)+ 640;
        }
        if(cashRewardGrade == 9)
        {
            RandResult2 = generator2.nextInt(2000) + 1280;
        }
        return RandResult2;
    }
}