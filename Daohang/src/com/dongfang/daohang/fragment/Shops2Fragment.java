package com.dongfang.daohang.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dongfang.daohang.R;
import com.dongfang.daohang.TakeMeSelectActivity;
import com.dongfang.v4.app.BaseFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 商户
 * 
 * @author dongfang
 *
 */
public class Shops2Fragment extends BaseFragment {
	View view;

	
	@ViewInject(R.id.top_bar_search_et_input)
	private EditText inputEditText;
	
	private ShopListFragment shoplistFragment;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null)
				parent.removeView(view);
		}

		view = inflater.inflate(R.layout.fragment_shops_2, container, false);
		ViewUtils.inject(this, view);

		shoplistFragment = (ShopListFragment) getFragmentManager().findFragmentById(
				R.id.fragment_shops_2_shoplist);

		
		
		
		inputEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					Bundle data = new Bundle();
					data.putString("name", s.toString());
					shoplistFragment.refresh(getActivity(), data);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		Bundle data = new Bundle();
		data.putString("name", "");
		shoplistFragment.refresh(getActivity(), data);

		return view;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Fragment f = getFragmentManager().findFragmentById(R.id.fragment_shops_2_shoplist);
		if (f != null)
			getFragmentManager().beginTransaction().remove(f).commit();
	}

	@Override
	public void onClick(View v) {}

}
