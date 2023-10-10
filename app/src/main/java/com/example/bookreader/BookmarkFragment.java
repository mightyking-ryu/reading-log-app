package com.example.bookreader;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment {

    private ImageButton btn_addbook;
    private TextView no_book;
    private ImageView bookim;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    ArrayList<Bookmark> users;


    public BookmarkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bookmark, container, false);

        btn_addbook = (ImageButton) v.findViewById(R.id.addbookmark);

        btn_addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),BookmarkTask.class);
                startActivity(intent);
            }
        });

        // Defining variables
        no_book = (TextView) v.findViewById(R.id.no_book_bookmark);
        bookim = (ImageView) v.findViewById(R.id.no_book_bookmarkim);


        // bookmark task
        users = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.bookmark_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new BookmarkRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        // Inflate the layout for this fragment
        return v;
    }


    public void refresh_bookmark() {

        List<Bookmark> bookmark = MainActivity.bookmarkDatabase.bookmarkDao().getBookmark();

        users.clear();

        for (Bookmark bmk : bookmark) {

            int id = bmk.getId();
            String title = bmk.getTitle();
            String author = bmk.getAuthor();
            int page = bmk.getPage();
            int year = bmk.getYear();
            int month = bmk.getMonth();
            int day = bmk.getDay();

            int date = year * 10000 + month * 100 + day;

            String textdate = date + "";

            Bookmark user = new Bookmark(title, author, page, year, month, day, textdate);
            user.setId(id);

            users.add(user);

            int num_books = users.size();
            if (num_books > 0) {
                no_book.setText("");
                bookim.setVisibility(View.INVISIBLE);
            } else if (num_books == 0) {
                no_book.setText("읽고 책이 없음");
                bookim.setVisibility(View.VISIBLE);
            }


        }

        sortDateList_az();

    }


    // Todo : very very very very important to add (I was stuck for almost five hours)

    @Override
    public void onResume() {
        super.onResume();

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.bookmark_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookmarkRecyclerViewAdapter(users);
        recyclerView.setAdapter(adapter);

        refresh_bookmark();

        //update whatever your list
        adapter.notifyDataSetChanged();
    }


    private void sortDateList_az() {

        Collections.sort(users, new Comparator<Bookmark>() {
            @Override
            public int compare(Bookmark o1, Bookmark o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        adapter.notifyDataSetChanged();

    }



}
