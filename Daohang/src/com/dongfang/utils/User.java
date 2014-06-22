package com.dongfang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.dongfang.encrypt.AESEncrypt;

/** 用户相关信息 */
public class User {
	public static final String TAG = "User";

	// ------------------------------------------------------------------------------------------
	/** {@link HttpActions#loading(context)} 接口中的相关信息 */
	public static final String SHAREDPREFERENCES_ACCESS = "ACCESS";
	/** {@link HttpActions#loading(context)} 接口中返回的token值保存的key */
	public static final String SHAREDPREFERENCES_ACCESS_TOKEN = "ACCESS_TOKEN";
	public static String SHAREDPREFERENCES_ACCESS_TOKEN_NEW = "";
	public static String SHAREDPREFERENCES_ACCESS_TOKEN_OLD = "";
	/** {@link HttpActions#loading(context)} 接口中返回的phone保存的key */
	public static final String SHAREDPREFERENCES_ACCESS_PHONE = "ACCESS_PHONE";

	/** {@link HttpActions#login(context, uName, uPassword)} 接口中的相关信息 */
	public static final String SHAREDPREFERENCES_USER_INFO_FILENAME = "USER_INFO";
	/** {@link HttpActions#login(context, uName, uPassword)} 接口中用户名 */
	public static final String SHAREDPREFERENCES_USER_INFO_USERNAME = "USER_NAME";
	/** {@link HttpActions#login(context, uName, uPassword)} 接口中密码 */
	public static final String SHAREDPREFERENCES_USER_INFO_PASSWORD = "PASSWORD";
	/** {@link HttpActions#login(context, uName, uPassword)} 接口返回信息 */
	public static final String SHAREDPREFERENCES_USER_INFO_USER_ID = "USER_ID";
	/** {@link HttpActions#login(context, uName, uPassword)} 接口返回信息 */
	public static final String SHAREDPREFERENCES_USER_INFO_USER_BIND_ID = "USER_BIND_ID";
	/** 用户是否登陆 */
	public static final String SHAREDPREFERENCES_USER_INFO_ISLOGIN = "IS_LOGIN";
	/** 用户昵称 */
	public static final String SHAREDPREFERENCES_USER_INFO_NICKNAME = "NICKNAME";
	/** 用户头像 */
	public static final String SHAREDPREFERENCES_USER_INFO_HEADIMG = "HEAD_IMG";

	// ------------------------------------------------------------------------------------------

	/**
	 * 判断用户是否已经登录
	 * 
	 * @param context
	 * @return 已登录，返回true，否则，返回false
	 */
	public static boolean isLogined(Context context) {
		// SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME,
		// Context.MODE_PRIVATE);
		// return sp.getBoolean(SHAREDPREFERENCES_USER_INFO_ISLOGIN, false);
		//
		return !TextUtils.isEmpty(getToken(context));
	}

	/**
	 * 清楚用户登录信息
	 * 
	 * @param context
	 */
	public static void clearUserInfo(Context context) {
		saveUserId(context, "");
		saveUserPassword(context, "");
	}

	/**
	 * 更新token值
	 * 
	 * @param context
	 * @param token
	 * @return 更新是否成功
	 */

