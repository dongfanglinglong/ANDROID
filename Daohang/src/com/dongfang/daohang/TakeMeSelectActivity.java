package com.dongfang.daohang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dongfang.daohang.fragment.ShopListFragment;
import com.dongfang.daohang.fragment.Shops1Fragment;
import com.dongfang.daohang.interf.OnSelectAreaListener;
import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

@SuppressLint("CommitTransaction")
public class TakeMeSelectActivity extends BaseActivity {

	@ViewInject(R.id.top_bar_search_iv_cancel)
	private ImageView cancel;
	@ViewInject(R.id.top_bar_search_et_input)
	private EditText inputEditText;

	private Fragment selectFragment;
	private Fragment searchFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_takeme_select);

		// selectFragment = new Shops1Fragment();
		// searchFragment = new ShopListFragment();
		selectFragment = getSupportFragmentManager().findFragmentById(R.id.activity_takeme_select_fragment);
		searchFragment = getSupportFragmentManager().findFragmentById(R.id.activity_takeme_search_fragment);

		// getSupportFragmentManager().beginTransaction().add(R.id.activity_takeme_fragment_contain, selectFragment)
		// .commit();

		onSelectAreaListener = new OnSelectAreaListener() {

			@Override
			public void onSelected(String result, String from) {
				Intent resultIntent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("result", result);
				bundle.putString("from", from);
				resultIntent.putExtras(bundle);
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		};

		((Shops1Fragment) selectFragment).setOnSelectAreaListener(onSelectAreaListener);
		((ShopListFragment) searchFragment).setOnSelectAreaListener(onSelectAreaListener);

		inputEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (TextUtils.isEmpty(s)) {
					getSupportFragmentManager().beginTransaction().show(selectFragment).commit();
				}
				else {
					Bundle data = new Bundle();
					data.putString("name", s.toString());
					((ShopListFragment) searchFragment).refresh(TakeMeSelectActivity.this, data);
					getSupportFragmentManager().beginTransaction().hide(selectFragment).commit();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void afterTextChanged(Editable s) {}
		});

	}

	@OnClick({ R.id.top_bar_search_iv_cancel })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_search_iv_cancel:
			// Intent resultIntent = new Intent();
			// Bundle bundle = new Bundle();
			// bundle.putString("result", "0987654321");
			// resultIntent.putExtras(bundle);
			// setResult(RESULT_OK, resultIntent);

			finish();
			break;

		default:
			break;
		}
	}

	private OnSelectAreaListener onSelectAreaListener;

	public OnSelectAreaListener getOnSelectAreaListener() {
		return onSelectAreaListener;
	}

	public void setOnSelectAreaListener(OnSelectAreaListener onSelectAreaListener) {
		this.onSelectAreaListener = onSelectAreaListener;
	}

}
