package com.clark.activity;

import com.clark.utils.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//�Ự Activity
public class ConversationActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.conversation); // ���ػỰҳ�� Fragment��
	}
}