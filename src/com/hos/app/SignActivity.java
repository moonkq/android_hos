package com.hos.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.hos.R;
import com.hos.entities.AccessRec;

/**
 * 签约Activity
 */
public class SignActivity extends Activity {
	
	
	
	
	private List<AccessRec> records;// 随访列表数据
	protected ListView lvSign;// 随访列表
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signlist);
		lvSign = (ListView) findViewById(R.id.lvsign);
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.listview_item_sign, 
				new String[]{"id","name","score"}, new int[]{R.id.id,R.id.name,R.id.score});
		
		lvSign.setAdapter(adapter);
	}
	
	private List getData(){
		// 基础数据
		String[] ids = {"0001","0002","0003","0004","0005"};
		String[] names = {"赵一","钱二","孙三","李四","周五"};
		float[] scores = {92.0f, 85.5f, 93.0f, 60.0f, 78.5f};
		
		// 将原有数据组合成SimpleAdapter可以接受的数据类型
		List data = new ArrayList();
		for(int i=0;i<5;i++){
			Map map = new HashMap();
			map.put("id", ids[i]);
			map.put("name", names[i]);
			map.put("score", scores[i]);
			data.add(map);
		}
		return data;
	}



}
