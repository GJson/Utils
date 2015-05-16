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
 * ���͵�ַ:http://blog.csdn.net/zhuangyalei
 * 
 * @author Gjson
 * 
 */
public class ScrollActivity extends Activity implements OnScrollListener {
	private MyScrollView myScrollView;
	private LinearLayout mBuyLayout;
	private WindowManager mWindowManager;
	/**
	 * �ֻ���Ļ���
	 */
	private int screenWidth;
	/**
	 * ���View
	 */
	private static View suspendView;
	/**
	 * ���Ĳ���
	 */
	private static WindowManager.LayoutParams suspendLayoutParams;
	/**
	 * ���򲼾ֵĸ߶�
	 */
	private int buyLayoutHeight;
	/**
	 * myScrollView���丸�಼�ֵĶ�������
	 */
	private int myScrollViewTop;

	/**
	 * ���򲼾����丸�಼�ֵĶ�������
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
	 * �����н����ʱ�򣬼����еĲ��ֻ�����ϵ�ʱ����������ȡ���򲼾ֵĸ߶Ⱥ�myScrollView���븸�಼�ֵĶ���λ��
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
	 * �����Ļص���������������Y������ڻ��ߵ��� ���򲼾־��븸�಼�ֶ�����λ�ã�����ʾ�������� ��������Y�ľ���С��
	 * ���򲼾־��븸�಼�ֶ�����λ�ü��Ϲ��򲼾ֵĸ߶Ⱦ��Ƴ�������
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
	 * ��ʾ��������
	 */
	private void showSuspend() {
		if (suspendView == null) {
			suspendView = LayoutInflater.from(this).inflate(
					R.layout.buy_layout, null);
			if (suspendLayoutParams == null) {
				suspendLayoutParams = new LayoutParams();
				suspendLayoutParams.type = LayoutParams.TYPE_PHONE; // ������ͣ�һ����Ϊ2002����ʾ������Ӧ�ó���֮�ϣ�����״̬��֮��
				suspendLayoutParams.format = PixelFormat.RGBA_8888;
				suspendLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
						| LayoutParams.FLAG_NOT_FOCUSABLE; // �����Ϊ������˵���ɾ۽�����ģ̬�Ի���ȵ�
				suspendLayoutParams.gravity = Gravity.TOP; // ��Ķ��뷽ʽ
				suspendLayoutParams.width = screenWidth;
				suspendLayoutParams.height = buyLayoutHeight;
				suspendLayoutParams.x = 0; // ��X��λ��
				suspendLayoutParams.y = myScrollViewTop; // //��Y��λ��
			}
		}

		mWindowManager.addView(suspendView, suspendLayoutParams);
	}

	/**
	 * �Ƴ�������
	 */
	private void removeSuspend() {
		if (suspendView != null) {
			mWindowManager.removeView(suspendView);
			suspendView = null;
		}
	}

}