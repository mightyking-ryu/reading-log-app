package com.example.bookreader;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class BookmarkTask extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    // Defining variables
    private Button BnYear, BnMonth, BnDay, BnSave, BnCancel;
    private EditText BookmarkTitle, BookmarkAuthor, BookmarkPage;


    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_task);



        // Initializing variables
        BnYear = (Button) findViewById(R.id.task_bookmark_year);
        BnMonth = (Button) findViewById(R.id.task_bookmark_month);
        BnDay = (Button) findViewById(R.id.task_bookmark_day);
        BnCancel = (Button) findViewById(R.id.btn_bookmark_cancel);
        BnSave = (Button) findViewById(R.id.btn_bookmark_save);
        BookmarkTitle = (EditText) findViewById(R.id.bookmark_task_title);
        BookmarkAuthor = (EditText) findViewById(R.id.bookmark_task_author);
        BookmarkPage = (EditText) findViewById(R.id.bookmark_task_page);


        // Creating date picker
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        BnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookmarkTask.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
                datePickerDialog.show();
            }
        });

        BnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookmarkTask.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
                datePickerDialog.show();
            }
        });

        BnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookmarkTask.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
                datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date_year = year + "";
                String date_month = month + "";
                String date_day = dayOfMonth + "";

                BnYear.setText(date_year);
                BnMonth.setText(date_month);
                BnDay.setText(date_day);

            }
        };

        // Cancel button
        BnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Save button
        BnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String nullcheck = BnYear.getText().toString();
                String bookmarkpagestring = BookmarkPage.getText().toString();

                if(nullcheck.trim().length()<=0 || bookmarkpagestring.trim().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "내용을 다 입력하세요", Toast.LENGTH_SHORT).show();
                }else {
                    String bookmarktitle = BookmarkTitle.getText().toString();
                    String bookmarkauthor = BookmarkAuthor.getText().toString();
                    int bookmarkpage = Integer.parseInt(BookmarkPage.getText().toString());
                    int bookmarkyear = Integer.parseInt(BnYear.getText().toString());
                    int bookmarkmonth = Integer.parseInt(BnMonth.getText().toString());
                    int bookmarkday = Integer.parseInt(BnDay.getText().toString());
                    int id;
                    List<Bookmark> bookmarklist = MainActivity.bookmarkDatabase.bookmarkDao().getBookmark();
                    id = bookmarklist.size() + 1;

                    if (bookmarktitle.trim().length() <= 0 || bookmarkauthor.trim().length() <= 0 || bookmarkpage <= 0) {

                        Toast.makeText(getApplicationContext(), "내용을 다 입력하세요", Toast.LENGTH_SHORT).show();

                    } else if(id <= 4){
                        ArrayList<Integer> id_list = new ArrayList<>();

                        id_list.add(0, 1);
                        id_list.add(1, 2);
                        id_list.add(2, 3);
                        id_list.add(3, 4);

                        ArrayList<Boolean> index_check = new ArrayList<>();

                        index_check.add(0, true);
                        index_check.add(1, true);
                        index_check.add(2, true);
                        index_check.add(3, true);

                        List<Bookmark> bookmark_check = MainActivity.bookmarkDatabase.bookmarkDao().getBookmark();


                        for (Bookmark bmk : bookmark_check) {

                            int id_c = bmk.getId();

                            if(id_c==1 || id_c==2 || id_c==3 || id_c==4) {
                                index_check.set(id_c-1, false);
                            }


                        }


                        ArrayList<Integer> id_list_left = new ArrayList<>();

                        for (int i = 0; i < 4; i++) {

                            if(index_check.get(i)==true) {
                                id_list_left.add(id_list.get(i));
                            }

                        }


                        Random rand = new Random();
                        final int id_db = id_list_left.get(rand.nextInt(id_list_left.size()));

                        int bookmarkdate = bookmarkyear*10000 +bookmarkmonth*100 + bookmarkday;
                        String textbookmarkdate = bookmarkdate + "";
                        Bookmark bookmark = new Bookmark(bookmarktitle, bookmarkauthor, bookmarkpage, bookmarkyear, bookmarkmonth, bookmarkday, textbookmarkdate);
                        bookmark.setId(id_db);

                        MainActivity.bookmarkDatabase.bookmarkDao().addBook(bookmark);


                        Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                        thread.start();

                        finish();

                    } else if(id > 4){

                        Toast.makeText(getApplicationContext(), "북마크 기능은 최대 4권까지 이용하실 수 있습니다", Toast.LENGTH_LONG).show();
                        thread.start();

                        finish();

                    }
                }
            }
        });



    }



    // Need this method to make a thread for toast messages
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(Toast.LENGTH_SHORT); // As I am using LENGTH_LONG in Toast
                BookmarkTask.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };










    // date pick method 2 (but this date picker method had some problems in galaxy s10)

    private void showDatePickerDailog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String year_string = year + "";
        BnYear.setText(year_string);
        String month_string = month+1 + "";
        BnMonth.setText(month_string);
        String day_string = dayOfMonth + "";
        BnDay.setText(day_string);
    }
}