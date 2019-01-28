package com.example.kim_seok_min.iasa_official.fragment.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kim_seok_min.iasa_official.R;

import java.util.List;


public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyRecyclerHolder> {
    private LayoutInflater inflater;
    private List<Information> list;
    private int index;
    RecyclerAdapter2(Context context, List<Information> list, int index) {
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.index = index;
        Log.e("인덱스", Integer.toString(index));
    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyRecyclerHolder(inflater.inflate(R.layout.cardview_02, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyRecyclerHolder holder, int position) {
        holder.time.setText(list.get(position).getTime());
        holder.post.setText(list.get(position).getPost());
        if(position==index && position != 8){
            int color = holder.cardView.getContext().getResources().getColor(R.color.colorPrimaryDark);
            int color2 = holder.time.getContext().getResources().getColor(R.color.white);
            int color3 = holder.time.getContext().getResources().getColor(R.color.darkblue);
            holder.cardView.setCardBackgroundColor(color);
            holder.time.setTextColor(color2);
            holder.post.setTextColor(color3);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyRecyclerHolder extends RecyclerView.ViewHolder {
        private TextView post, time;
        private CardView cardView;

        public MyRecyclerHolder(View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.title2);
            time = (TextView) itemView.findViewById(R.id.contents2);
            cardView = (CardView) itemView.findViewById(R.id.cardview2);
        }
    }
}