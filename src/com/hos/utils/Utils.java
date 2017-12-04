package com.hos.utils;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hos.base.BaseApplication;
import com.lidroid.xutils.HttpUtils;
/**
 * 工具类
 * @author Administrator
 *
 */
public class Utils {
	/**
	 * 把整数转换为星期
	 * @param i 整型
	 * @return
	 */
	public static String getDate(int i) {
		String date = null;
		switch (i) {
		case 1:
			date = "星期一";
			break;
		case 2:
			date = "星期二";
			break;
		case 3:
			date = "星期三";
			break;
		case 4:
			date = "星期四";
			break;
		case 5:
			date = "星期五";
			break;
		case 6:
			date = "星期六";
			break;
		case 7:
			date = "星期日";
			break;

		default:
			break;
		}
		return date;

	}
	/**
	 * 得到一个[0,9)的随机整数
	 * @return
	 */
		public static int getInt() {

			Random ra = new Random();
			int i = ra.nextInt(9);
			return i;

		}

	/**
	 * 获取上下文
	 * 
	 * @return
	 */
	public static Context getContext() {
		return BaseApplication.getContext();
	}

	private static SharedPreferences sp = getContext().getSharedPreferences(
			"LDTs", Context.MODE_PRIVATE);
	private static Editor editor = sp.edit();

	/**
	 * 存字符串到SharedPreferences中
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static void putSP(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * 从SharedPreferences中获取字符串
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static String getSP(String key) {
		String name;
		name = sp.getString(key, "");
		return name;

	}

	private static HttpUtils utils;

	/**
	 * 得到utils
	 */
	public static synchronized HttpUtils getHttpUtils() {
		if (utils == null) {
			utils = new HttpUtils();
		}
		return utils;

	}

}
