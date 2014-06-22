package com.dongfang.utils;

import android.content.Context;
import android.text.TextUtils;

/**
 * @author dongfang
 */
public class DFException extends Exception {
	public static final String TAG = DFException.class.getSimpleName();
	private static final long serialVersionUID = 475022994858770423L;
	private int statusCode = -1;
	private String msg;

	public DFException() {
		super();
	}

	public DFException(Context context, int statusCode) {
		super(getMsg(context, statusCode));
		this.statusCode = statusCode;
		ULog.e("TVException   statusCode : " + statusCode + " \n    Exception message  = "
				+ getMsg(context, statusCode));
		switch (statusCode) {
		default:
			return;
		case DFException.DOWNLOAD_IS_DOWNLOADING:
			// new DialogFactory(context).showToast("视频正在下载中，不用重复下载", Toast.LENGTH_SHORT);
			break;
		case DFException.DOWNLOAD_HAVA_DOWNLOADED:
			// new DialogFactory(context).showToast("视频已下载，不用重复下载", Toast.LENGTH_SHORT);
			break;
		case DFException.DOWNLOAD_NO_FILE_PATH:
			// new DialogFactory(context).showToast("文件路径为空", Toast.LENGTH_SHORT);
			break;
		}
	}

	public DFException(int statusCode) {
		super(getMsg(null, statusCode));
		ULog.e("TVException   statusCode : " + statusCode + " \n    Exception message  = " + getMsg(null, statusCode));
		this.statusCode = statusCode;
	}

	public DFException(String msg) {
		super(msg);
		ULog.e("TVException  message  = " + msg);
	}

	public DFException(Exception cause) {
		super(cause);
		ULog.e("TVException  cause  = " + cause.toString());
	}

	public DFException(Throwable throwable) {
		super(throwable);
		ULog.e("TVException  cause  = " + throwable.toString());
	}

	public DFException(String msg, int statusCode) {
		super(msg);
		if (917 == statusCode)
			msg = "请重新登录";
		this.statusCode = statusCode;
		ULog.e("TVException   statusCode : " + statusCode + " \n    Exception message  = " + msg);
	}

	public DFException(Exception cause, int statusCode) {
		super(cause);
		this.statusCode = statusCode;
		ULog.e("TVException   statusCode : " + statusCode + " \n    Exception cause  = " + cause.toString());

	}

	public DFException(String msg, Exception cause) {
		super(msg, cause);
		ULog.e("TVException   msg : " + msg + " \n    Exception cause  = " + cause.toString());

	}

	public DFException(String msg, Exception cause, int statusCode) {
		super(msg, cause);
		this.statusCode = statusCode;
		ULog.e("TVException   msg : " + msg + " \n    Exception cause  = " + cause.toString()
				+ "         statusCode  = " + statusCode);
	}

