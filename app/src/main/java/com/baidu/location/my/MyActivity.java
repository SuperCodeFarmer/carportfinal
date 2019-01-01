package com.baidu.location.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.baidulocationdemo.R;
import com.baidu.location.book.BookActivity;
import com.baidu.location.release.ReleaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mHBack;
    private ImageView mHHead;
    List<MyItem> listItem=new ArrayList<>();
    private TextView titleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Log.e("MyActivity","yly");
        titleText=findViewById(R.id.title_text);
        titleText.setText("个人中心");
        LinearLayout book= (LinearLayout) findViewById(R.id.book);
        LinearLayout release= (LinearLayout) findViewById(R.id.release);
        LinearLayout my= (LinearLayout) findViewById(R.id.my);
        //分别实现预定，发布，我的这三个Activity的跳转
        book.setOnClickListener(this);
        release.setOnClickListener(this);
        my.setOnClickListener(this);
        //logIn.setOnClickListener(this);


        mHBack= (ImageView) findViewById(R.id.h_back);
        mHHead= (ImageView) findViewById(R.id.h_head);
        //设置背景磨砂效果
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(mHBack);
        //设置圆形图像
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mHHead);

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        initData();
        LinearLayoutManager linear=new LinearLayoutManager(this);
        MyAdapter myAdapter=new MyAdapter(listItem);
        recyclerView.setLayoutManager(linear);
        recyclerView.setAdapter(myAdapter);
    }

    public void initData(){
        MyItem item1=new MyItem(R.drawable.ic_pass,"我的发布");
        listItem.add(item1);
        MyItem item2=new MyItem(R.drawable.ic_pass,"我的预定");
        listItem.add(item2);
        MyItem item3=new MyItem(R.drawable.ic_pass,"我的钱包");
        listItem.add(item3);
        MyItem item4=new MyItem(R.drawable.ic_pass,"我的服务");
        listItem.add(item4);
        MyItem item5=new MyItem(R.drawable.ic_pass,"我的设置");
        listItem.add(item5);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.book:
                Intent intentBook=new Intent(this,BookActivity.class);
                startActivity(intentBook);
                break;
            case R.id.release:
                Intent intentRelease=new Intent(this,ReleaseActivity.class);
                startActivity(intentRelease);
                break;
            case R.id.my:
                Intent intentMy=new Intent(this,MyActivity.class);
                startActivity(intentMy);
                break;
        /*    case R.id.login:
                    Intent intent=new Intent(this,LogInActivity.class);
                    startActivity(intent);
                break;*/
            default:
        }
    }
}
