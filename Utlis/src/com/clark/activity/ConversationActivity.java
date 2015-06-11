package com.clark.activity;

import com.clark.utils.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//会话 Activity
public class ConversationActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.conversation); // 加载会话页面 Fragment。
	}
}