package com.example.bookreader;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    public void addBook (History history);

    @Query("select * from history_table")
    public List<History> getHistory();

    @Delete
    public void deleteBook(History history);

    @Update
    public void updateBook(History history);

}
