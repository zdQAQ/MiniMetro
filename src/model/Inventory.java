package model;

import javafx.Controller;

public class Inventory {
    private  int lineNb = 0 ;
    private int tunnelNb = 0 ;
    private int wagonNb = 0 ;
    private int trainNb = 0 ;
    private int stationNb = 0;

    public Inventory () {

    }

    public Inventory (int train,int wagon,int line,int tunnel,  int station) {
        lineNb = line;
        tunnelNb = tunnel;
        wagonNb = wagon;
        trainNb = train;
        stationNb = station;
    }

    public void setInventory(int train,int wagon,int line,int tunnel,  int station){
        lineNb = line;
        tunnelNb = tunnel;
        wagonNb = wagon;
        trainNb = train;
        stationNb = station;
    }

    public int getTunnelNb()
    {
        return tunnelNb;
    }

    public void subTunnelNb(int t)
    {
        tunnelNb=tunnelNb-t;
    }

    public void addTunnelNb(int t)
    {
        tunnelNb=tunnelNb+t;
    }



    public int getLineNb()
    {
        return lineNb;
    }

    public void subLineNb()
    {
        --lineNb;
    }

    public void addLineNb()
    {
        ++lineNb;
    }


    public int getWagonNb()
    {
        return wagonNb;
    }

    public void subWagonNb(int t)
    {
        --wagonNb;
    }

    public void addWagonNb()
    {
        ++wagonNb;
    }
    
    public int getStationNb()
    {
        return stationNb;
    }

    public void subStationNb(int t)
    {
        --stationNb;
    }

    public void addStationNb()
    {
        ++stationNb;
    }



    public int getTrainNb()
    {
        return trainNb;
    }

    public void subTrainNb()
    {
        --trainNb;
    }

    public void addTrain()
    {
        ++trainNb;
    }

}
