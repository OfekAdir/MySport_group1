package com.example.firebase_example;

import java.util.Calendar;
import java.util.Date;

public class Group {
    private String creator;
    private String groupDate;
    private int maxPlayersNum;
    private String sportType;
    private int countPlayer=0;

    public Group(){}

    public String getCreator() {
        return creator;
    }

    public String getGroupDate() {
        return groupDate;
    }

    public int getMaxPlayersNum() {
        return maxPlayersNum;
    }

    public String getSportType() {
        return sportType;
    }

    public Group(String creator, String groupDate, int maxPlayersNum, String sportType) {
        this.creator = creator;
        this.groupDate = groupDate;
        this.maxPlayersNum = maxPlayersNum;
        this.sportType = sportType;
    }

    public void setCountPlayer()
    {
        if (countPlayer<maxPlayersNum)
        countPlayer++;
    }
}

