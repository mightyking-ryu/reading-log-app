package com.example.bookreader;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert
    public void addBook (Bookmark bookmark);

    @Query("select * from bookmark_table")
    public List<Bookmark> getBookmark();

    @Delete
    public void deleteBook(Bookmark bookmark);

    @Update
    public void updateBook(Bookmark bookmark);

}