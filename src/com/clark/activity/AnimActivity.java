package com.clark.activity;

import com.clark.common.until.MyAnimView;
import com.clark.utils.R;

import android.app.Activity;
import android.os.Bundle;

public class AnimActivity extends Activity {
	
	private MyAnimView mAnimView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anmi);
		mAnimView=(MyAnimView)findViewById(R.id.myanimview);
//		mAnimView.startAnimation();
	}

}
