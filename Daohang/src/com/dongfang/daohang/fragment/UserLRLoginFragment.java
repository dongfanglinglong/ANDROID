package com.dongfang.daohang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dongfang.daohang.MainActivity;
import com.dongfang.daohang.MainDaohangActivity;
import com.dongfang.daohang.R;
import com.dongfang.daohang.UserLRRegisterActivity;
import com.dongfang.daohang.beans.UserBean;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.dialog.ProgressDialog;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.utils.User;
import com.dongfang.v4.app.BaseFragment;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 用户登录注册
 * 
 * @author dongfang
 *
 */
public class UserLRLoginFragment extends BaseFragment {
	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@ViewInject(R.id.fragment_userlr_login_et_name)
	private EditText etName;
	@ViewInject(R.id.fragment_userlr_login_et_psw)
	private EditText etPsw;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_userlr_login, container, false);
		ViewUtils.inject(this, view);
		title.setText("登录");

		return view;
	}

	protected boolean isInputed() {
		return (!TextUtils.isEmpty(etName.getText().toString().trim()) && !TextUtils.isEmpty(etPsw.getText().toString()
				.trim()));
	}

	@OnClick({ R.id.fragment_userlr_login_bt_register, R.id.fragment_userlr_login_bt_login, R.id.top_bar_btn_back })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_btn_back:
			getActivity().finish();
			break;
		case R.id.fragment_userlr_login_bt_login: {
			if (!isInputed()) {
				Toast.makeText(getActivity(), "请输入用户名和密码", Toast.LENGTH_SHORT).show();
				return;
			}

			new HttpUtils().send(HttpMethod.GET,
					HttpActions.login(etName.getText().toString().trim(), etPsw.getText().toString().trim()),
					new RequestCallBack<String>() {

						ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "登录中...");

						@Override
						public void onStart() {
							super.onStart();
							ULog.d(this.getRequestUrl());
							progressDialog.show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							progressDialog.dismiss();
							ULog.d(responseInfo.result);
							try {
								UserBean user = JsonAnalytic.getInstance().analyseJsonTInfoDF(responseInfo.result,
										UserBean.class);
								ULog.d(user.toString());
								User.saveToken(getActivity(), user.getUserToken());
								User.saveUserId(getActivity(), user.getId());
								User.savePhone(getActivity(), user.getMobile());
								User.saveUserNickname(getActivity(), user.getNickName());
								User.savaUserName(getActivity(), etName.getText().toString().trim());

								MainActivity.tab = MainDaohangActivity.tab = 3;
								getActivity().finish();

							} catch (DFException e) {
								e.printStackTrace();

								Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();

							}

						}

						@Override
						public void onFailure(HttpException error, String msg) {
							progressDialog.dismiss();
							ULog.e(error.toString());

						}
					});
		}
			break;
		case R.id.fragment_userlr_login_bt_register:
			getActivity().startActivity(new Intent(getActivity(), UserLRRegisterActivity.class));
			break;

		default:
			break;
		}

	}
}
