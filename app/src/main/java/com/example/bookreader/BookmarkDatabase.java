package com.example.bookreader;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Bookmark.class}, version = 1, exportSchema = false)
public abstract class BookmarkDatabase extends RoomDatabase {

    public abstract BookmarkDao bookmarkDao();

}

