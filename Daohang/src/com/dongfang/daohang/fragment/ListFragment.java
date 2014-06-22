package com.dongfang.daohang.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongfang.daohang.MainDaohangActivity;
import com.dongfang.daohang.R;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseFragment;
import com.dongfang.v4.app.MCaptureActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ListFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		ViewUtils.inject(this, view);
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		StringBuilder sb = new StringBuilder();
		sb.append("requestCode = ").append(requestCode);
		sb.append("\n").append("resultCode = ").append(resultCode);
		if (null != data && data.hasExtra("result"))
			sb.append("\n").append("result = ").append(data.getStringExtra("result"));
		ULog.e(sb.toString());
		if (resultCode == Activity.RESULT_OK && requestCode == 0x00f0 && null != data && data.hasExtra("result")) {
			Intent intent = new Intent(getActivity(), MainDaohangActivity.class);
			intent.putExtras(data.getExtras());
			startActivity(new Intent(getActivity(), MainDaohangActivity.class));
		}
	}

	@OnClick({ R.id.fragment_list_top_bar_iv_qr })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_list_top_bar_iv_qr:
			startActivityForResult(new Intent(getActivity(), MCaptureActivity.class), 0x00f0);
			break;
		}
	}

}
