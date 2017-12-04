package com.hos.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
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
import com.hos.utils.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 机构注册的Activity
 * 
 * @author Administrator
 * 
 */
public class RegisterHosActivity extends Activity implements OnClickListener {

	private EditText edName;// 姓名输入框
	private EditText edAccount;// 账户名输入框
	private EditText edPassword;// 密码输入框
	private Button btRegister;// 注册按钮
	private String userAccount;// 输入的账户名
	private String userName;// 输入的姓名
	private String userPassword;// 输入的密码
	private HttpUtils utils = Utils.getHttpUtils();// 网络请求类
	private RequestParams params = new RequestParams();// 请求参数
	private Intent intent;// 意图

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register_hos);
		edAccount = (EditText) findViewById(R.id.et_accountRegHos);
		edName = (EditText) findViewById(R.id.et_nameRegHos);
		edPassword = (EditText) findViewById(R.id.et_passwordRegHos);
		btRegister = (Button) findViewById(R.id.bt_registerRegHos);
		btRegister.setOnClickListener(this);
	}

	/**
	 * 注册按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register:
			System.out.println("注册");
//			dialog();//选择主用类型对话框
			register();
			break;

		default:
			break;
		}
	}

	// 生成选择  "注册类型" 对话框
	protected void dialog() {
		AlertDialog.Builder builder = new Builder(RegisterHosActivity.this);
		builder.setTitle("选择");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("请选择您的用户类型：");

		builder.setPositiveButton("个人", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
//				Uri uri = Uri.parse("http://www.baidu.com/");// 打开链接
//				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(intent);
				perRegister();

			}

		});

		builder.setNegativeButton("机构", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				orgRegister();

			}
		});

		builder.show();

	}
	
	// 机构注册
	private void orgRegister(){
		System.out.println("机构注册");
		intent = new Intent(RegisterHosActivity.this, SignActivity.class);//修改ia
		startActivity(intent);
	}
	// 个人注册
	private void perRegister(){
		System.out.println("个人注册");
	}

	/**
	 * 注册
	 */
	private void register() {
		userAccount = edAccount.getText().toString();
		userName = edName.getText().toString();
		userPassword = edPassword.getText().toString();
		if (TextUtils.isEmpty(userAccount) || TextUtils.isEmpty(userAccount)
				|| TextUtils.isEmpty(userPassword)) {
			Toast.makeText(getApplicationContext(), "请输入姓名，账户名，密码", 1).show();
			System.out.println("请输入姓名，账户名，密码");
			return;
		}

		String sessionId = Utils.getSP("JSESSIONID");
		System.out.println(userName + userAccount + userPassword);
		List<NameValuePair> lsit = new ArrayList<NameValuePair>();
		lsit.add(new BasicNameValuePair("userName", userName));
		lsit.add(new BasicNameValuePair("userAccount", userAccount));
		lsit.add(new BasicNameValuePair("userPassword", userPassword));
		params.addBodyParameter(lsit);
		params.addHeader("Cookie", sessionId);
		// 访问服务器端对应接口 URL
		utils.send(HttpMethod.POST, Constants.REGISTERURL, params,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						System.out.println("失败");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						String result = arg0.result;
						System.out.println("result--->" + result);
						// intent = new Intent(RegisterActivity.this,
						// LoginActivity.class);
						// startActivity(intent);
						finish();
						overridePendingTransition(0, 0);
					}
				});

	}

}
