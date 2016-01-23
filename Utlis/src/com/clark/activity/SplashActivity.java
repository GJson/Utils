package com.clark.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;

import com.clark.common.until.Constants;
import com.clark.utils.R;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;


/**
 * @name SplashActivity.java
 *
 * @time 2016-1-22
 *
 * @Version 1.0
 *
 * @Author Gjson
 * 
 */

public class SplashActivity extends Activity implements SplashADListener {

	  @SuppressWarnings("unused")
	  private SplashAD splashAD;
	  private ViewGroup container;

	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_splash);
	    container = (ViewGroup) this.findViewById(R.id.splash_container);
	    splashAD = new SplashAD(this, container, Constants.APPID, Constants.SplashPosID, this);
	  }

	  @Override
	  public void onADPresent() {
	    Log.i("AD_DEMO", "SplashADPresent");
	  }

	  @Override
	  public void onADDismissed() {
	    Log.i("AD_DEMO", "SplashADDismissed");
	    next();
	  }

	  private void next() {
	    this.startActivity(new Intent(this, MainActivity.class));
	    this.finish();
	  }

	  @Override
	  public void onNoAD(int arg0) {
	    Log.i("AD_DEMO", "LoadSplashADFail,ecode=" + arg0);
	    next();
	  }

	  @Override
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
	      return true;
	    }
	    return super.onKeyDown(keyCode, event);
	  }
	}
