package com.dongfang.daohang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dongfang.daohang.beans.BaseEntity;
import com.dongfang.daohang.dialog.MMAlert;
import com.dongfang.daohang.dialog.MMAlert.OnAlertSelectId;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.dialog.ProgressDialog;
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

/**
 * 关注
 * 
 * @author dongfang
 *
 */
public class UserInfoActivity extends BaseActivity {

	@ViewInject(R.id.top_bar_btn_back)
	private View v;
	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@ViewInject(R.id.activity_user_info_rl_header)
	private RelativeLayout rlHeader;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		title.setText("个人中心");
		context = UserInfoActivity.this;
	}

	@OnClick({ R.id.activity_user_info_rl_header, R.id.activity_user_info_rl_nickname,
			R.id.activity_user_info_rl_phonenumber, R.id.activity_user_info_rl_username,
			R.id.activity_user_info_rl_userpsw, R.id.top_bar_btn_back, R.id.activity_user_info_rl_usersex,
			R.id.fragment_userinfo_btn_logout })
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (R.id.activity_user_info_rl_header == id) {
			MMAlert.showAlert(this, null, new String[] { "拍照", "相册" }, null, new OnAlertSelectId() {
				@Override
				public void onClick(int whichButton) {
					ULog.d("whichButton = " + whichButton);
				}

			});
			return;
		}
		else if (id == R.id.fragment_userinfo_btn_logout) {
			new HttpUtils().send(HttpMethod.GET, HttpActions.logout(context), new RequestCallBack<String>() {
				ProgressDialog progressDialog = ProgressDialog.show(context, "登出中...");

				@Override
				public void onStart() {
					super.onStart();
					ULog.d(this.getRequestUrl());
					progressDialog.show();
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					progressDialog.dismiss();

					try {
						BaseEntity entity = JsonAnalytic.getInstance().analyseJsonT(responseInfo.result,
								BaseEntity.class);
						if (0 == entity.getCode()) {
							User.saveToken(context, "");
							User.clearUserInfo(context);
							Toast.makeText(context, "登出成功", Toast.LENGTH_SHORT).show();
							finish();
							MainActivity.tab = 0;
						}

						else {
							Toast.makeText(context, entity.getMsg() + "(" + entity.getCode() + ")", Toast.LENGTH_LONG)
									.show();

							if (1002 == entity.getCode()) {
								User.saveToken(context, "");
								startActivity(new Intent(context, UserLRLoginActivity.class));
								finish();
							}
						}

					} catch (DFException e) {
						e.printStackTrace();

					}
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					progressDialog.dismiss();
				}
			});
			return;
		}
		else if (id == R.id.top_bar_btn_back) {
			finish();
			return;
		}

		Intent intent = new Intent(this, UserInfoModifyActivity.class);
		switch (id) {
		case R.id.activity_user_info_rl_nickname:
			intent.putExtra("type", UserInfoModifyActivity.TYPE_FRAGMENT_NICKNAME);
			intent.putExtra("title", "修改昵称");
			break;
		case R.id.activity_user_info_rl_phonenumber:
			intent.putExtra("type", UserInfoModifyActivity.TYPE_FRAGMENT_USERPHONENUM);
			intent.putExtra("title", "修改手机号码");
			break;
		case R.id.activity_user_info_rl_username:
			intent.putExtra("type", UserInfoModifyActivity.TYPE_FRAGMENT_USERNAME);
			intent.putExtra("title", "修改用户名");
			break;
		case R.id.activity_user_info_rl_userpsw:
			intent.putExtra("type", UserInfoModifyActivity.TYPE_FRAGMENT_USERPSW);
			intent.putExtra("title", "修改密码");
			break;
		case R.id.activity_user_info_rl_usersex:
			intent.putExtra("type", UserInfoModifyActivity.TYPE_FRAGMENT_USERSEX);
			intent.putExtra("title", "修改性别");
			break;
		default:
			break;
		}
		startActivity(intent);

	}
}
