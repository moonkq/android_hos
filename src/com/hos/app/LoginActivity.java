package com.hos.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hos.R;
import com.hos.constant.Constants;
import com.hos.utils.SessionManager;
import com.hos.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 登录的Activity
 */
public class LoginActivity extends Activity implements OnClickListener {

	private EditText edAccount = null;// 账号输入框
	private EditText edPassword = null;// 密码输入框
	private Button btRegister = null;// 注册按钮
	private Button btLogin = null;// 登录按钮
	private String userAccount = null;// 用户账号
	private String userPassword = null;// 用户密码
	private HttpUtils utils = Utils.getHttpUtils();// 网络请求对象
	private RequestParams params = null;// 请求参数
	private Intent intent = null;// 意图
	private DefaultHttpClient mDh = null;//
	private CookieStore mCookieStore = null;// cookie的存储策略类
	private List<Cookie> mCookies = null;// cookie 列表
	private String mSessionId = "";// Session的ID

	private SessionManager sessionManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		edAccount = (EditText) findViewById(R.id.et_account);
		edPassword = (EditText) findViewById(R.id.et_password);
		btRegister = (Button) findViewById(R.id.bt_register);
		btLogin = (Button) findViewById(R.id.bt_login);
		btLogin.setOnClickListener(this);// 设置点击事件
		btRegister.setOnClickListener(this);// 设置点击事件

		sessionManager = new SessionManager(getApplicationContext());// 登陆状态管理
		// 如果用户没有登陆,那么跳转到登陆页面
		if (sessionManager.isLoggedIn()) {
			Intent intent = new Intent(LoginActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		}
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register:// 点击注册
			dialog();// 弹出对话框，选择是"个人注册"、"机构注册"
			// intent = new Intent(this, RegisterActivity.class);
			// startActivity(intent);
			overridePendingTransition(0, 0);
			break;
		case R.id.bt_login:// 点击登录
			login();

			// 调试 跳转到 主Activity
			// intent = new Intent(LoginActivity.this, MapActivity.class);//
			// 调试跳转到高德 地图

			// intent = new Intent(LoginActivity.this, MainActivity.class);
			// startActivity(intent);

			break;

		default:
			break;
		}
	}

	/**
	 * 登录
	 */
	private void login() {
		userAccount = edAccount.getText().toString();
		userPassword = edPassword.getText().toString();
		if (TextUtils.isEmpty(userAccount) || TextUtils.isEmpty(userPassword)) {
			// 账号或密码为空时的提示信息
			Toast.makeText(Utils.getContext(), "用户账号或密码不能为空", Toast.LENGTH_LONG).show();
			return;
		}

		Toast.makeText(Utils.getContext(),
				userAccount + "用户账号密码" + userPassword, Toast.LENGTH_LONG).show();

		if (userAccount.equals("123") && userPassword.equals("456")) {
			Toast.makeText(Utils.getContext(), "登录成功", Toast.LENGTH_LONG).show();
			// 从登录Activity跳转到MainActivity
			intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(Utils.getContext(), "用户名或密码错误,请重新输入", Toast.LENGTH_LONG).show();
		}
		
		
		
		
		
//TODO:网络请求服务器端URL
		// 设置POST请求的参数
		/*
		 * params = new RequestParams(); List<NameValuePair> lsit = new
		 * ArrayList<NameValuePair>(); lsit.add(new
		 * BasicNameValuePair("userAccount", userAccount)); lsit.add(new
		 * BasicNameValuePair("userPassword", userPassword));
		 * params.addBodyParameter(lsit); utils.send(HttpMethod.POST,
		 * Constants.LOGINURL, params,// 请求URL,及传递的参数 new
		 * RequestCallBack<String>() {
		 * 
		 * @Override public void onFailure(HttpException arg0, String arg1) { //
		 * 请求失败后的回调 System.out.println("失败"); }
		 * 
		 * @Override public void onSuccess(ResponseInfo<String> arg0) { //
		 * 请求成功后的回调 String result = arg0.result; mDh = (DefaultHttpClient)
		 * utils.getHttpClient(); mCookieStore = mDh.getCookieStore(); mCookies
		 * = mCookieStore.getCookies(); mSessionId = ""; for (int i = 0; i <
		 * mCookies.size(); i++) { if
		 * ("JSESSIONID".equals(mCookies.get(i).getName())) { mSessionId =
		 * mCookies.get(i).getValue(); System.out.println("login" + mSessionId);
		 * Utils.putSP("JSESSIONID", mSessionId); } }
		 * 
		 * System.out.println("result--->" + result); if (!"-1".equals(result))
		 * { System.out.println("登录成功"); Toast.makeText(Utils.getContext(),
		 * "登录成功", 0) .show(); finish(); } else {
		 * Toast.makeText(Utils.getContext(), "登录失败", 0) .show(); } } });
		 */
	}

	// 生成选择 "注册类型" 对话框
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(LoginActivity.this);
		builder.setTitle("选择");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("请选择您的用户类型：");

		builder.setPositiveButton("个人", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// perRegister();
				intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
				overridePendingTransition(0, 0);

			}

		});

		builder.setNegativeButton("机构", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				// orgRegister();
				intent = new Intent(LoginActivity.this,
						RegisterHosActivity.class);// 修改ia
				startActivity(intent);

			}
		});

		builder.show();

	}
}
