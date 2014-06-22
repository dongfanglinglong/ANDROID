package com.dongfang.daohang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dongfang.daohang.fragment.ActivityFragment;
import com.dongfang.daohang.fragment.HomeFragment;
import com.dongfang.daohang.fragment.ListFragment;
import com.dongfang.daohang.fragment.SettingFragment;
import com.dongfang.daohang.fragment.UserFragment;
import com.dongfang.utils.ULog;
import com.dongfang.utils.User;
import com.dongfang.v4.app.BaseActivity;
import com.dongfang.v4.app.FragmentTabHostDF;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 
 * @author dongfang
 *
 */
public class MainActivity extends BaseActivity {

	public static int tab = 0;

	@ViewInject(android.R.id.tabhost)
	private FragmentTabHostDF fgtHost;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		initData(getIntent());
		initTabhostItems();
	}

	/** 获取首页数据 */
	private void initData(Intent intent) {
		ULog.d("initData");
	}

	/** 初始化底部菜单 */
	@SuppressLint("NewApi")
	private void initTabhostItems() {
		ULog.d("initTabhostItems");
		fgtHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		TextView tab1 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab2 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab3 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab4 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab5 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab_2, null);

		tab1.setText("地图");
		tab2.setText("列表");
		tab3.setText("活动");
		tab4.setText("我的");
		tab5.setText("设置");

		tab1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mian_activity_tab_0, 0, 0, 0);
		tab2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mian_activity_tab_1, 0, 0, 0);
		tab3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mian_activity_tab_2, 0, 0, 0);
		tab4.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mian_activity_tab_3, 0, 0, 0);
		tab5.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mian_activity_tab_4, 0, 0, 0);

		fgtHost.getTabWidget().setDividerDrawable(null);

		fgtHost.addTab(fgtHost.newTabSpec("0").setIndicator(tab1), HomeFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("1").setIndicator(tab2), ListFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("2").setIndicator(tab3), ActivityFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("3").setIndicator(tab4), UserFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("4").setIndicator(tab5), SettingFragment.class, null);

		// User.saveToken(this, "25");

		fgtHost.setOnBeforeChangeTab(new FragmentTabHostDF.OnBeforeChangeTab() {
			@Override
			public int onBeforeChangeTab(int index) {
				if (3 == index && !User.isLogined(context)) {
					startActivity(new Intent(context, UserLRLoginActivity.class));
					tab = 0;
					return index;
				}
				tab = index;
				return -1;
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		fgtHost.setCurrentTab(tab);
	}

	@Override
	public void onClick(View v) {}

}
