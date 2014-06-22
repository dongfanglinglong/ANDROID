package com.dongfang.daohang;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.dongfang.daohang.views.MyWebView;
import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class WapActivity extends BaseActivity {

	@ViewInject(R.id.activity_wap_webview)
	private MyWebView webView;

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wap);
		title.setText("上海");
		
		
		String url = TextUtils.isEmpty(getIntent().getStringExtra("url")) ? "http://www.baidu.com/" : getIntent()
				.getStringExtra("url");
		webView.loadUrl(url);

	}

	@OnClick(R.id.top_bar_btn_back)
	@Override
	public void onClick(View v) {
		finish();
	}

}
