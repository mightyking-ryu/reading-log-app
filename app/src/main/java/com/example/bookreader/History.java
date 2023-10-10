package com.example.bookreader;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_table")
public class History {

    public History(String title, String author, int year, int month, int day, String date) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.month = month;
        this.day = day;
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "history_title")
    private String title;

    @ColumnInfo(name = "history_author")
    private String author;

    @ColumnInfo(name = "history_year")
    private int year;

    @ColumnInfo(name = "history_month")
    private int month;

    @ColumnInfo(name = "history_day")
    private int day;

    @ColumnInfo(name = "history_date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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
}
