package com.clark.activity;

import com.clark.utils.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//�Ự�б� Activity
public class ConversationListActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.conversationlist); // ���ػỰ�б�ҳ�� Fragment��
	}
}