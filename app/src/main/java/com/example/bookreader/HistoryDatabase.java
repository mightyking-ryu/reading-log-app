package com.example.bookreader;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {History.class}, version = 1, exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();

}
