package com.clark.activity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ChatRongYunActivity extends FragmentActivity {

	private String tag = getClass().getName();
	private Context mContext;
	/**
	 * �����Ӧ�÷��������󲢻�ȡ�� Token��
	 */
	private String Token = "ABiJiw9kjm5JNKBqOueR1kmcbyeYIrXSDaacEOnpq4ALGp+0d48QDl1Qq02s3vO1S9Uvmgoceg==";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		RongIM.init(this);
		mContext = this;

		/**
		 * IMKit SDK���õڶ���������������������ӡ�
		 */
		RongIM.connect(Token, new RongIMClient.ConnectCallback() {

			@Override
			public void onSuccess(String userId) {
				// Connect �ɹ�
				Log.v(tag, "userid:" + userId);
			}

			@Override
			public void onError(RongIMClient.ErrorCode error) {
				// Connect ʧ��
				Log.v(tag, "error:" + error.getMessage());
			}
		 
		});
	}
}