	public DFException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		ULog.e("TVException   detailMessage : " + detailMessage + "\n    Exception throwable  = "
				+ throwable.toString());
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return TextUtils.isEmpty(msg) ? super.getMessage() : msg;
	}

	public static String getMsg(Context context, int code) {
		// if (599 > code) {
		// return null == context ? "数据请求失败！" :
		// context.getString(R.string.exception_data_request_fail);
		// }
		// else if (899 < code && code < 920) {
		// return null == context ? "数据解析错误！" :
		// context.getString(R.string.exception_json_ananlytic_fail);
		// }
		// else

		if (919 < code && code < 940) {
			// if (code == HTTP_READ_IO_EXCEPTION || code == HTTP_POST_IO_EXCEPTION || code == HTTP_GET_IO_EXCEPTION) {
			// return null == context ? "网络链接超时！" : context.getString(R.string.exception_net_connect_timeout);
			// }
			// return null == context ? "网络链接失败！" : context.getString(R.string.exception_net_connect_fail);
		}
		return "";
	}

	@Override
	public String toString() {
		return getMessage() + " ( " + statusCode + " )";
	}

	// ------------- [900 , 919]
	public static final int AUTH_ID = 136;
	// --------------------------------------------------------------
	/** http请求下发信息中，有升级标识位，没有升级地址 */
	public static final int JSON_ANALYTIC_LACK_UPDATE_PATH = 900;
	/** http请求下发信息中，没有升级相关版本信息 */
	public static final int JSON_ANALYTIC_LACK_UPDATE_VERSION = 906;
	/** http请求下发信息中，没有升级相关升级标识位 */
	public static final int JSON_ANALYTIC_LACK_UPDATE_ID = 907;
	/** JsonAnalytic解析json时，解析字符串为空 */
	public static final int JSON_ANALYTIC_STRING_NULL = 901;
	/** json 解析时缺少token字段 */
	public static final int JSON_ANALYTIC_LACK_TOKEN = 902;
	/** json 解析时缺少uid字段 */
	public static final int JSON_ANALYTIC_LACK_UID = 903;
	/** json 解析时缺少绑定的用户ID字段 */
	public static final int JSON_ANALYTIC_LACK_BINDID = 904;
	/** json 解析时缺少info字段 */
	public static final int JSON_ANALYTIC_LACK_INFO = 905;
	/** json 解析时缺少data字段 */
	public static final int JSON_ANALYTIC_LACK_DATA = 906;

	// -------------- [920 , 939]
	// -------------------------------------------------------------
	public static final int HTTP_READ_IO_EXCEPTION = 920;
	public static final int HTTP_GET_HTTPS_URL_CONN_MALFORMED_URL_EXCEPTION = 921;
	public static final int HTTP_GET_HTTPS_URL_CONN_IO_EXCEPTION = 922;
	public static final int HTTP_GET_HTTPS_URL_CONN_NO_SUCH_ALG_EXCEPTION = 923;
	public static final int HTTP_GET_HTTPS_URL_CONN_KEYMANAGEMENT_EXCEPTION = 924;
	public static final int HTTP_GET_HTTP_URL_CONN_MALFORMED_URL_EXCEPTION = 925;
	public static final int HTTP_GET_HTTP_URL_CONN_IO_EXCEPTION = 926;
	public static final int HTTP_READ_HTTP_RESPONSE_IO_EXCEPTION = 927;

	public static final int HTTP_POST_UNSUPPORTED_ENCODING_EXCEPTION = 930;
	public static final int HTTP_POST_CLIENT_PROTOCOL_EXCEPTION = 931;
	public static final int HTTP_POST_IO_EXCEPTION = 932;
	public static final int HTTP_GET_CLIENT_PROTOCOL_EXCEPTION = 933;
	public static final int HTTP_GET_IO_EXCEPTION = 934;
	public static final int HTTP_GETINPUTSTRING_CLIENT_PROTOCOL_EXCEPTION = 935;
	public static final int HTTP_GETINPUTSTRING_IO_EXCEPTION = 936;
	// ---------------[940 , 959]
	// -------------------------------------------------------------
	public static final int ENCRYPT_NO_SUCH_PADDING_EXCEPTION = 940;
	public static final int ENCRYPT_NO_SUCH_ALGORITHM_EXCEPTION = 941;
	public static final int ENCRYPT_No_Such_Provider_Exception = 981;
	public static final int ENCRYPT_INVALID_KEY_EXCEPTION = 942;
	public static final int ENCRYPT_ILLEGAL_BLOCKSIZE_EXCEPTION = 943;
	public static final int ENCRYPT_BAD_PADDING_EXCEPTION = 944;

	public static final int DECRYPT_NO_SUCH_PADDING_EXCEPTION = 945;
	public static final int DECRYPT_NO_SUCH_ALGORITHM_EXCEPTION = 946;
	public static final int DECRYPT_INVALID_KEY_EXCEPTION = 947;
	public static final int DECRYPT_ILLEGAL_BLOCKSIZE_EXCEPTION = 948;
	public static final int DECRYPT_BAD_PADDING_EXCEPTION = 949;

	public static final int GETRAWKEY_NO_SUCH_ALGORITHM_EXCEPTION = 950;

	// ---------------[960 , 969]
	// ---------------------------------------------------------------
	public static final int DOWNLOAD_NO_STORAGE_SPACE = 960;
	public static final int DOWNLOAD_CONTENT_LENGTH_ZERO = 961;
	public static final int DOWNLOAD_FILE_NOT_FOUND_EXCEPTION = 962;
	public static final int DOWNLOAD_DOINBACKGROUND_IO_EXCEPTION = 963;
	public static final int DOWNLOAD_WRITE_IO_EXCEPTION = 964;
	public static final int DOWNLOAD_NO_FILE_PATH = 965;
	public static final int DOWNLOAD_HAVA_DOWNLOADED = 966;
	public static final int DOWNLOAD_IS_DOWNLOADING = 967;

	public static final int UTIL_ENCODERBYMD5_EXCEPTION = 980;

	// /** 登录返回后需要的解析的xml信息错误编号（下发错误导致解析错误，或者仅仅解析错误） */
	// public static final String LOGIN_RESULT_ERROR_XML_MSGMAP_NULL_CODE =
	// "900";
	//
	// /** 解压配置出错 */
	// public static final String LOGIN_RESULT_ERROR_UNZIP_FILE = "901";
	//
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少升级标识数据 */
	// public static final String LOGIN_RESULT_ERROR_XML_MSGMAP_NO_UPGRADETYPE =
	// "930";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少客户端升级地址 */
	// public static final String
	// LOGIN_RESULT_ERROR_XML_MSGMAP_NO_CLIENTUPDATEURI = "931";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少客户端升级信息 */
	// public static final String LOGIN_RESULT_ERROR_XML_MSGMAP_NO_RELEASENOTE =
	// "932";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少配置文件升级标识数据 */
	// public static final String
	// LOGIN_RESULT_ERROR_XML_MSGMAP_NO_ISCONFIGUPDATE = "933";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少配置文件升级地址 */
	// public static final String
	// LOGIN_RESULT_ERROR_XML_MSGMAP_NO_CONFIGUPDATEURI = "934";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少UUID标识数据 */
	// public static final String LOGIN_RESULT_ERROR_XML_MSGMAP_NO_UUID = "935";
	// /** 登录返回后需要的解析的xml信息解析正常，但是缺少QAS标识数据 */
	// public static final String LOGIN_RESULT_ERROR_XML_MSGMAP_NO_QAS = "936";

	// 0 成功
	// 1001 订购失败：该产品已经存在有效授权（订购关系已存在，建议直接授权用户使用）
	// 1005 针对退订和停机：无有效产品授权
	// 1006 该节目重复收藏
	// 1032 订购失败：无法计算有效CDR
	// 1033 ProductID无效
	// 1035 ContentID无效
	// 1036 业务鉴权失败 业务管理平台下发可订购的产品列表，触发订购流程
	// 1037 包月产品即将下线（离下线时间不到一个月），无法订购
	// 1038 订购失败：用户已订购必须退订关联产品后才能订购
	// 1052 CRM用户查询失败
	// 1053 CRM用户开通失败
	// 1054 退订产品时发现通过CRM订购不能退订
	// 2011 UserToken无效，提示用户重新开机
	// 2012
	// UserToken过期，发起UserToken更新（客户端接收到包含该字段的应答时，自动调用1.1接口，使用ReLogin作为action，刷新usertoken）
	// 3001 订购失败：产品不存在
	// 3005 订购失败：产品不是发布状态
	// 3006 订购失败：包月产品已经下线
	// 4001 用户上传空间未开通
	// 4002 用户上传内容空间不足
	// 4003 用户上传空间无写权限
	// 4004 用户上传空间无读权限
	// 5001 CRM接口调用失败
	// 6001 无法计算费用
	// 7001 用户未登录，需要登录
	// 7002 接口参数有误（做基本参数校验时，参数缺失或有误）
	// 7003 手机号码有误，提示用户输入正确的手机号码
	// 7004 短信验证码发送失败
	// 9001 参数不全
	// 9999 未知异常(一般是数据库错误)
	// 10000是数据库连接失败。

	// 10001 签名校验不通过
	//
	// 101 数据库操作失败
	// 102 缓存服务操作失败
	// 1001 参数缺少或错误
	// 1004 无权限执行该操作
	// 210001 该终端记录已存在
	// 210002 该终端记录不存在
	// 211001 该用户记录已存在
	// 211002 该用户记录不存在
	// 110001 该收藏记录已存在
	// 110002 该收藏记录不存在
	// 111001 该节目在播放列表中已存在
	// 111002 该节目在播放列表中不存在

}
