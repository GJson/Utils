package com.clark.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.clark.adapter.UlitlsListAdapter;
import com.clark.common.until.ToastManager;
import com.clark.utils.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private Context mContext;
	private List<String> mListStrs = new ArrayList<String>();
	private ListView mListView;
	private UlitlsListAdapter mAdapter;
	private long mLastPressBackExitTime = 0;

	public enum FAIL {
		NOR, DD, DDD, DDDD
	};

	// FAIL e=FAIL.NOR;

	public void enumTest(FAIL type) {
		switch (type) {
		case NOR:

			break;

		default:
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// enumTest(e);
		setContentView(R.layout.activity_main);
		init();
		testParenter();
		int[] a = { 3, 1, 5, 7, 2, 4, 9, 6 };
		insertsortmain(a);
		// enumTest(FAIL.NOR);
	}

	private void insertsortmain(int[] b) {
		for (int i = 1; i < b.length; i++) {
			int median = b[i];
			int j = i - 1;
			while (j >= 0 && median < b[j]) {
				b[j + 1] = b[j];
				j--;
			}
			b[j + 1] = median;
		}
		for (int j = 0; j < 8; j++) {
			Log.d("111", (b[j]) + "");
		}
	}

	/*
	 * 正则匹配
	 */
	private void testParenter() {
		String s = "abcaawea";
		Pattern p = Pattern.compile("(a[^a]*a)");
		Matcher m = p.matcher(s);
		List<String> result = new ArrayList<String>();
		while (m.find()) {
			result.add(m.group());
		}
		for (String s1 : result) {
			System.out.println(s1);
		}

	}

	private void init() {
		mContext = this;
		mListView = (ListView) findViewById(R.id.utilslist);
		String time = DateFormat.format("yy-MM-dd HH:mm:ss",
				new Date(System.currentTimeMillis())).toString();
		String time1 = DateFormat.format("yyyy-MM-dd HH:mm:ss",
				System.currentTimeMillis()).toString();
		System.out.println(System.currentTimeMillis());
		System.out.println(new Date(System.currentTimeMillis()));
		System.out.println(time);
		System.out.println(time1);
		initDatas();
		mAdapter = new UlitlsListAdapter(mContext, mListStrs);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);

	}

	private void initDatas() {
		mListStrs.add("GifMainActivity & URL SCheme");
		mListStrs.add("WebJsLocalInteractionActivity");
		mListStrs.add("AnimActivity & ValueAnimation");
		mListStrs.add("PulldownViewSinaActivity");
		mListStrs.add("StretchViewActivity");
		mListStrs.add("ScrollMeiTuanActivity");
		mListStrs.add("ScrollListViewQQActivity");
		mListStrs.add("OrmLiteBaseActivity");
		mListStrs.add("LongToShortUrlActivity");
		mListStrs.add("QuickReturnHeaderActivity");
		mListStrs.add("AutoScrollViewPageAvtivity");
		mListStrs.add("RippleActivity");
		mListStrs.add("RainViewActivity");
		mListStrs.add("BrokenActivity");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
			long arg3) {
		if (positon > 13)
			return;
		Intent intent = new Intent();
		switch (positon) {
		case 0:
			intent = new Intent(this, GifMainActivity.class);
			break;
		case 1:
			intent = new Intent(this, WebJsLocalInteractionActivity.class);
			break;
		case 2:
			intent = new Intent(this, AnimActivity.class);
			break;
		case 3:
			intent = new Intent(this, PulldownViewSinaActivity.class);
			break;
		case 4:
			intent = new Intent(this, StretchViewActivity.class);
			break;
		case 5:
			intent = new Intent(this, ScrollMeiTuanActivity.class);
			break;
		case 6:
			intent = new Intent(this, ScrollListViewQQActivity.class);
			break;

		case 7:
			intent.setClass(mContext, UserInfoEditAcitivity.class);
			break;
		case 8:
			intent.setClass(mContext, LongUrlToShortUrlActivity.class);
			break;
		case 9:
			intent.setClass(mContext, QuickReturnHeaderActivity.class);
			break;
		case 10:
			intent.setClass(mContext, AutoScrollViewPageAvtivity.class);
			break;
		case 11:
			intent.setClass(mContext, RippleActivity.class);
			break;
		case 12:
			intent.setClass(mContext, RainViewActivity.class);
			break;
		case 13:
			intent.setClass(mContext, BrokenViewActivity.class);
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_UP) {
			if ((System.currentTimeMillis() - mLastPressBackExitTime) > 3000) {
				showToast(getString(R.string.prompt_exit_yiqichang),
						ToastManager.TOAST_FLAG_EXCEPTION);
				mLastPressBackExitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}

		return super.dispatchKeyEvent(event);
	}

	private void showToast(String message, int toastFlag) {
		ToastManager.showToast(mContext, message, toastFlag);
	}
}
