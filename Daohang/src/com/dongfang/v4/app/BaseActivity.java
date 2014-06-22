package com.dongfang.v4.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dongfang.broadcast.CloseAppReceiver;
import com.dongfang.utils.ULog;
import com.lidroid.xutils.ViewUtils;

/**
 * 1. 所有该项目activity都需要继承该类；<br>
 * 
 * @author dongfang
 * 
 */
public abstract class BaseActivity extends FragmentActivity {

	public abstract void onClick(View v);

	protected String TAG = BaseActivity.class.getSimpleName();
	private CloseAppReceiver closeApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		closeApp = new CloseAppReceiver(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(getPackageName().toString() + "." + CloseAppReceiver.TAG);
		registerReceiver(closeApp, filter);
		// 系统崩溃日志,崩溃重启
		// MyUnCaughtExceptionHandler.getInstance(this);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		ViewUtils.inject(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter connectFilter = new IntentFilter();
		connectFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(connectReciver, connectFilter);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			unregisterReceiver(connectReciver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(closeApp);
		connectReciver = null;
		closeApp = null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		ULog.d("onCreateOptionsMenu");
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		// case R.id.menu_item_exit:
		// appExit();
		// return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return super.onKeyDown(keyCode, event);
	}

	protected void showAppExitDialog() {}

	protected void showOfflineViewExitDialog() {}

	protected void appExit() {
		sendBroadcast(new Intent(getPackageName().toString() + "." + CloseAppReceiver.TAG));
	}

	private BroadcastReceiver connectReciver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {}
	};

}