package com.clark.common.until;

import io.rong.imkit.RongIM;
import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		/**
		 * IMKit SDK ≥ı ºªØ
		 */
		RongIM.init(this);
	}
}