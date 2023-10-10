package com.example.bookreader;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class BookmarkRecyclerViewAdapter extends RecyclerView.Adapter<BookmarkRecyclerViewAdapter.ViewHolder> {



    ArrayList<Bookmark> users;


    public BookmarkRecyclerViewAdapter(ArrayList<Bookmark> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public BookmarkRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    //now you can use adapterPos to get the item in your list
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkRecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.anoid_1.setText("<책 제목>");
        holder.anoid_2.setText("<저자>");
        holder.anoid_3.setText("<전체 페이지 수>");
        holder.anoid_4.setText("<읽기 시작한 날짜>");


        String dateforrecycler;

        final int id= users.get(position).getId();
        final String title = users.get(position).getTitle();
        final String author=users.get(position).getAuthor();
        final int page = users.get(position).getPage();
        final int year = users.get(position).getYear();
        final int month = users.get(position).getMonth();
        final int day =  users.get(position).getDay();
        final int date = year*10000 + month*100 + day;
        final String textdate = date+"";



        dateforrecycler = + year+"년  " + month + "월  "+ day + "일";

        holder.bookinfo.setText(title+"");
        holder.bookinfo2.setText(author + "");
        holder.bookinfo3.setText(page + "P");
        holder.bookinfo4.setText(dateforrecycler + "");

        holder.ib_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookmarkTaskFix.class);
                intent.putExtra("fixdataid2", users.get(position).getId());
                intent.putExtra("fixdatatitle2",users.get(position).getTitle());
                intent.putExtra("fixdataauthor2",users.get(position).getAuthor());
                intent.putExtra("fixdatapage2",users.get(position).getPage());
                intent.putExtra("fixdatayear2",users.get(position).getYear());
                intent.putExtra("fixdatamonth2",users.get(position).getMonth());
                intent.putExtra("fixdataday2",users.get(position).getDay());
                v.getContext().startActivity(intent);
            }
        });


        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookmark bookmark = new Bookmark(title, author, page, year, month, day,textdate);
                bookmark.setId(id);


                // Deleting database
                MainActivity.bookmarkDatabase.bookmarkDao().deleteBook(bookmark);


                // Todo => adding this is very important otherwise it will crash when you delete an item
                notifyDataSetChanged();


                // Removing recyclerview item
                users.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position, users.size());



                Toast.makeText(v.getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();


            }
        });

        holder.start_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), BookmarkRead.class);
                intent.putExtra("readbook", users.get(position).getId());
                v.getContext().startActivity(intent);

            }
        });

        holder.add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), BookmarkRecord.class);
                intent.putExtra("recordbook", users.get(position).getId());
                v.getContext().startActivity(intent);

            }
        });




    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookinfo;
        public TextView bookinfo2;
        public TextView bookinfo3;
        public TextView bookinfo4;
        public Button ib_fix;
        public Button ib_delete;
        public Button add_record;
        public Button start_reading;
        public TextView anoid_1, anoid_2, anoid_3, anoid_4;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookinfo = (TextView) itemView.findViewById(R.id.bookmark_bookinfo);
            bookinfo2 = (TextView) itemView.findViewById(R.id.bookmark_bookinfo2);
            bookinfo3 = (TextView)itemView.findViewById(R.id.bookmark_bookinfo3);
            bookinfo4 = (TextView) itemView.findViewById(R.id.bookmark_bookinfo4);
            ib_fix = (Button) itemView.findViewById(R.id.bookmark_fix);
            ib_delete = (Button) itemView.findViewById(R.id.bookmark_delete);
            anoid_1 = (TextView) itemView.findViewById(R.id.anoid_1);
            anoid_2 = (TextView) itemView.findViewById(R.id.anoid_2);
            anoid_3 = (TextView) itemView.findViewById(R.id.anoid_3);
            anoid_4 = (TextView) itemView.findViewById(R.id.anoid_4);
            add_record = (Button) itemView.findViewById(R.id.bookmark_record);
            start_reading = (Button) itemView.findViewById(R.id.bookmark_startreading);


        }
    }



}
