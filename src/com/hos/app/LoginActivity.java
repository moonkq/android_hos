package com.hos.app;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
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
 * 登录的Activity
 * 
 * @author Administrator
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {

	private EditText edAccount = null;// 账号输入框
	private EditText edPassword = null;// 密码输入框
	private Button btRegister = null;// 注册按钮
	private Button btLogin = null;// 登录按钮
	private String userAccount = null;// 用户账号
	private String userPassword = null;// 用户面
	private HttpUtils utils = Utils.getHttpUtils();// 网络请求对象
	private RequestParams params = null;// 请求参数
	private Intent intent = null;// 意图
	private DefaultHttpClient mDh = null;//
	private CookieStore mCookieStore = null;// cookie的存储策略类
	private List<Cookie> mCookies = null;// cookie 列表
	private String mSessionId = "";// Session的ID

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
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register:
			intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
		case R.id.bt_login:
			login();
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
			Toast.makeText(Utils.getContext(), "用户账号或密码不能为空", 0).show();
			return;
		}

		// 设置POST请求的参数
		params = new RequestParams();
		List<NameValuePair> lsit = new ArrayList<NameValuePair>();
		lsit.add(new BasicNameValuePair("userAccount", userAccount));
		lsit.add(new BasicNameValuePair("userPassword", userPassword));
		params.addBodyParameter(lsit);
		utils.send(HttpMethod.POST, Constants.LOGINURL, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// 请求失败后的回调
						System.out.println("失败");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// 请求成功后的回调
						String result = arg0.result;
						mDh = (DefaultHttpClient) utils.getHttpClient();
						mCookieStore = mDh.getCookieStore();
						mCookies = mCookieStore.getCookies();
						mSessionId = "";
						for (int i = 0; i < mCookies.size(); i++) {
							if ("JSESSIONID".equals(mCookies.get(i).getName())) {
								mSessionId = mCookies.get(i).getValue();
								System.out.println("login" + mSessionId);
								Utils.putSP("JSESSIONID", mSessionId);
							}
						}

						System.out.println("result--->" + result);
						if (!"-1".equals(result)) {
							System.out.println("登录成功");
							Toast.makeText(Utils.getContext(), "登录成功", 0)
									.show();
							finish();
						} else {
							Toast.makeText(Utils.getContext(), "登录失败", 0)
									.show();
						}
					}
				});
	}
}