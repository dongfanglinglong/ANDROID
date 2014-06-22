package com.dongfang.v4.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.dongfang.utils.ULog;

/** @author dongfang */
public class BaseApplication extends Application {
	public static final String TAG = BaseApplication.class.getSimpleName();
	private static BaseApplication myApplication = null;

	/** 行为统计 */
	// private List<ActionReport> actionList = null;
	// private int actionReportCode = -1;

	// public List<ActionReport> getActionList() {
	// if (null == actionList) {
	// actionList = new ArrayList<ActionReport>();
	// return actionList;
	// }
	// return actionList;
	// }

	public void actionReport(final Handler handler) {
		// if (null == getActionList() || getActionList().size() < 1) {
		// if (null != handler)
		// handler.sendEmptyMessage(1);
		// return;
		// }
		// String actionReport = ActionReport.listToJson(getActionList());
		// final String actionReportJson = "{" + "action:" + actionReport + "}";
		// ULog.v(TAG, "actionReportJson=" + actionReportJson);
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// HttpActions actions = new HttpActions(getBaseContext());
		// String json = actions.actionReport(getBaseContext(), actionReportJson);
		// JSONObject jsonObject = new JSONObject(json);
		// actionReportCode = jsonObject.getInt("code");
		// ULog.v(TAG, "actionReport--result=" + json);
		// } catch (TVException e) {
		// ULog.e(TAG, "actionReport error=" + e.getMessage());
		// } catch (JSONException e) {
		// e.printStackTrace();
		// ULog.e(TAG, "---actionReport error=" + e.getMessage());
		// } finally {
		// if (null != handler)
		// handler.sendEmptyMessage(1);
		// if (0 == actionReportCode) {
		// // 上报成功，清除list
		// getActionList().clear();
		// }
		// }
		// }
		// }).start();
	}

	public static BaseApplication getInstance() {
		if (myApplication == null) {
			myApplication = new BaseApplication();
			return myApplication;
		}
		return myApplication;
	}

	public void onCreate() {
		super.onCreate();
		ULog.i("BASE APP-->" + Thread.currentThread().getId());
		getDeviceInfo();
		// myApplication = this;
		// actionList = new ArrayList<ActionReport>();
		//
		// /* 上报异常错误功能，禁止关闭 */
		// // debug模式关闭
		// if(!ULog.isDug){
		// Thread.setDefaultUncaughtExceptionHandler(MyUnCaughtExceptionHandler.getInstance(getApplicationContext()));
		// }
	}

	private void getDeviceInfo() {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);

		if (dm.widthPixels < dm.heightPixels) {
			// 竖屏
			DeviceInfo.SCREEN_WIDTH_PORTRAIT = dm.widthPixels;
			DeviceInfo.SCREEN_HEIGHT_PORTRAIT = dm.heightPixels;

			DeviceInfo.SCREEN_HEIGHT_LANDSCAPE = dm.widthPixels;
			DeviceInfo.SCREEN_WIDTH_LANDSCAPE = dm.heightPixels;
		}
		else {
			// 横屏
			DeviceInfo.SCREEN_WIDTH_PORTRAIT = dm.heightPixels;
			DeviceInfo.SCREEN_HEIGHT_PORTRAIT = dm.widthPixels;

			DeviceInfo.SCREEN_HEIGHT_LANDSCAPE = dm.heightPixels;
			DeviceInfo.SCREEN_WIDTH_LANDSCAPE = dm.widthPixels;
		}
		DeviceInfo.DENSITYDPI = dm.densityDpi;
		DeviceInfo.DENSITY = dm.density;
		ULog.d("setupBaseData, SCREEN_WIDTH = " + dm.widthPixels + ", SCREEN_HEIGHT = " + dm.heightPixels
				+ ", densityDpi = " + dm.densityDpi);
	}

}
