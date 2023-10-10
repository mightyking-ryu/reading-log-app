package com.example.bookreader;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HistoryTaskFix extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    private EditText HistoryTitle, HistoryAuthor;
    private Button BnYear, BnMonth, BnDay, BnSave;

    Button button_finish;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task_fix);

        final int fixdataid = getIntent().getIntExtra("fixdataid", 1);
        String fixdatatitle = getIntent().getStringExtra("fixdatatitle");
        String fixdataauthor = getIntent().getStringExtra("fixdataauthor");
        int fixdatayear = getIntent().getIntExtra("fixdatayear",1);
        int fixdatamonth = getIntent().getIntExtra("fixdatamonth",1);
        int fixdataday = getIntent().getIntExtra("fixdataday",1);


        button_finish = (Button) findViewById(R.id.btn_task_cancel);
        HistoryTitle = (EditText) findViewById(R.id.task_booktitle);
        HistoryAuthor = (EditText) findViewById(R.id.task_bookauthor);
        BnYear = (Button) findViewById(R.id.task_history_year);
        BnMonth = (Button) findViewById(R.id.task_history_month);
        BnDay = (Button) findViewById(R.id.task_history_day);
        BnSave = (Button) findViewById(R.id.btn_task_save);

        HistoryTitle.setText(fixdatatitle);
        HistoryAuthor.setText(fixdataauthor);
        BnYear.setText(fixdatayear+"");
        BnMonth.setText(fixdatamonth+"");
        BnDay.setText(fixdataday+"");

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        BnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryTaskFix.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
                datePickerDialog.show();
            }
        });

        BnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryTaskFix.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
                datePickerDialog.show();
            }
        });

        BnDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(HistoryTaskFix.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, setListener, year, month, day);
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

        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        BnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nullcheck = BnYear.getText().toString();

                if(nullcheck.trim().length()<=0) {
                    Toast.makeText(getApplicationContext(), "내용을 다 입력하세요", Toast.LENGTH_SHORT).show();
                }else {
                    String historytitle = HistoryTitle.getText().toString();
                    String historyauthor = HistoryAuthor.getText().toString();
                    int historyyear = Integer.parseInt(BnYear.getText().toString());
                    int historymonth = Integer.parseInt(BnMonth.getText().toString());
                    int historyday = Integer.parseInt(BnDay.getText().toString());
                    int historydate = historyyear*10000 + historymonth*100 + historyday;
                    String texthistorydate = historydate + "";

                    if (historytitle.trim().length() <= 0 || historyauthor.trim().length() <= 0) {

                        Toast.makeText(getApplicationContext(), "내용을 다 입력하세요", Toast.LENGTH_SHORT).show();

                    } else {
                        History history = new History(historytitle, historyauthor, historyyear, historymonth, historyday,texthistorydate);
                        history.setId(fixdataid);
                        history.setTitle(historytitle);
                        history.setAuthor(historyauthor);
                        history.setYear(historyyear);
                        history.setMonth(historymonth);
                        history.setDay(historyday);
                        history.setDate(texthistorydate);

                        MainActivity.historyDatabase.historyDao().updateBook(history);

                        Toast.makeText(getApplicationContext(), "수정되었습니다", Toast.LENGTH_SHORT).show();
                        thread.start();

                        Intent returnIntent = new Intent();

                        returnIntent.putExtra("endingcode",1001);


                        finish();
                    }
                }


            }
        });



    }








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

    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(Toast.LENGTH_SHORT); // As I am using LENGTH_LONG in Toast
                HistoryTaskFix.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
