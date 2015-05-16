package com.clark.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.clark.utils.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class WebJsLocalInteractionActivity extends Activity {

	private WebView mWebView;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		context = this;
		init();

	}

	private void init() {
		Map<String, List<String>> users;
		users = new HashMap<String, List<String>>();
		for (Entry<String, List<String>> item : users.entrySet()) {
			item.getKey();
			item.getValue();
		}
		Set<String> keys = users.keySet();
		for (String item : keys) {
			users.get(item);

		}
		mWebView = (WebView) findViewById(R.id.webView2);
		WebSettings ws = mWebView.getSettings();
		ws.setJavaScriptEnabled(true);
		mWebView.setWebChromeClient(new WebChromeClient());

		mWebView.loadUrl("file:///android_asset/index.html");
		// mWebView.loadUrl("http://192.168.99.200/Web-17chang/phoneanimation/");
		// mWebView.loadUrl("http://42.159.224.23:800//AppInternalPage/Share/share.htm");
		// mWebView.addJavascriptInterface(new , name);
		mWebView.addJavascriptInterface(new JavaScriptInterface(), "gohome");
	}

	final class JavaScriptInterface {
		public void gotoHome() {
			Intent intent = new Intent(WebJsLocalInteractionActivity.this, GifMainActivity.class);
			startActivity(intent);
//			Toast.makeText(context, "js���ص���", Toast.LENGTH_SHORT).show();
		}
	}
}
