package com.dongfang.daohang.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dongfang.daohang.params.ComParams;
import com.dongfang.utils.ULog;

public class MyWebViewClient extends WebViewClient {
	private long ltime = 0; // 统计页面载入时间
	@Override
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		ULog.d("onReceivedError");
		/**
		 * 1.平台无法访问 2.客户端网络断开
		 */
		view.stopLoading();
		view.loadUrl(ComParams.WEBVIEW_404);
	}

	@Override
	public void onLoadResource(WebView view, String url) {
		ULog.d("onLoadResource " + url);
		super.onLoadResource(view, url);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		ULog.d("onPageFinished " + url + " " + (System.currentTimeMillis() - ltime) + " ms");
		super.onPageFinished(view, url);
		view.getSettings().setBlockNetworkImage(false); // 取消 把图片加载放在最后来加载渲
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		//
		ULog.d("onPageStarted " + url);
		super.onPageStarted(view, url, favicon);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		ULog.d("url = " + url);
		if (url.toLowerCase().startsWith("rtsp://") || url.contains(":8210")) {
			// 第三方页面播放
			/**
			 * 1）启动播放器 2）播放器条件： 1.webview处于一直隐藏 2.webview不执行loadurl() 3.播放完毕，显示加载图片
			 */
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			view.getContext().startActivity(intent);
			return true;
		}

		view.stopLoading();
		view.loadUrl(url);
		return true;
	}

}
