package com.clark.activity;

import com.clark.utils.R;
import com.clark.view.RippleLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class RippleActivity extends Activity {
	ImageView imageview;
	RippleLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ripple);

		layout = (RippleLayout) findViewById(R.id.ripple_layout);
		imageview = (ImageView) findViewById(R.id.centerImage);
		imageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (layout.isRippleAnimationRunning()) {
					imageview.setImageResource(R.drawable.mac_btn_mac_normal);
					layout.stopRippleAnimation();
				} else {
					imageview.setImageResource(R.drawable.mac_btn_mac_pressed);
					layout.startRippleAnimation();
				}
			}
		});
	}

}
