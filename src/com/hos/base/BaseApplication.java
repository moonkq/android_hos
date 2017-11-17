package com.hos.base;

import android.app.Application;
import android.content.Context;

import com.lidroid.xutils.HttpUtils;
/**
 * BaseApplication��,Ӧ�ó�������ʼ��ڡ�
 * ���ã�����ȫ�ֱ�����
 * @author Administrator
 *
 */
public class BaseApplication extends Application {

	private static Context context;//�����Ķ���
	private static HttpUtils utils;//�����������

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
			// ��������ʱʱ��Ϊ10��
			utils = new HttpUtils(1000 * 10);
			utils.configSoTimeout(1000 * 10);
			utils.configResponseTextCharset("UTF-8");
		}
		return utils;
	}

}
