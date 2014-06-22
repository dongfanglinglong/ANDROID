package com.dongfang.daohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongfang.daohang.R;
import com.dongfang.v4.app.BaseFragment;
import com.lidroid.xutils.ViewUtils;

/**
 * 商户
 * 
 * @author dongfang
 *
 */
public class ShopsFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shops, container,false);
		ViewUtils.inject(this, v);

		return v;
	}

	@Override
	public void onClick(View v) {}

}
