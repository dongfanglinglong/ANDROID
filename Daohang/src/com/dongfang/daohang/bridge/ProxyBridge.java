package com.dongfang.daohang.bridge;

import android.content.Context;
import android.webkit.WebView;

import com.dongfang.daohang.MainDaohangActivity;
import com.dongfang.daohang.TakeMeActivity;
import com.dongfang.daohang.TakeMeSelectActivity;
import com.dongfang.daohang.dialog.RecordDialog;
import com.dongfang.utils.ULog;
import com.dongfang.utils.User;

/**
 * 和页面互调接口
 * 
 * @author dongfang
 *
 */
public class ProxyBridge {
	private Context context;
	private WebView webView;

	public ProxyBridge(Context context, WebView webView) {
		this.context = context;
		this.webView = webView;
	}

	// ----------调用js接口------------------
	/**
	 * 
	 * @param name
	 * @param type
	 *            1-设置起始点, 2-设置结束点, 3-当前位置（扫码）
	 */
	public void setPosition(String name, int type) {
		webView.loadUrl("javascript:page_setPosition('" + name + "','" + type + "')");
	}

	/**
	 * 
	 * 获取文字路径，会回调方法 {@link setRoadTextList}
	 * 
	 * @param type
	 *            0:最短 ；1：路过洗手间； 2：不坐电梯
	 */
	public void getRoadTextList(int type) {
		webView.loadUrl("javascript:page_getRoadTextList(" + type + ")");
	}

	/**
	 * 显示atm等信息
	 */
	public void setShowSpecialArea() {
		webView.loadUrl("javascript:page_setShowArea('1')");
	}

	/**
	 * 导航时，显示上一个下一个位置点
	 * 
	 * @param floor
	 * @param x
	 * @param y
	 */
	public void nextRoadTextList(String floor, String x, String y) {
		webView.loadUrl("javascript:page_nextRoadTextList('" + floor + "','" + x + "','" + y + "')");
	}

	// ----------js转本地接口------------------
	/**
	 * 设置起点和终点
	 * 
	 * @param json
	 */
	public void sendAreaInfo(String json) {
		if ((context instanceof TakeMeSelectActivity)
				&& null != ((TakeMeSelectActivity) context).getOnSelectAreaListener()) {
			((TakeMeSelectActivity) context).getOnSelectAreaListener().onSelected(json, "ProxyBridge");
			((TakeMeSelectActivity) context).finish();
		}
		else if (context instanceof MainDaohangActivity) {
			RecordDialog.show(context, json).show();
		}
	}

	/**
	 * 显示文字导航
	 * 
	 * @param s
	 */
	public void showTextNavigation(String s) {
		if ((context instanceof TakeMeActivity)) {
			((TakeMeActivity) context).initTextNavigation(s);
		}
	}

	// ----------js调用本地接口------------------
	public String getToken() {
		return User.getToken(context);
	}

	/**
	 * gps 位置 | 二维码的？
	 * 
	 * @return
	 */
	public String getPosition() {
		return "";
	}

	/**
	 * 设置文字路径
	 * 
	 * @param jsons
	 */
	public void setRoadTextList(String jsons) {
		ULog.d(jsons);

	}

	/**
	 * 打开activity
	 * 
	 * @param type
	 */
	public void openActivity(int type, String jsons) {
		ULog.d(jsons);
	}

	/**
	 * 
	 * @param func
	 * @param params
	 */
	public void send(String func, String params) {
		ULog.d(func + "(" + params + ")");

		if ("page_getRoadTextList".equalsIgnoreCase(func) && !"undefined".equalsIgnoreCase(params)) {
			showTextNavigation(params);
		}
		else if ("page_getAreaInfo".equalsIgnoreCase(func) && !"undefined".equalsIgnoreCase(params)) {
			sendAreaInfo(params);
		}
	}
}
