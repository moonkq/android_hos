package com.hos.app;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hos.R;
import com.jorge.circlelibrary.ImageCycleView;
import com.jorge.circleviewpager.ImageLoaderHelper;

public class MainActivity extends Activity {
	
	private ImageView rankImage, typeImage, helpImage, feedbackImage;// tab中的图片
	private TextView rankText, typeText, helpText, feedbackText;// tab中的文字
	
	ImageCycleView imageCycleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		  /** 找到轮播控件*/
        imageCycleView= (ImageCycleView) findViewById(R.id.cycleView);

        imageCycleView.setCycle_T(ImageCycleView.CYCLE_T.CYCLE_VIEW_THREE_SCALE);
        /**装在数据的集合  文字描述*/
        ArrayList<String> imageDescList=new ArrayList<String>();
        /**装在数据的集合  图片地址*/
        ArrayList<String> urlList=new ArrayList<String>();

        /**添加数据*/
        urlList.add("http://attach.bbs.miui.com/forum/month_1012/101203122706c89249c8f58fcc.jpg");
        urlList.add("http://bbsdown10.cnmo.com/attachments/201308/06/091441rn5ww131m0gj55r0.jpg");
//        urlList.add("http://kuoo8.com/wall_up/hsf2288/200801/2008012919460743597.jpg");
        urlList.add("http://attach.bbs.miui.com/forum/201604/05/001754vp6j0vmcj49f0evc.jpg.thumb.jpg");
        urlList.add("http://d.3987.com/taiqiumein_141001/007.jpg");
        urlList.add("http://attach.bbs.miui.com/forum/201604/05/100838d2b99k6ihk32a36a.jpg.thumb.jpg");

        imageDescList.add("小仓柚子");
        imageDescList.add("抚媚妖娆性感美女");
        imageDescList.add("热血沸腾 比基尼");
        imageDescList.add(" 台球美女");
        imageDescList.add("身材妙曼");


        initCarsuelView(imageDescList, urlList);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	  /**初始化轮播图*/
    public void initCarsuelView(ArrayList<String> imageDescList,ArrayList<String>urlList) {
        LinearLayout.LayoutParams cParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getScreenHeight(MainActivity.this) * 3 / 10);
        imageCycleView.setLayoutParams(cParams);
        ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {
                /**实现点击事件*/
                Toast.makeText(MainActivity.this,"position="+position,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                /**在此方法中，显示图片，可以用自己的图片加载库，也可以用本demo中的（Imageloader）*/
                ImageLoaderHelper.getInstance().loadImage(imageURL, imageView);
            }
        };
        /**设置数据*/
        imageCycleView.setImageResources(imageDescList,urlList, mAdCycleViewListener);
        // 是否隐藏底部
        imageCycleView.hideBottom(false);
        imageCycleView.startImageCycle();
    }
	
	 /**
     * 得到屏幕的高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){
        if (null == context) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    
	/**
	 * 初始化视图
	 */
	public void initViews() {
//		rankTab = (LinearLayout) findViewById(R.id.tabLayout_rank);
//		typeTab = (LinearLayout) findViewById(R.id.tabLayout_type);
//		helpTab = (LinearLayout) findViewById(R.id.tabLayout_help);
//		feedbackTab = (LinearLayout) findViewById(R.id.tabLayout_feedback);
//		rankTab.setOnClickListener(new TabOnClickListener(0));
//		typeTab.setOnClickListener(new TabOnClickListener(1));
//		helpTab.setOnClickListener(new TabOnClickListener(2));
//		feedbackTab.setOnClickListener(new TabOnClickListener(3));
		rankImage = (ImageView) findViewById(R.id.imageView_rank);
		typeImage = (ImageView) findViewById(R.id.imageView_type);
		helpImage = (ImageView) findViewById(R.id.imageView_help);
		feedbackImage = (ImageView) findViewById(R.id.imageView_feedback);
		rankText = (TextView) findViewById(R.id.textView_rank);
		typeText = (TextView) findViewById(R.id.textView_type);
		helpText = (TextView) findViewById(R.id.textView_help);
		feedbackText = (TextView) findViewById(R.id.textView_feedback);
	}

}
