package com.clark.activity;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.clark.utils.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.RenderPriority;

public class LongUrlToShortUrlActivity extends Activity {

	private WebView mWebView;
	private ProgressDialog mDialog;
	private AsyncHttpClient client = new AsyncHttpClient();
	private String mShortUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_short_long_url);
		mWebView = (WebView) findViewById(R.id.detail_webview);
		initData();

	}

	private void initData() {
		mDialog = new ProgressDialog(this);
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setUserAgentString("yiqichang");
		webSettings.setJavaScriptEnabled(true);
		mWebView.getSettings().setRenderPriority(RenderPriority.HIGH);
		mWebView.setWebViewClient(webViewClient);
		mWebView.setWebChromeClient(new WebChromeClient());
		String longUrl = "http://www.17chang.com/recording/index.php?info=eyJ1c2VybmFtZSI6IuW/q+aNt+mUrum8oCIsInNpbmdlciI6Imh0dHA6XC9cL3NvbmdsaXN0LjE3Y2hhbmcuY29tOjIyMjJcL2F2YXRhclwv5byg5p2wLmpwZyIsInNvbmdfc3JjIjoiaHR0cDpcL1wvbXVzaWMuMTdjaGFuZy5jb21cLzY3NzY3XzkwODU0XzE0MzI5NzQ4MThfbWVyZ2UubXAzIiwia3R2IjoiIiwic29uZ19uYW1lIjoi56m/6LaK5Lq65rW3In0=";
		getShortUrl(longUrl);

	}

	private void getShortUrl(String str) {
		RequestParams req = new RequestParams();
		req.add("url", str);
		client.post("http://dwz.cn/create.php", req,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] res) {
						String result = new String(res);
						Log.v("111", result);
						try {
							JSONObject object = new JSONObject(result);
							int status = object.getInt("status");
							if (status != -1) {
								mShortUrl = object.getString("tinyurl");
								loadWebview(mShortUrl);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {

					}
				});
	}

	private void loadWebview(String url) {
		mWebView.loadUrl(url);
	}

	private WebViewClient webViewClient = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			mDialog.show();
			super.onPageStarted(view, url, favicon);
		};

		@Override
		public void onPageFinished(WebView view, String url) {
			mDialog.dismiss();
			super.onPageFinished(view, url);
		};
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWebView.destroy();
	};
}
