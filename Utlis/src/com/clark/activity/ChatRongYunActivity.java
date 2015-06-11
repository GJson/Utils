package com.clark.activity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import com.clark.common.until.ToastManager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ChatRongYunActivity extends FragmentActivity {

	private String tag = getClass().getName();
	private Context mContext;
	/**
	 * 从您的应用服务器请求并获取的 Token。
	 */
	private String Token = "ABiJiw9kjm5JNKBqOueR1kmcbyeYIrXSDaacEOnpq4ALGp+0d48QDl1Qq02s3vO1S9Uvmgoceg==";

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		RongIM.init(this);
		mContext = this;

		/**
		 * IMKit SDK调用第二步，建立与服务器的连接。
		 */
		RongIM.connect(Token, new RongIMClient.ConnectCallback() {

			@Override
			public void onSuccess(String userId) {
				// Connect 成功
				Log.v(tag, "userid:" + userId);
			}

			@Override
			public void onError(RongIMClient.ErrorCode error) {
				// Connect 失败
				Log.v(tag, "error:" + error.getMessage());
			}
		 
		});
	}
}
