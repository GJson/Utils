package com.clark.activity;

import com.clark.common.until.MyScrollView;
import com.clark.common.until.MyScrollView.OnScrollListener;
import com.clark.utils.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;

/**
 * 锟斤拷锟酵碉拷址:http://blog.csdn.net/zhuangyalei
 * 
 * @author Gjson
 * 
 */
public class ScrollMeiTuanActivity extends Activity implements OnScrollListener {
	private MyScrollView myScrollView;
	private LinearLayout mBuyLayout;
	private WindowManager mWindowManager;
	/**
	 * 锟街伙拷锟斤拷幕锟斤拷锟�
	 */
	private int screenWidth;
	/**
	 * 锟斤拷锟絍iew
	 */
	private static View suspendView;
	/**
	 * 锟斤拷锟侥诧拷锟斤拷
	 */
	private static WindowManager.LayoutParams suspendLayoutParams;
	/**
	 * 锟斤拷锟津布局的高讹拷
	 */
	private int buyLayoutHeight;
	/**
	 * myScrollView锟斤拷锟戒父锟洁布锟街的讹拷锟斤拷锟斤拷锟斤拷
	 */
	private int myScrollViewTop;

	/**
	 * 锟斤拷锟津布撅拷锟斤拷锟戒父锟洁布锟街的讹拷锟斤拷锟斤拷锟斤拷
	 */
	private int buyLayoutTop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll);

		myScrollView = (MyScrollView) findViewById(R.id.scrollView);
		mBuyLayout = (LinearLayout) findViewById(R.id.buy);

		myScrollView.setOnScrollListener(this);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = mWindowManager.getDefaultDisplay().getWidth();
	}

	/**
	 * 锟斤拷锟斤拷锟叫斤拷锟斤拷锟绞憋拷颍锟斤拷锟斤拷械牟锟斤拷只锟斤拷锟斤拷锟较碉拷时锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷取锟斤拷锟津布局的高度猴拷myScrollView锟斤拷锟诫父锟洁布锟街的讹拷锟斤拷位锟斤拷
	 */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			buyLayoutHeight = mBuyLayout.getHeight();
			buyLayoutTop = mBuyLayout.getTop();

			myScrollViewTop = myScrollView.getTop();
		}
	}

	/**
	 * 锟斤拷锟斤拷锟侥回碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷Y锟斤拷锟斤拷锟斤拷诨锟斤拷叩锟斤拷锟�锟斤拷锟津布局撅拷锟诫父锟洁布锟街讹拷锟斤拷锟斤拷位锟矫ｏ拷锟斤拷锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷锟斤拷锟斤拷Y锟侥撅拷锟斤拷小锟斤拷
	 * 锟斤拷锟津布局撅拷锟诫父锟洁布锟街讹拷锟斤拷锟斤拷位锟矫硷拷锟较癸拷锟津布局的高度撅拷锟狡筹拷锟斤拷锟斤拷锟斤拷
	 * 
	 */
	@Override
	public void onScroll(int scrollY) {
		if (scrollY >= buyLayoutTop) {
			if (suspendView == null) {
				showSuspend();
			}
		} else if (scrollY <= buyLayoutTop + buyLayoutHeight) {
			if (suspendView != null) {
				removeSuspend();
			}
		}
	}

	/**
	 * 锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷
	 */
	private void showSuspend() {
		if (suspendView == null) {
			suspendView = LayoutInflater.from(this).inflate(
					R.layout.buy_layout, null);
			if (suspendLayoutParams == null) {
				suspendLayoutParams = new LayoutParams();
				suspendLayoutParams.type = LayoutParams.TYPE_PHONE; // 锟斤拷锟斤拷锟斤拷停锟揭伙拷锟斤拷锟轿�002锟斤拷锟斤拷示锟斤拷锟斤拷锟斤拷应锟矫筹拷锟斤拷之锟较ｏ拷锟斤拷锟斤拷状态锟斤拷之锟斤拷
				suspendLayoutParams.format = PixelFormat.RGBA_8888;
				suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE; // 锟斤拷锟斤拷锟轿拷锟斤拷锟斤拷锟剿碉拷锟斤拷删劢锟斤拷锟斤拷锟侥Ｌ拷曰锟斤拷锟饺碉拷
				suspendLayoutParams.gravity = Gravity.TOP; // 锟斤拷亩锟斤拷敕绞�
				suspendLayoutParams.width = screenWidth;
				suspendLayoutParams.height = buyLayoutHeight;
				suspendLayoutParams.x = 0; // 锟斤拷X锟斤拷位锟斤拷
				suspendLayoutParams.y = myScrollViewTop; // //锟斤拷Y锟斤拷位锟斤拷
			}
		}

		mWindowManager.addView(suspendView, suspendLayoutParams);
	}

	/**
	 * 锟狡筹拷锟斤拷锟斤拷锟斤拷
	 */
	private void removeSuspend() {
		if (suspendView != null) {
			mWindowManager.removeView(suspendView);
			suspendView = null;
		}
	}

}