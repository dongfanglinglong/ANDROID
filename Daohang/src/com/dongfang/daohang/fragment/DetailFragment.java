package com.dongfang.daohang.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dongfang.daohang.CarportActivity;
import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.ActivityBean;
import com.dongfang.daohang.beans.ActivityEntity;
import com.dongfang.daohang.beans.PlaceBean;
import com.dongfang.daohang.fragment.adp.DetailActivityAdapter;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.daohang.params.ComParams;
import com.dongfang.dialog.ProgressDialog;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseFragment;
import com.dongfang.v4.app.DeviceInfo;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class DetailFragment extends BaseFragment {

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;
	@ViewInject(R.id.top_bar_btn_back)
	private View vBack;

	@ViewInject(R.id.fragment_detail_tv_around_carport)
	private TextView tvArroudCarport;
	@ViewInject(R.id.fragment_detail_carport)
	private TextView tvCarport;
	@ViewInject(R.id.fragment_detail_listview_activity)
	private ListView lvActivity;
	@ViewInject(R.id.fragment_detail_tv_detail)
	private TextView tvDetail;

	private PlaceBean placeBean = null;
	private List<ActivityBean> listData;
	private DetailActivityAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_detail, container, false);
		ViewUtils.inject(this, v);

		title.setText("详情");
		vBack.setVisibility(View.INVISIBLE);

		tvDetail.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
				DeviceInfo.SCREEN_WIDTH_PORTRAIT * 246 / 480));

		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey("bean")) {
				placeBean = savedInstanceState.getParcelable("bean");
			}

			if (savedInstanceState.containsKey("list")) {
				listData = savedInstanceState.getParcelableArrayList("list");
			}
		}

		if (null == listData) {
			listData = new ArrayList<ActivityBean>();
		}

		adapter = new DetailActivityAdapter(this.getActivity(), listData);
		lvActivity.setAdapter(adapter);

		init(placeBean);

		return v;
	}

	@Override
	public void onStart() {
		super.onStart();

		if (null != placeBean) {
			init(placeBean);
			return;
		}

		new HttpUtils().send(HttpMethod.GET, HttpActions.getPlace(ComParams.BASE_PLACEID),
				new RequestCallBack<String>() {

					ProgressDialog progressDialog = ProgressDialog.show(getActivity());

					@Override
					public void onStart() {
						super.onStart();
						ULog.d(this.getRequestUrl());
						progressDialog.show();

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						progressDialog.dismiss();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						progressDialog.dismiss();
						ULog.d(arg0.result);
						try {
							placeBean = JsonAnalytic.getInstance().analyseJsonTInfoDF(arg0.result, PlaceBean.class);

							ULog.d(placeBean.toString());

							init(placeBean);
						} catch (DFException e) {
							e.printStackTrace();
						}

					}
				});

		if (null == listData || listData.size() < 1) {
			getActivity(-10);
			getActivity(1);
		}

	}

	public void onSaveInstanceState(Bundle outState) {

		outState.putParcelable("bean", placeBean);
		outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) listData);

		super.onSaveInstanceState(outState);
	}

	private void init(PlaceBean placeBean2) {
		if (null == placeBean2)
			return;
		int i = Integer.valueOf(placeBean.getCarportCount()) - Integer.valueOf(placeBean.getUseCarportCount());
		tvCarport.setText("剩余车位\n" + i + "/" + placeBean.getCarportCount());
	}

	private synchronized void add(List<ActivityBean> list) {
		for (ActivityBean bean : list) {
			listData.add(bean);
		}
		adapter.setList(listData);
		adapter.notifyDataSetChanged();
		ULog.d("listLength = " + listData.size());
	}

	private void getActivity(final int type) {
		new HttpUtils().send(HttpMethod.GET, HttpActions.getActivitys(ComParams.BASE_PLACEID, 1, 5, type),
				new RequestCallBack<String>() {
					ProgressDialog progressDialog = ProgressDialog.show(getActivity());

					@Override
					public void onStart() {
						super.onStart();
						ULog.d(this.getRequestUrl());
						progressDialog.show();

					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						progressDialog.dismiss();
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						progressDialog.dismiss();
						ULog.d(arg0.result);
						try {
							ActivityEntity bean = JsonAnalytic.getInstance().analyseJsonTInfoDF(arg0.result,
									ActivityEntity.class);
							ULog.d(bean.toString());
							add(bean.getList());
						} catch (DFException e) {
							e.printStackTrace();
						}

					}
				});
	}

	@OnClick({ R.id.fragment_detail_tv_around_carport })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_detail_tv_around_carport:
			getActivity().startActivity(new Intent(getActivity(), CarportActivity.class));
			break;
		}

	}

}
