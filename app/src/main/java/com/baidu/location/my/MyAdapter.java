package com.baidu.location.my;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.baidulocationdemo.R;

import java.util.List;

/**
 * Created by yly on 2019/1/1.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<MyItem> myItem;
    public MyAdapter(List<MyItem> myItem){
        this.myItem=myItem;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView itemImage;
        private TextView itemText;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage= (ImageView) itemView.findViewById(R.id.left_icon);
            itemText= (TextView) itemView.findViewById(R.id.left_title);
        }
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyItem my=myItem.get(position);
        holder.itemImage.setImageResource(my.getItemimage());
        holder.itemText.setText(my.getItemtext());
    }

    @Override
    public int getItemCount() {
        return myItem.size();
    }
}
