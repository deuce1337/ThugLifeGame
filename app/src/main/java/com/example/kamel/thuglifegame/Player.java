package com.example.kamel.thuglifegame;

/**
 * Created by Kamel on 2016-05-13.
 */
public class Player {

    private String Username;

    private double Cash;
    private double Bank;

    private int Energy;
    private int Level;
    private int High;
    private int Exp;
    private int Strength;
    private int Inteligence;
    private int Agility;
    private int Respect;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public double getCash() {
        return Cash;
    }

    public void setCash(double cash) {
        Cash = cash;
    }

    public void addCash(double cash) {
        Cash += cash;
    }

    public void minusCash(double cash) {
        Cash -= cash;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getHigh() {
        return High;
    }

    public void setHigh(int high) {
        High = high;
    }

    public int getExp() {
        return Exp;
    }

    public void setExp(int exp) {
        Exp = exp;
    }

    public void addExp(int exp)
    {
        Exp = exp;
    }

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public int getInteligence() {
        return Inteligence;
    }

    public void setInteligence(int inteligence) {
        Inteligence = inteligence;
    }

    public int getAgility() {
        return Agility;
    }

    public void setAgility(int agility) {
        Agility = agility;
    }

    public int getRespect() {
        return Respect;
    }

    public void setRespect(int respect) {
        Respect = respect;
    }

    public double getBank() {
        return Bank;
    }

    public void setBank(double bank) {
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




}
