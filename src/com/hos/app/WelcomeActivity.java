package com.hos.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.hos.R;

/**
 * 启动应用程序界面
 * */
public class WelcomeActivity extends Activity {
	protected MyRunnable myrun = new MyRunnable();
	private Handler handler = new Handler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 全屏设置，隐藏窗口所有装饰
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// 标题是属于View的，所以窗口所有的修饰部分被隐藏后标题依然有效
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.welcome);
		handler.postDelayed(myrun, 3000);
	}

	class MyRunnable implements Runnable {
		public void run() {
			Intent in = new Intent();
			in.setClass(WelcomeActivity.this, LoginActivity.class);
			startActivity(in);
			WelcomeActivity.this.finish();
		}
	}
}