	public static synchronized boolean saveToken(Context context, String token) {
		ULog.d("saveToken token = " + token);
		return context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_ACCESS_TOKEN, token).commit();
	}

	/**
	 * 获取token值
	 * 
	 * @param context
	 * @return token, 如果不存在，这返回空串
	 */
	public static synchronized String getToken(Context context) {
		if (!TextUtils.isEmpty(SHAREDPREFERENCES_ACCESS_TOKEN_NEW)) {
			saveToken(context, SHAREDPREFERENCES_ACCESS_TOKEN_NEW);
			SHAREDPREFERENCES_ACCESS_TOKEN_NEW = "";
		}

		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE);
		return SHAREDPREFERENCES_ACCESS_TOKEN_OLD = sp.getString(SHAREDPREFERENCES_ACCESS_TOKEN, "");
	}

	/**
	 * 保存用户信息
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static boolean savaUserName(Context context, String name) {
		ULog.d("savaUserName name = " + name);
		return context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_USER_INFO_USERNAME, name).commit();

	}

	/**
	 * 获取账户
	 * 
	 * @param context
	 * @return username, 如果不存在，这返回空串
	 */
	public static String getUserName(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE);
		return sp.getString(SHAREDPREFERENCES_USER_INFO_USERNAME, "");
	}

	/**
	 * 更新password值
	 * 
	 * @param context
	 * @param password
	 * @return 更新是否成功
	 */
	public static boolean saveUserPassword(Context context, String password) {
		ULog.d("saveToken password = " + password);
		return context
				.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE)
				.edit()
				.putString(SHAREDPREFERENCES_USER_INFO_PASSWORD,
						AESEncrypt.encrypt(SHAREDPREFERENCES_USER_INFO_PASSWORD, password)).commit();

	}

	/**
	 * 获取password值
	 * 
	 * @param context
	 * @return password, 如果不存在，这返回空串
	 */
	public static String getUserPassword(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE);
		return AESEncrypt.decrypt(SHAREDPREFERENCES_USER_INFO_PASSWORD,
				sp.getString(SHAREDPREFERENCES_USER_INFO_PASSWORD, ""));
	}

	/**
	 * 更新手机号码
	 * 
	 * @param context
	 * @param phone
	 * @return 更新是否成功
	 */

	public static boolean savePhone(Context context, String phone) {
		ULog.d("savePhone phone = " + phone);
		return context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_ACCESS_PHONE, phone).commit();

	}

	/**
	 * 获取手机号码
	 * 
	 * @param context
	 * @return phone, 如果不存在，这返回空串
	 */
	public static String getPhone(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_ACCESS, Context.MODE_PRIVATE);
		return sp.getString(SHAREDPREFERENCES_ACCESS_PHONE, "");
	}

	/**
	 * 更新uid和userBindId值
	 * 
	 * @param context
	 * @param userId
	 * @param userBindId
	 * @return 更新是否成功
	 */
	public static boolean saveUserId(Context context, String userId, String userBindId) {
		ULog.d("saveUserId uid = " + userId + "BindId = " + userBindId);
		return context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_USER_INFO_USER_ID, userId)
				.putString(SHAREDPREFERENCES_USER_INFO_USER_BIND_ID, userBindId).commit();
	}

	/**
	 * 更新uid值
	 * 
	 * @param context
	 * @param userId
	 * @return 更新是否成功
	 */
	public static boolean saveUserId(Context context, String userId) {
		ULog.d("saveUserId uid = " + userId);
		return context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_USER_INFO_USER_ID, userId).commit();
	}

/**
	 * 获取uid值
	 * 
	 * @param context
	 * @return uid, 如果不存在，这返回空串
	 */
	public static String getUserId(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE);
		return sp.getString(SHAREDPREFERENCES_USER_INFO_USER_ID, "");
	}

/**  @deprecated see {@link #saveToken(Context, String)
	 * 
	 * 更新用户登陆状态
	 * 
	 * @param context
	 * @param isLogin
	 * @return 更新是否成功
	 */
	public static boolean saveUserLoginStatu(Context context, boolean isLogin) {
		// ULog.d( "saveUserLoginStatu isLogin = " + isLogin);
		// return context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE).edit()
		// .putBoolean(SHAREDPREFERENCES_USER_INFO_ISLOGIN, isLogin).commit();
		saveToken(context, "");
		return false;
	}

	/**
	 * 更新用户昵称
	 * 
	 * @param context
	 * @param nickname
	 * @return 更新是否成功
	 */
	public static boolean saveUserNickname(Context context, String nickname) {
		ULog.d("saveUserNickname nickname = " + nickname);
		return context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE).edit()
				.putString(SHAREDPREFERENCES_USER_INFO_NICKNAME, nickname).commit();
	}

	/**
	 * 更新用户头像
	 * 
	 * @param context
	 * @param imgid
	 * @return 更新是否成功
	 */
	public static boolean saveUserHeadimg(Context context, int imgid) {
		ULog.d("saveUserHeadimg imgid = " + imgid);
		return context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE).edit()
				.putInt(SHAREDPREFERENCES_USER_INFO_HEADIMG, imgid).commit();
	}

	/**
	 * 获取用户头像编号
	 * 
	 * @param context
	 * @return headImgid, 如果不存在，这返回空串
	 */
	public static int getUserHeadimgId(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE);
		return sp.getInt(SHAREDPREFERENCES_USER_INFO_HEADIMG, 0);
	}

	/**
	 * 获取用户昵称
	 * 
	 * @param context
	 * @return nickname, 如果不存在，这返回空串
	 */
	public static String getUserNickname(Context context) {
		SharedPreferences sp = context.getSharedPreferences(SHAREDPREFERENCES_USER_INFO_FILENAME, Context.MODE_PRIVATE);
		return sp.getString(SHAREDPREFERENCES_USER_INFO_NICKNAME, "");
	}

}
