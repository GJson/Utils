package com.clark.activity;

import com.clark.gifutils.GifView;
import com.clark.gifutils.GifView.GifImageType;
import com.clark.gifview.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class GifMainActivity extends Activity implements OnClickListener {
	private GifView mGifView1;
	private GifView mGifView2;
	private Button btn;
	private boolean mIsplay = true;
	private WebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gif);
		initView();
	}

	private void initView() {
		mGifView1 = (GifView) findViewById(R.id.gif1);
		mGifView2 = (GifView) findViewById(R.id.gif2);
		mGifView1.setGifImage(R.drawable.gif1);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		// mGifView1.setOnClickListener(this);

		// ���ý����������ʾ��ʽΪֻ��ʾ��һ֡
		// mGifView2.setGifImageType(GifImageType.COVER);
		// ����ͼƬ��С
		// mGifView2.setShowDimension(300, 300);
		// ����Ҫ���ŵ�GIFͼƬ
		mGifView2.setGifImage(R.drawable.gif2);
		mWebView = (WebView) findViewById(R.id.webView1);
		WebSettings ws = mWebView.getSettings();
		mWebView.setWebChromeClient(new WebChromeClient());
		ws.setJavaScriptEnabled(true);
		// ws.setAllowUniversalAccessFromFileURLs(true);
		mWebView.loadUrl("http://www.gjson.com/openapp.html");

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.gif1:
			if (mIsplay) {
				// ֻ��ʾ��һ֡
				mGifView1.showCover();
				mIsplay = false;
			} else {
				// ����Ŷ������÷����ڵ���showCover��ʹ�ò���Ч��
				mGifView1.showAnimation();
				mIsplay = true;
			}
			break;
		case R.id.button1:
			Intent i = new Intent(this, SecondActivity.class);
			startActivity(i);
			break;
		default:
			break;
		}

	}

}
