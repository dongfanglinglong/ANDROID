package com.dongfang.daohang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.dongfang.daohang.fragment.ActivityFragment;
import com.dongfang.daohang.fragment.DetailFragment;
import com.dongfang.daohang.fragment.FloorFragment;
import com.dongfang.daohang.fragment.Shops2Fragment;
import com.dongfang.daohang.fragment.UserFragment;
import com.dongfang.utils.ULog;
import com.dongfang.utils.User;
import com.dongfang.v4.app.BaseActivity;
import com.dongfang.v4.app.FragmentTabHostDF;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 
 * @author dongfang
 *
 */
public class MainDaohangActivity extends BaseActivity {

	@ViewInject(android.R.id.tabhost)
	private FragmentTabHostDF fgtHost;

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	private Context context;
	public static int tab = 0;
	
	private Bundle data; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_daohang);
		context = this;
		initData(getIntent());
		// ViewUtils.inject(this);
		initTabhostItems();
	}

	/** 获取首页数据 */
	private void initData(Intent intent) {
		ULog.d("initData");
		
		if (null != getIntent() && null != getIntent().getExtras() && getIntent().getExtras().containsKey("result")) {
			data = getIntent().getExtras();
		}
		
		
	}

	/** 初始化底部菜单 */
	@SuppressLint("NewApi")
	private void initTabhostItems() {
		ULog.d("initTabhostItems");
		fgtHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

		TextView tab1 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab2 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
//		TextView tab3 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab4 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab, null);
		TextView tab5 = (TextView) getLayoutInflater().inflate(R.layout.activity_main_tab_2, null);

		tab1.setText("楼层");
		tab2.setText("商户");
//		tab3.setText("活动");
		tab4.setText("我的");
		tab5.setText("详情");

		tab1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mian_activity_tab_5, 0, 0);
		tab2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mian_activity_tab_1, 0, 0);
//		tab3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mian_activity_tab_2, 0, 0);
		tab4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mian_activity_tab_3, 0, 0);
		tab5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mian_activity_tab_4, 0, 0);

		fgtHost.getTabWidget().setDividerDrawable(null);
		fgtHost.addTab(fgtHost.newTabSpec("0").setIndicator(tab1), FloorFragment.class, data);
		// fgtHost.addTab(fgtHost.newTabSpec("1").setIndicator(tab2), ShopListFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("1").setIndicator(tab2), Shops2Fragment.class, null);
//		fgtHost.addTab(fgtHost.newTabSpec("2").setIndicator(tab3), ActivityFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("3").setIndicator(tab4), UserFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("4").setIndicator(tab5), DetailFragment.class, null);

		fgtHost.setOnBeforeChangeTab(new FragmentTabHostDF.OnBeforeChangeTab() {
			@Override
			public int onBeforeChangeTab(int index) {
				if (3 == index && !User.isLogined(context)) {
					context.startActivity(new Intent(context, UserLRLoginActivity.class));
					tab = 0;
					return index;
				}
				tab = index;
				return -1;
			}
		});

		title.setText("楼层");
		fgtHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				switch (Integer.valueOf(tabId)) {
				case 0:
					title.setText("楼层");
					break;
				case 1:
					title.setText("商户");
					break;
				case 2:
					title.setText("活动");
					break;
				case 3:
					title.setText("我的");
					break;
				case 4:
					title.setText("详情");
					break;
				default:
					break;
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		fgtHost.setCurrentTab(tab);
	}

	@OnClick({ R.id.top_bar_btn_back })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_btn_back:
			finish();
			break;
		}
	}

}
