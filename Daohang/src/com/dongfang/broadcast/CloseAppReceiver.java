package com.dongfang.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 
 * @author dongfang
 * 
 */
public class CloseAppReceiver extends BroadcastReceiver {
	public static final String TAG = CloseAppReceiver.class.getSimpleName();

	private Activity activity;

	public CloseAppReceiver(Activity activity) {
		this.activity = activity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if ((context.getPackageName().toString() + "." + TAG).equals(intent.getAction()))
			activity.finish();
	}
}
