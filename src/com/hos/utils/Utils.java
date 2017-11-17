package com.hos.utils;

import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hos.base.BaseApplication;
import com.lidroid.xutils.HttpUtils;
/**
 * ������
 * @author Administrator
 *
 */
public class Utils {
	/**
	 * ������ת��Ϊ����
	 * @param i ����
	 * @return
	 */
	public static String getDate(int i) {
		String date = null;
		switch (i) {
		case 1:
			date = "����һ";
			break;
		case 2:
			date = "���ڶ�";
			break;
		case 3:
			date = "������";
			break;
		case 4:
			date = "������";
			break;
		case 5:
			date = "������";
			break;
		case 6:
			date = "������";
			break;
		case 7:
			date = "������";
			break;

		default:
			break;
		}
		return date;

	}
/**
 * �õ�һ��[0,9)���������
 * @return
 */
	public static int getInt() {

		Random ra = new Random();
		int i = ra.nextInt(9);
		return i;

	}

	/**
	 * ��ȡ������
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
	 * ���ַ�����SharedPreferences��
	 * 
	 * @param key
	 *            ��
	 * @param value
	 *            ֵ
	 */
	public static void putSP(String key, String value) {
		editor.putString(key, value);
		editor.commit();
	}

	/**
	 * ��SharedPreferences�л�ȡ�ַ���
	 * 
	 * @param key
	 *            ��
	 * @return ֵ
	 */
	public static String getSP(String key) {
		String name;
		name = sp.getString(key, "");
		return name;

	}

	private static HttpUtils utils;

	/**
	 * �õ�utils
	 */
	public static synchronized HttpUtils getHttpUtils() {
		if (utils == null) {
			utils = new HttpUtils();
		}
		return utils;

	}

}
