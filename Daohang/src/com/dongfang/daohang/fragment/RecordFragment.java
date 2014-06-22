package com.dongfang.daohang.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.RecordBean;
import com.dongfang.daohang.beans.RecordEntity;
import com.dongfang.daohang.fragment.adp.RecordAdapter;
import com.dongfang.daohang.interf.OnSelectAreaListener;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.dialog.ProgressDialog;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 记录点
 * 
 * @author dongfang
 *
 */
public class RecordFragment extends BaseFragment {
	@ViewInject(R.id.fragment_record_list)
	private ListView listView;

	private RecordAdapter adapter;
	private List<RecordBean> listData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_record, container, false);
		ViewUtils.inject(this, view);
		//
		// ArrayList<Point> list = new ArrayList<Point>();
		// for (int i = 0; i < 10; i++) {
		// list.add(new Point());
		// }
		//
		// listView.setAdapter(new RecordAdapter(getActivity(), list));

		listData = new ArrayList<RecordBean>();
		adapter = new RecordAdapter(getActivity(), listData);
		listView.setAdapter(adapter);
		listView.setDivider(null);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ULog.d(new Gson().toJson(listData.get(position)));

				if (getParentFragment() instanceof Shops1Fragment) {
					onSelectAreaListener = ((Shops1Fragment) getParentFragment()).getOnSelectAreaListener();
				}

				if (onSelectAreaListener != null) {
					onSelectAreaListener.onSelected(new Gson().toJson(listData.get(position)),
							RecordFragment.class.getSimpleName());
				}
			}
		});

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		new HttpUtils().send(HttpMethod.GET, HttpActions.getRecords(getActivity(), 10, 1, 10),
				new RequestCallBack<String>() {
					ProgressDialog progress = ProgressDialog.show(getActivity());

					@Override
					public void onStart() {
						super.onStart();
						progress.show();
						ULog.d(this.getRequestUrl());
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						progress.dismiss();
						ULog.d(responseInfo.result);

						try {
							RecordEntity recodeEntity = JsonAnalytic.getInstance().analyseJsonTInfoDF(
									responseInfo.result, RecordEntity.class);

							if (null == listData)
								listData = new ArrayList<RecordBean>();

							listData.addAll(recodeEntity.getRecords());
							adapter.setList(listData);
							adapter.notifyDataSetChanged();

							// ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
							// for (RecordBean bean : recodeEntity.getRecords()) {
							// Map<String, String> map = new HashMap<String, String>();
							// map.put("t", bean.toString());
							// data.add(map);
							// }
							//
							// SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
							// android.R.layout.simple_list_item_1, new String[] { "t" },
							// new int[] { android.R.id.text1 });
							//
							// listView.setAdapter(adapter);
						} catch (DFException e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						progress.dismiss();
					}

				});

	}

	private OnSelectAreaListener onSelectAreaListener;

	public OnSelectAreaListener getOnSelectAreaListener() {
		return onSelectAreaListener;
	}

	public void setOnSelectAreaListener(OnSelectAreaListener onSelectAreaListener) {
		this.onSelectAreaListener = onSelectAreaListener;
	}

	@Override
	public void onClick(View v) {}

}
