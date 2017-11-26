package com.hos.app;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.hos.R;
import com.hos.entities.AccessRec;
/**
 * 随访Activity
 */
public class FollowupActivity extends Activity {
	
	private List<AccessRec> records;// 随访列表数据
	protected ListView lvMeal;// 随访列表
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		lvMeal = (ListView) findViewById(R.id.lv_access);
	}

}
