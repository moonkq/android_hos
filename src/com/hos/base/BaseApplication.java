package com.hos.base;

import android.app.Application;
import android.content.Context;

import com.lidroid.xutils.HttpUtils;
/**
 * BaseApplication类,应用程序的最初始入口。
 * 作用：定义全局变量。
 * @author Administrator
 *
 */
public class BaseApplication extends Application {

	private static Context context;//上下文对象
	private static HttpUtils utils;//网络请求对象

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
	}

	public static Context getContext() {
		return context;
	}

	public static void setContext(Context context) {
		BaseApplication.context = context;
	}
	
	public synchronized static HttpUtils getInstence() {
		if (utils == null) {
			// 设置请求超时时间为10秒
			utils = new HttpUtils(1000 * 10);
			utils.configSoTimeout(1000 * 10);
			utils.configResponseTextCharset("UTF-8");
		}
		return utils;
	}

}
