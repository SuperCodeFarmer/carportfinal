package com.baidu.location.demo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.baidulocationdemo.R;

public class Carport_Details extends AppCompatActivity implements View.OnClickListener {
    private Button submit;
    private TextView carportDetailCost;
    private TextView carportDetailAddress;
    private TextView carportDetailTimeout;
    private TextView carportDetailTime;
    private TextView carportDetai;

    private ImageView carportImageDetail_1;
    private ImageView carportImageDetail_2;
    private ImageView carportImageDetail_3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carport__details);
        //标题栏
        TextView title= (TextView) findViewById(R.id.title_text);
        title.setText("订单详情");

        carportDetailAddress= (TextView) findViewById(R.id.carport_detail_address);
        carportDetailCost= (TextView) findViewById(R.id.carport_detail_cost);
        carportDetailTime= (TextView) findViewById(R.id.carport_detail_time);
        carportDetailTimeout= (TextView) findViewById(R.id.carport_detail_timeout);
        carportDetai= (TextView) findViewById(R.id.carport_detail_detai);

        carportImageDetail_1= (ImageView) findViewById(R.id.image_detail_1);
        carportImageDetail_2= (ImageView) findViewById(R.id.image_detail_2);
        carportImageDetail_3= (ImageView) findViewById(R.id.image_detail_3);

        Intent intent=getIntent();
        carportDetailAddress.setText(intent.getStringExtra("carportAddress"));
        carportDetailCost.setText(intent.getStringExtra("carportCost"));
        carportDetailTimeout.setText(intent.getStringExtra("carportTimeout"));
        carportDetailTime.setText(intent.getStringExtra("carportTime"));
        carportDetai.setText(intent.getStringExtra("carportDetai"));

        carportImageDetail_1.setImageBitmap((Bitmap) intent.getParcelableExtra("carportimage"));
        submit= (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit_button:
                Toast.makeText(this,"订单已经提交，请前往个人中心查看订单详情",Toast.LENGTH_LONG).show();;
                break;
        }
    }
}
