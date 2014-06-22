package com.dongfang.daohang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.AreaBean;
import com.dongfang.daohang.interf.OnSelectAreaListener;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseFragment;
import com.dongfang.v4.app.FragmentTabHostDF;
import com.google.gson.Gson;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 商户
 * 
 * @author dongfang
 *
 */
public class Shops1Fragment extends BaseFragment {

	@ViewInject(android.R.id.tabhost)
	private FragmentTabHostDF fgtHost;
	@ViewInject(R.id.horizontalscrollview)
	private HorizontalScrollView horizontalscrollview;
	@ViewInject(R.id.fragment_shops_1_ll)
	private LinearLayout ll;

	private String[][] items = new String[][] { { "男卫生间", "女卫生间", "紧急逃生口", "扶行梯", "ATM" },
			{ "A-14-1", "A-14-0", "A-16", "A-17", "A-13" } };

	// {'areatype':'A-14-1','name':'男卫生间'},
	// {'areatype':'A-14-0','name':'女卫生间'},
	// {'areatype':'A-16','name':'紧急逃生口'},
	// {'areatype':'A-17','name':'扶行梯'},
	// {'areatype':'A-13','name':'ATM'},

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_shops_1, container, false);
		ViewUtils.inject(this, v);

		init(inflater);
		return v;
	}

	private void init(LayoutInflater inflater) {
		fgtHost.setup(getActivity(), getChildFragmentManager(), android.R.id.tabcontent);

		TextView tab1 = (TextView) inflater.inflate(R.layout.fragment_shop_tab, null);
		TextView tab2 = (TextView) inflater.inflate(R.layout.fragment_shop_tab, null);
		TextView tab3 = (TextView) inflater.inflate(R.layout.fragment_shop_tab, null);

		tab1.setText("我的记录点");
		tab2.setText("地图选择");
		tab3.setText("功能设施");
		tab1.setBackgroundResource(R.drawable.btn_tab_0);
		tab2.setBackgroundResource(R.drawable.btn_tab_0);
		tab3.setBackgroundResource(R.drawable.btn_tab_1);

		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(0, -2);
		lp1.setMargins(10, 10, 0, 10);
		lp1.weight = 1f;
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(0, -2);
		lp2.setMargins(0, 10, 0, 10);
		lp2.weight = 1f;
		LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(0, -2);
		lp3.setMargins(0, 10, 10, 10);
		lp3.weight = 1f;

		tab1.setLayoutParams(lp1);
		tab2.setLayoutParams(lp2);
		tab3.setLayoutParams(lp3);

		ULog.d(tab3.getLayoutParams().height + "");

		fgtHost.addTab(fgtHost.newTabSpec("0").setIndicator(tab1), RecordFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("1").setIndicator(tab2), MapFragment.class, null);
		fgtHost.addTab(fgtHost.newTabSpec("2").setIndicator(tab3), MapFragment.class, null);

		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-2, -2);
		llp.setMargins(10, 5, 5, 5);

		for (String s : items[0]) {
			// TextView view = new TextView(getActivity());
			TextView view = (TextView) inflater.inflate(R.layout.fragment_shops_1_item, null);
			view.setText(s);
			view.setLayoutParams(llp);
			// view.setTextSize(18);
			// view.setTextColor(Color.parseColor("black"));
			ll.addView(view);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					ULog.d("-------------- " + ((TextView) v).getText());
					if (null == onSelectAreaListener)
						return;

					AreaBean bean = new AreaBean();

					if (((TextView) v).getText().equals(items[0][0])) {
						bean.setAreaName(items[0][0]);
						bean.setAreaId(items[1][0]);
					}
					else if (((TextView) v).getText().equals(items[0][1])) {
						bean.setAreaName(items[0][1]);
						bean.setAreaId(items[1][1]);
					}
					else if (((TextView) v).getText().equals(items[0][2])) {
						bean.setAreaName(items[0][2]);
						bean.setAreaId(items[1][2]);
					}
					else if (((TextView) v).getText().equals(items[0][3])) {
						bean.setAreaName(items[0][3]);
						bean.setAreaId(items[1][3]);
					}
					else if (((TextView) v).getText().equals(items[0][4])) {
						bean.setAreaName(items[0][4]);
						bean.setAreaId(items[1][4]);
					}

					onSelectAreaListener.onSelected(new Gson().toJson(bean), Shops1Fragment.class.getSimpleName());

				}
			});
		}

		fgtHost.setOnBeforeChangeTab(new FragmentTabHostDF.OnBeforeChangeTab() {
			@Override
			public int onBeforeChangeTab(int index) {
				if (2 == index) {
					horizontalscrollview.setVisibility(horizontalscrollview.isShown() ? View.GONE : View.VISIBLE);
					return 1;
				}
				else {
					horizontalscrollview.setVisibility(View.GONE);
				}
				return -1;
			}
		});

		fgtHost.setCurrentTab(1);
		// fgtHost.getCurrentTabTag();
		// getChildFragmentManager().findFragmentByTag(fgtHost.getCurrentTabTag());
	}

	private OnSelectAreaListener onSelectAreaListener;

	public OnSelectAreaListener getOnSelectAreaListener() {
		return onSelectAreaListener;
	}

	public void setOnSelectAreaListener(OnSelectAreaListener onSelectAreaListener) {
		this.onSelectAreaListener = onSelectAreaListener;

		// for (Fragment f : getChildFragmentManager().getFragments()) {
		// getChildFragmentManager().findFragmentByTag("0");
		//
		// ULog.d(f.getTag() + " ==== " + f.getClass().getSimpleName());
		// }

	}

	@Override
	public void onClick(View v) {}

}
