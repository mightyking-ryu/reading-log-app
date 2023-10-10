package com.example.bookreader;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {



    ArrayList<History> users;


    public HistoryRecyclerViewAdapter(ArrayList<History> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public HistoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
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
    public void onBindViewHolder(@NonNull HistoryRecyclerViewAdapter.ViewHolder holder, final int position) {



        String dateforrecycler;

        final int id= users.get(position).getId();
        final String title = users.get(position).getTitle();
        final String author=users.get(position).getAuthor();
        final int year = users.get(position).getYear();
        final int month = users.get(position).getMonth();
        final int day =  users.get(position).getDay();
        final int date = year*10000 + month*100 + day;
        final String textdate = date+"";



        dateforrecycler = + year+"년  " + month + "월  "+ day + "일";

        holder.bookinfo.setText(title);
        holder.bookinfo2.setText("- " + author + " -");
        holder.bookdate.setText(dateforrecycler);


        holder.ib_fix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HistoryTaskFix.class);
                intent.putExtra("fixdataid", users.get(position).getId());
                intent.putExtra("fixdatatitle",users.get(position).getTitle());
                intent.putExtra("fixdataauthor",users.get(position).getAuthor());
                intent.putExtra("fixdatayear",users.get(position).getYear());
                intent.putExtra("fixdatamonth",users.get(position).getMonth());
                intent.putExtra("fixdataday",users.get(position).getDay());
                v.getContext().startActivity(intent);
            }
        });


        holder.ib_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History history = new History(title, author, year, month, day,textdate);
                history.setId(id);


                // Deleting database
                MainActivity.historyDatabase.historyDao().deleteBook(history);
                // Todo => adding this is very important otherwise it will crash when you delete an item
                notifyDataSetChanged();


                // Removing recyclerview item
                users.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position, users.size());



                Toast.makeText(v.getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();


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
        public TextView bookdate;
        public ImageButton ib_fix;
        public ImageButton ib_delete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookinfo = (TextView) itemView.findViewById(R.id.history_bookinfo);
            bookinfo2 = (TextView) itemView.findViewById(R.id.history_bookinfo2);
            bookdate = (TextView)itemView.findViewById(R.id.history_bookdate);
            ib_fix = (ImageButton) itemView.findViewById(R.id.history_fix);
            ib_delete = (ImageButton) itemView.findViewById(R.id.history_delete);

        }
    }



}
