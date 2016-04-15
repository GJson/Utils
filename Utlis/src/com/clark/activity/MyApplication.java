package com.clark.activity;

import java.io.File;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVInstallation;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this, null);
		 AVOSCloud.initialize(this, "cgO4vY244Qa0CENl20Dgxqiy-gzGzoHsz", "630z2iM42DYE9c2vK4mj3io9");
		/**
		 * IMKit SDK ��ʼ��
		 */
		// RongIM.init(this);
		 AVObject testObject = new AVObject("TestObject");
		 testObject.put("foo", "bar");
//		 testObject.saveInBackground();
		 AVInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
			    public void done(AVException e) {
			    	Log.v("111","saveInBackground");
			        if (e == null) {
			        	
			            // 保存成功
			            String installationId = AVInstallation.getCurrentInstallation().getInstallationId();
			            Log.v("111", "installationId chenggong:" +installationId);
			            // 关联  installationId 到用户表等操作……
			        } else {
			        	Log.v("111", " // 保存失败，输出错误信息");
			            // 保存失败，输出错误信息
			        }
			    }
			});
	}

	/**
	 * ��ʼ��ͼƬ������
	 * 
	 * @param mContext
	 * @param defaultOptions
	 */
	public static void initImageLoader(Context mContext,
			DisplayImageOptions defaultOptions) {

		if (defaultOptions == null)
			defaultOptions = buildImageOptions(mContext);

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				mContext).threadPoolSize(5)
				.defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize(3 * 1024 * 1024)
				.discCacheSize(6 * 1024 * 1024).discCacheFileCount(100) // ������ļ�����
				.discCache(new UnlimitedDiscCache(new File(getCachePath())))
				/* .writeDebugLogs() */.build();

		ImageLoader.getInstance().init(config);
	}

	public static String getCachePath() {
		String sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // �ж�sd���Ƿ����
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory().getPath() + "/"
					+ "catch";// ��ȡ��Ŀ¼
		} else {
			sdDir = "data/data/files/";
		}
		return sdDir;
	}

	/**
	 * ����ͼƬ����
	 * 
	 * @param mContext
	 * @return
	 */
	private static DisplayImageOptions buildImageOptions(Context mContext) {
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.considerExifParams(true).cacheInMemory(false)
				.cacheOnDisc(true)
				// .bitmapConfig(android.graphics.Bitmap.Config.RGB_565)
				// .displayer(new RoundedBitmapDisplayer(1))
				.build();

		return defaultOptions;
	}
}
