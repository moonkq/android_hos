package com.hos.base;

import java.io.File;

import android.app.Application;
import android.content.Context;

import com.jorge.circleviewpager.FileUtils;
import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
/**
 * BaseApplication类,应用程序的最初始入口。
 * 作用：定义全局变量。
 * @author Administrator
 *
 */
public class BaseApplication extends Application {
	
	/** 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了 */
	private static BaseApplication mInstance;

	private static Context context;//上下文对象
	private static HttpUtils utils;//网络请求对象

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		mInstance = this;
		
		super.onCreate();
		context = getApplicationContext();
		
		initImageLoader();
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
	
	public void initImageLoader(){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mInstance)
                .threadPriority(Thread.NORM_PRIORITY + 2)
                .denyCacheImageMultipleSizesInMemory()
                .threadPoolSize(10)
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiskCache(new File(FileUtils.getIconDir(this))))
                .memoryCache(new LRULimitedMemoryCache(2*1024*1024))
                .build();

        ImageLoader.getInstance().init(config);
}

}
