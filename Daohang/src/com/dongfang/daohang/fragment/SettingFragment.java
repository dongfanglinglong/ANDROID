package com.dongfang.daohang.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dongfang.daohang.R;
import com.dongfang.daohang.SettingAboutActivity;
import com.dongfang.daohang.SettingFeedbackActivity;
import com.dongfang.daohang.SettingMapDownloadActivity;
import com.dongfang.daohang.dialog.MMAlert;
import com.dongfang.daohang.dialog.MMAlert.OnAlertSelectId;
import com.dongfang.v4.app.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class SettingFragment extends BaseFragment {

	@ViewInject(R.id.top_bar_btn_back)
	private View v;
	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container, false);
		ViewUtils.inject(this, view);

		v.setVisibility(View.INVISIBLE);
		title.setText("设置");

		return view;
	}

	@OnClick({ R.id.fragment_setting_tv_invite, R.id.fragment_setting_tv_map, R.id.fragment_setting_tv_about,
			R.id.fragment_setting_tv_evaluate, R.id.fragment_setting_tv_feedback,

	})
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_setting_tv_feedback:
			getActivity().startActivity(new Intent(getActivity(), SettingFeedbackActivity.class));
			break;
		case R.id.fragment_setting_tv_about:
			getActivity().startActivity(new Intent(getActivity(), SettingAboutActivity.class));
			break;
		case R.id.fragment_setting_tv_map:
			getActivity().startActivity(new Intent(getActivity(), SettingMapDownloadActivity.class));
			break;
		case R.id.fragment_setting_tv_invite: // 邀请好友
			MMAlert.showAlert(getActivity(), null, new String[] { "微信", "新浪微博", "微信朋友圈" }, null, new OnAlertSelectId() {
				@Override
				public void onClick(int whichButton) {}
			});
			break;
		case R.id.fragment_setting_tv_evaluate: // 评论
			try {
				Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(getActivity(), "Couldn't launch the market !", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}

	}

}
