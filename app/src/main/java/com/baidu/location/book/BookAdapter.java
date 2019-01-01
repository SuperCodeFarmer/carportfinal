package com.baidu.location.book;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.baidulocationdemo.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yly on 2018/12/31.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<BookItem> bookItemsList;

    public BookAdapter(List<BookItem> bookItemsList) {
        this.bookItemsList = bookItemsList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView bookCarport_image;
        private TextView bookCarport_address;
        private TextView bookCarport_timeout;
        private TextView bookCarport_releasetime;
        private TextView bookCarport_cost;
        private TextView BookCarport_detai;
        private View carportView;
        public ViewHolder(View itemView) {
            super(itemView);
            bookCarport_image= (ImageView) itemView.findViewById(R.id.book_carport_image);
            bookCarport_address= (TextView) itemView.findViewById(R.id.book_carport_address);
            bookCarport_timeout= (TextView) itemView.findViewById(R.id.book_carport_timeout);
            bookCarport_releasetime= (TextView) itemView.findViewById(R.id.release_time);
            bookCarport_cost= (TextView) itemView.findViewById(R.id.book_carport_cost);
            BookCarport_detai= (TextView) itemView.findViewById(R.id.release_detai);
            carportView=itemView;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final BookItem bookItem=bookItemsList.get(position);
        holder.bookCarport_address.setText(bookItem.getCarportAddress());
        holder.bookCarport_timeout.setText("出租时长："+bookItem.getCarportTimeout());
        // ("yyyy年MM月dd日   HH:mm:ss");
        holder.bookCarport_releasetime.setText("发布时间："+new SimpleDateFormat("MM月dd日 HH:mm:ss")
                .format( new Date(System.currentTimeMillis())));
        holder.bookCarport_cost.setText(bookItem.getCarportCost());
        holder.BookCarport_detai.setText(bookItem.getCarportDetail());
        //条目点击事件
        holder.carportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),BookActivity.class);
                intent.putExtra("carportAddress",holder.bookCarport_address.getText());
                intent.putExtra("carportTimeout",holder.bookCarport_timeout.getText());
                intent.putExtra("carportTime",holder.bookCarport_releasetime.getText());
                intent.putExtra("carportCost",holder.bookCarport_cost.getText());
                intent.putExtra("carportDetai",holder.BookCarport_detai.getText());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookItemsList.size();
    }

}
