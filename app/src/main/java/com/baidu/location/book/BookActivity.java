package com.baidu.location.book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.baidulocationdemo.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {
    private List<BookItem> listBook=new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        titleText=findViewById(R.id.title_text);
        titleText.setText("车位预定");
        textView1= (TextView) findViewById(R.id.text_view_1);
        textView2= (TextView) findViewById(R.id.text_view_2);
        textView3= (TextView) findViewById(R.id.text_view_3);
        textView4= (TextView) findViewById(R.id.text_view_4);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        recyclerView= findViewById(R.id.book_recyclerview);
        LinearLayoutManager linear=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linear);
        BookAdapter bookAdapter=new BookAdapter(listBook);
        recyclerView.setAdapter(bookAdapter);
    }
    public void initData(){
        List<BookItem> list= DataSupport.findAll(BookItem.class);
        for (BookItem bookitem:list){
            listBook.add(bookitem);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_view_1:
                Toast.makeText(BookActivity.this,"筛选功能正在实现中，请绕道",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_view_2:
                Toast.makeText(BookActivity.this,"筛选功能正在实现中，请绕道",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_view_3:
                Toast.makeText(BookActivity.this,"筛选功能正在实现中，请绕道",Toast.LENGTH_LONG).show();
                break;
            case R.id.text_view_4:
                Toast.makeText(BookActivity.this,"筛选功能正在实现中，请绕道",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
