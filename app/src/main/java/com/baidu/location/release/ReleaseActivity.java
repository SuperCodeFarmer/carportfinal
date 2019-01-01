package com.baidu.location.release;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.baidulocationdemo.R;
import com.baidu.location.book.BookItem;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class ReleaseActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText carportAddress;
    private EditText carportTimeout;
    private EditText bookCost1;
    private EditText carportDetail;
    private ImageView imageView_L;
    private ImageView imageView_R;
    private Uri imageUri;
    private String imageleft;
    private String imageright;
    private BookItem bookItem;
    private File outputStream;
    private TextView titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);


        //设置头布局为我的发布
        titleText= (TextView) findViewById(R.id.title_text);
        titleText.setText("我的发布");

        //初始化BooItem
        bookItem=new BookItem();

        carportAddress= (EditText) findViewById(R.id.carport_address);
        carportTimeout= (EditText) findViewById(R.id.carport_timeout);
        bookCost1= (EditText) findViewById(R.id.book_cost);
        carportDetail= (EditText) findViewById(R.id.book_detail);

        imageView_L= (ImageView) findViewById(R.id.imageview_l);//左边图片
        imageView_R= (ImageView) findViewById(R.id.imageview_r);//右边图片
        Button releaseInfo= (Button) findViewById(R.id.release_button);//发布车位

        //左边图片和右边图片的名称
        imageleft="image_left.jpg";
        imageright="image_left.jpg";

        imageView_L.setOnClickListener(this);
        imageView_R.setOnClickListener(this);
        releaseInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageview_l:
                showDialog();
                break;
            case R.id.imageview_r:
                showDialog();
                break;
            case R.id.release_button:
                LitePal.getDatabase();
                bookItem.setCarportAddress(carportAddress.getText().toString());
                bookItem.setCarportTimeout(carportTimeout.getText().toString()+"小时");
                bookItem.setCarportCost(bookCost1.getText().toString()+"元/小时");
                bookItem.setCarportDetail(carportDetail.getText().toString());
                bookItem.setCarportTime(new SimpleDateFormat("MM月dd日 HH:mm:ss")
                        .format( new java.sql.Date(System.currentTimeMillis())));
                bookItem.save();
                Toast.makeText(this,"车位已经发布，请去个人中心查看",Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }

    private void showDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        Button takePhoto = (Button) view.findViewById(R.id.take_photo);
        Button takeGallery = (Button) view.findViewById(R.id.gallay);
        //设置点击事件
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takephoto();
                dialog.cancel();
            }
        });
        takeGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                opoenGallery();
            }
        });
        dialog.show();

        //这个方法是用来设置对话框的大小的，下面的ScreenUtil里面的方法即使设置了也不会起作用
        //dialog.getWindow().setLayout(width,height);

        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(this)/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    //拍照
    private void takephoto() {
        outputStream=new File(getExternalCacheDir(),"output_image.jpg");
        try{
            if (outputStream.exists()){
                outputStream.delete();
            }
            outputStream.createNewFile();
        }catch(Exception e){
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT>=24){
            imageUri = FileProvider.getUriForFile(ReleaseActivity.this,
                    "com.example.cameraalbumtest.fileprovider", outputStream);
        }else{
            imageUri=Uri.fromFile(outputStream);
        }
        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);

        Log.e("ReleaseActivity",outputStream.getAbsolutePath()+"yly");
        startActivityForResult(intent,0);
    }
    //从相册中选择
    public void opoenGallery(){
        if (ContextCompat.checkSelfPermission(ReleaseActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ReleaseActivity.this, new String[]{ Manifest.permission. WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            openAlbum();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image");
        startActivityForResult(intent, 1); // 打开相册
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                if (resultCode==RESULT_OK){
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        if (imageView_L.getDrawable()==null){
                            imageView_L.setImageBitmap(bitmap);
                        }else if (imageView_R.getDrawable()==null){
                            imageView_R.setImageBitmap(bitmap);
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            if (imageView_L.getDrawable()==null){
                imageView_L.setImageBitmap(bitmap);
            }else if (imageView_R.getDrawable()==null){
                imageView_R.setImageBitmap(bitmap);
            }
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }


    static class ScreenUtils{
        //设置对话框的告诉占整个屏幕的几分之几
        public static int getScreenHeight(Context context) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        //设置对话框的宽度占整个屏幕的几分之几
        public static int getScreenWidth(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
    }
}
