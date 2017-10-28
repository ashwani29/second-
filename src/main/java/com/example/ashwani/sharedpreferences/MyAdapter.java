package com.example.ashwani.sharedpreferences;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ashwani on 25-10-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Data> dataarrayList = new ArrayList<>();
    Context context;
    public MyAdapter(Context context,  ArrayList<Data> dataarrayList1){
        this.context = context;
        this.dataarrayList = dataarrayList1;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rec, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder  = (MyViewHolder) holder;
        myViewHolder.text.setText(dataarrayList.get(position).getTitle());
        myViewHolder.text1.setText(dataarrayList.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return dataarrayList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView text1;
        public MyViewHolder(View itemView) {
            super(itemView);
             text= (TextView) itemView.findViewById(R.id.text);
             text1 = (TextView) itemView.findViewById(R.id.text1);

        }
    }


}
