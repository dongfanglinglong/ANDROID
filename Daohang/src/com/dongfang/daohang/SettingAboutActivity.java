package com.dongfang.daohang;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 意见反馈
 * 
 * @author dongfang
 *
 */
public class SettingAboutActivity extends BaseActivity {

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_about);
		title.setText("关于我们");
	}

	@OnClick({ R.id.top_bar_btn_back })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_btn_back:
			finish();
			break;
		}
	}

}
