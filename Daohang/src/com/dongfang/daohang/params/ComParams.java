package com.dongfang.daohang.params;

public class ComParams {

	public static final String WEBVIEW_404 = "file:///android_asset/404.html";

	private static final String HTTP_BASE = "http://211.149.200.227:30002";
	public static final String HTTP_URL = HTTP_BASE + "/www/interface/index.php";

	public static final String BASE_URL = "http://211.149.200.227:30001/web/mapsn.php?m=6&hide=5";

	public static final int BASE_PLACEID = 12;

	/** startActivityForResult的第二个参数，用于获取起点 */
	public static final int REQUESTCODE_START_QR = 0x00FF;
	public static final int REQUESTCODE_START = 0x00FE;
	/** startActivityForResult的第二个参数，用于获取终点 */
	public static final int REQUESTCODE_END = 0x00FD;

}
