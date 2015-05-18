package com.clark.activity;

import java.util.ArrayList;
import java.util.List;

import com.clark.adapter.UlitlsListAdapter;
import com.clark.common.until.ToastManager;
import com.clark.utils.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private Context mContext;
	private List<String> mListStrs = new ArrayList<String>();
	private ListView mListView;
	private UlitlsListAdapter mAdapter;
	private long mLastPressBackExitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		mContext = this;
		mListView = (ListView) findViewById(R.id.utilslist);
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
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
			long arg3) {
		if (positon > 6)
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