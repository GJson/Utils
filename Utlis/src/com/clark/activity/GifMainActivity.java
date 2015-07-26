package com.clark.activity;

import com.clark.gifutils.GifView;
import com.clark.gifutils.GifView.GifImageType;
import com.clark.utils.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewOverlay;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class GifMainActivity extends Activity implements OnClickListener {
	private GifView mGifView1;
	private GifView mGifView2;
	private Button btn;
	private boolean mIsplay = true;
	private WebView mWebView;
	private TextView mAnimTxt;
	private static final int MAX = 1;// 初始maxLine大小
	private static final int TIME = 20000;// 间隔时间
	private int maxLines;
	private boolean hasMesure = false;
	private Thread thread;
	ViewTreeObserver viewTreeObserver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gif);
		initView();
	}

	private void initView() {
		mAnimTxt = (TextView) findViewById(R.id.anim_txt);
		viewTreeObserver = mAnimTxt.getViewTreeObserver();
		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {

			@Override
			public boolean onPreDraw() {
				// 只需要获取一次就可以了
				if (!hasMesure) {
					// 这里获取到完全展示的maxLine
					maxLines = mAnimTxt.getLineCount();
					// 设置maxLine的默认值，这样用户看到View就是限制了maxLine的TextView
					mAnimTxt.setMaxLines(MAX);
					hasMesure = true;
				}

				return true;
			}
		});
		mGifView1 = (GifView) findViewById(R.id.gif1);
		mGifView2 = (GifView) findViewById(R.id.gif2);
		mGifView1.setGifImage(R.drawable.gif1);
		btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);
		// mGifView1.setOnClickListener(this);

		// 锟斤拷锟矫斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞撅拷锟绞轿伙拷锟绞撅拷锟揭恢�
		// mGifView2.setGifImageType(GifImageType.COVER);
		// 锟斤拷锟斤拷图片锟斤拷小
		// mGifView2.setShowDimension(300, 300);
		// 锟斤拷锟斤拷要锟斤拷锟脚碉拷GIF图片
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
				// 只锟斤拷示锟斤拷一帧
				mGifView1.showCover();
				mIsplay = false;
			} else {
				// 锟斤拷锟斤拷哦锟斤拷锟斤拷锟斤拷梅锟斤拷锟斤拷诘锟斤拷锟絪howCover锟斤拷使锟矫诧拷锟斤拷效锟斤拷
				mGifView1.showAnimation();
				mIsplay = true;
			}
			break;
		case R.id.button1:
			// Intent i = new Intent(this, WebJsLocalInteractionActivity.class);
			// startActivity(i);
			toggle();
			break;
		default:
			break;
		}

	}

	/**
	 * 打开TextView
	 */
	@SuppressLint("HandlerLeak")
	private void toggle() {
		mAnimTxt.setVisibility(View.VISIBLE);
		viewTreeObserver = mAnimTxt.getViewTreeObserver();
		viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {

			@Override
			public boolean onPreDraw() {
				// 只需要获取一次就可以了
				if (!hasMesure) {
					// 这里获取到完全展示的maxLine
					maxLines = mAnimTxt.getLineCount();
					// 设置maxLine的默认值，这样用户看到View就是限制了maxLine的TextView
					mAnimTxt.setMaxLines(MAX);
					hasMesure = true;
				}

				return true;
			}
		});
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				int lines = msg.what;
				// 这里接受到消息，让后更新TextView设置他的maxLine就行了
				mAnimTxt.setMaxLines(lines);
				mAnimTxt.postInvalidate();
			}
		};
		if (thread != null)
			handler.removeCallbacks(thread);

		thread = new Thread() {
			@Override
			public void run() {
				int count = MAX;
				while (count++ <= maxLines) {
					// 每隔20mms发送消息
					Message message = new Message();
					message.what = count;
					handler.sendMessage(message);

					try {
						Thread.sleep(TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				super.run();
			}
		};
		thread.start();
	}

}
