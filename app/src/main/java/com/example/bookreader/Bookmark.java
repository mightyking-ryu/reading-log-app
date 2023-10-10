package com.example.bookreader;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmark_table")
public class Bookmark {

    public Bookmark(String title, String author, int page, int year, int month, int day, String date) {
        this.title = title;
        this.author = author;
        this.page = page;
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = date;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "bookmark_title")
    private String title;

    @ColumnInfo(name = "bookmark_author")
    private String author;

    @ColumnInfo(name = "bookmark_page")
    private int page;

    @ColumnInfo(name = "bookmark_year")
    private int year;

    @ColumnInfo(name = "bookmark_month")
    private int month;

    @ColumnInfo(name = "bookmark_day")
    private int day;

    @ColumnInfo(name = "bookmark_date")
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

