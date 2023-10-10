package com.example.bookreader;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookmarkRead extends AppCompatActivity {

    private TextView test_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_read);

        final int readbookid = getIntent().getIntExtra("readbook", 1);

        test_id = findViewById(R.id.test_id);

        test_id.setText(readbookid + "");

    }
}
