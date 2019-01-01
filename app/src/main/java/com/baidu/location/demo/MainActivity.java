package com.baidu.location.demo;

import java.util.ArrayList;
import java.util.List;

import com.baidu.baidulocationdemo.R;
import com.baidu.mapapi.map.MapView;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/***
 * 本类代码同定位业务本身无关，负责现实列表
 * 
 * @author baidu
 *
 */
public class MainActivity extends Activity implements View.OnClickListener {
	private final int SDK_PERMISSION_REQUEST = 127;
	//private ListView FunctionList;
	private String permissionInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("MainActivity","yly------1");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.e("MainActivity","yly------2");
		getPersimmions();

		//login button
		final Button login = (Button) findViewById(R.id.button);
		final String user = "admin";
		final String pass = "admin";



		login.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//从输入框输入用户名
				String username = "";
				EditText editText1 = (EditText) findViewById(R.id.editText);
				username = editText1.getText().toString();
				//从输入框获取密码
				String password = "";
				EditText editText2 = (EditText) findViewById(R.id.editText2);
				password = editText2.getText().toString();

				if (username.equals(user) & password.equals(pass)) {
					Intent intent = new Intent(MainActivity.this,LocationFilter.class);
					startActivity(intent);
				} else {
					new AlertDialog.Builder(MainActivity.this).setTitle("Error!").setMessage("Wrong username or password.")
							.setNegativeButton("OK", null)
							.show();
				}
			}
		});
		//register button
		final Button register = (Button) findViewById(R.id.button2);
		register.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//提示框确定是否跳转
				new AlertDialog.Builder(MainActivity.this).setTitle("注册").setMessage("接下来您将进入注册界面?")
						.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								Intent intent = new Intent(MainActivity.this,MainActivity.class);
								startActivity(intent);
							}
						})
						.setNegativeButton("No", null)
						.show();
			}
		});

	}


	@TargetApi(23)
	private void getPersimmions() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			ArrayList<String> permissions = new ArrayList<String>();
			/***
			 * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
			 */
			// 定位精确位置
			if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
				permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
			}
			if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
				permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
			}
			/*
			 * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
			// 读写权限
			if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
				permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
			}
			// 读取电话状态权限
			if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
				permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
			}
			
			if (permissions.size() > 0) {
				requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
			}
		}
	}


	@TargetApi(23)
	private boolean addPermission(ArrayList<String> permissionsList, String permission) {
		if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请	
			if (shouldShowRequestPermissionRationale(permission)){
				return true;
			}else{
				permissionsList.add(permission);
				return false;
			}
				
		}else{
			return true;
		}
	}


	@TargetApi(23)
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		
	}

	@Override
	public void onClick(View v) {

	}
}
