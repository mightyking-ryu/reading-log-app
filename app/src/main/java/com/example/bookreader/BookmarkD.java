package com.example.bookreader;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class BookmarkD {

    public BookmarkD(int id, int year, int month, int day, int todaypage, int recentpage) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.todaypage = todaypage;
        this.recentpage = recentpage;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo
    private int year;

    @ColumnInfo
    private int month;

    @ColumnInfo
    private int day;

    @ColumnInfo
    private int todaypage;

    @ColumnInfo
    private int recentpage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTodaypage() {
        return todaypage;
    }

    public void setTodaypage(int todaypage) {
        this.todaypage = todaypage;
    }

    public int getRecentpage() {
        return recentpage;
    }

    public void setRecentpage(int recentpage) {
        this.recentpage = recentpage;
    }
}