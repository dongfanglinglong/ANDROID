package com.dongfang.utils;

import android.content.Context;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

/**
 * 
 * @author dongfang
 * 
 */
public class BitmapHelp {
	private BitmapHelp() {}

	private static BitmapUtils bitmapUtils;

	private static BitmapDisplayConfig bigPicDisplayConfig;

	/**
	 * BitmapUtils不是单例的 根据需要重载多个获取实例的方法
	 * 
	 * @param appContext
	 *            application context
	 * @return
	 */
	public static BitmapUtils getBitmapUtils(Context appContext) {
		if (bitmapUtils == null) {
			bitmapUtils = new BitmapUtils(appContext);
		}
		return bitmapUtils;
	}

	public static BitmapDisplayConfig getBitmapDisplayConfig() {
		if (bigPicDisplayConfig == null) {
			bigPicDisplayConfig = new BitmapDisplayConfig();
		}
		return bigPicDisplayConfig;
	}
}
