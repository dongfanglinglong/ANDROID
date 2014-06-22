package com.dongfang.daohang;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dongfang.daohang.beans.UserBean;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.utils.User;
import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class UserLRRegisterActivity extends BaseActivity {

	@ViewInject(R.id.activity_userlr_et_name)
	private EditText etName;
	@ViewInject(R.id.activity_userlr_et_psw)
	private EditText etPsw;
	@ViewInject(R.id.activity_userlr_et_phonenum)
	private EditText etPhonenum;

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userlr_register);
		title.setText("注册");

	}

	protected boolean isNotInputed() {
		return TextUtils.isEmpty(etName.getText().toString().trim())
				|| TextUtils.isEmpty(etPsw.getText().toString().trim())
				|| TextUtils.isEmpty(etPhonenum.getText().toString().trim());
	}

	@OnClick({ R.id.activity_userlr_bt_rigister, R.id.top_bar_btn_back })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_btn_back: {
			finish();
		}
			break;
		case R.id.activity_userlr_bt_rigister: {
			if (isNotInputed()) {
				Toast.makeText(UserLRRegisterActivity.this, "请输入用户名,密码和手机号码", Toast.LENGTH_SHORT).show();
				return;
			}

			final String name = etName.getText().toString().trim();
			String psw = etPsw.getText().toString().trim();
			String phone = etPhonenum.getText().toString().trim();

			new HttpUtils().send(HttpMethod.GET, HttpActions.register(name, psw, phone, "123456"),
					new RequestCallBack<String>() {

						@Override
						public void onStart() {
							super.onStart();
							ULog.d(getRequestUrl());
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							try {
								UserBean user = JsonAnalytic.getInstance().analyseJsonTInfoDF(responseInfo.result,
										UserBean.class);
								ULog.d(user.toString());
								User.saveToken(UserLRRegisterActivity.this, user.getUserToken());
								User.saveUserId(UserLRRegisterActivity.this, user.getId());
								User.savePhone(UserLRRegisterActivity.this, user.getMobile());
								User.saveUserNickname(UserLRRegisterActivity.this, user.getNickName());
								User.savaUserName(UserLRRegisterActivity.this, name);

								finish();

							} catch (DFException e) {
								Toast.makeText(UserLRRegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
								e.printStackTrace();
							}

						}

						@Override
						public void onFailure(HttpException error, String msg) {}
					});
		}
			break;
		}

	}
}
