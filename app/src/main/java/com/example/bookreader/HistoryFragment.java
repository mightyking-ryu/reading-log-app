package com.example.bookreader;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private ImageButton btn_addhistory;
    public TextView no_book;
    private Spinner sort_spinner;
    private ImageView bookim;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<History> users;


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }


    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    private void sortTitleList() {

        Collections.sort(users, new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        adapter.notifyDataSetChanged();

    }


    private void sortAuthorList() {

        Collections.sort(users, new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });

        adapter.notifyDataSetChanged();

    }

    private void sortDateList_za() {

        Collections.sort(users, new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        adapter.notifyDataSetChanged();

    }

    private void sortDateList_az() {

        Collections.sort(users, new Comparator<History>() {
            @Override
            public int compare(History o1, History o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        adapter.notifyDataSetChanged();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_history, container, false);


        btn_addhistory = (ImageButton) v.findViewById(R.id.addhistory);
        no_book = (TextView) v.findViewById(R.id.no_book_history);
        sort_spinner = (Spinner) v.findViewById(R.id.spinner_sort);
        bookim = (ImageView) v.findViewById(R.id.history_bookim);

        // sort menu
        sort_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    sortDateList_za();
                } else if(position==1) {
                    sortDateList_az();
                } else if(position==2) {
                    sortTitleList();
                } else if(position==3) {
                    sortAuthorList();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        // history task
        users = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);


        btn_addhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), HistoryTask.class);
                startActivity(intent);
            }
        });



        refresh_history();


        // Inflate the layout for this fragment
        return v;
    }


    public void refresh_history() {

        List<History> history = MainActivity.historyDatabase.historyDao().getHistory();

        users.clear();

        for (History his : history) {

            int id = his.getId();
            String title = his.getTitle();
            String author = his.getAuthor();
            int year = his.getYear();
            int month = his.getMonth();
            int day = his.getDay();

            int date = year * 10000 + month * 100 + day;

            String textdate = date + "";

            History user = new History(title, author, year, month, day, textdate);

            user.setId(id);

            users.add(user);

                int num_books = users.size();
                if (num_books > 0) {
                    no_book.setText("");
                    bookim.setVisibility(View.INVISIBLE);
                } else if (num_books == 0) {
                    no_book.setText("읽은 책이 없음");
                    bookim.setVisibility(View.VISIBLE);
                }


        }

        sortDateList_za();


    }


    // Todo : very very very very important to add (I was stuck for almost five hours)

    @Override
    public void onResume() {
        super.onResume();

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoryRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);

        refresh_history();

        sort_spinner.setSelection(0);
        //update whatever your list
        adapter.notifyDataSetChanged();
    }


}
