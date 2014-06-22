package com.dongfang.daohang;

import android.os.Bundle;
import android.view.View;

import com.dongfang.utils.User;
import com.dongfang.v4.app.BaseActivity;

public class UserLRLoginActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlr_login);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (User.isLogined(this)) {
			MainActivity.tab = 3;
			finish();
		}
	}

	@Override
	public void onClick(View v) {}

}
