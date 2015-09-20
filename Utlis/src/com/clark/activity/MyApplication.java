package com.clark.activity;

import java.io.File;

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

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		initImageLoader(this, null);
		/**
		 * IMKit SDK ��ʼ��
		 */
		// RongIM.init(this);
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
