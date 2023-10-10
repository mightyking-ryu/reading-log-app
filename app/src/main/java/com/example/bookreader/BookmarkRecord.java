package com.example.bookreader;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BookmarkRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_record);

        final int recordbookid = getIntent().getIntExtra("recordbook", 1);

    }
}
