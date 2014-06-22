package com.dongfang.daohang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dongfang.daohang.beans.CollectBean;
import com.dongfang.daohang.beans.CollectItem;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.dialog.ProgressDialog;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/**
 * 关注
 * 
 * @author dongfang
 *
 */
public class UserFollowActivity extends BaseActivity {
	@ViewInject(R.id.activity_user_follow_listview)
	private ListView listView;
	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_follow);

		title.setText("我的关注");
	}

	@Override
	protected void onStart() {
		super.onStart();

		new HttpUtils().send(HttpMethod.GET, HttpActions.getCollects(this, "1", 1, 10), new RequestCallBack<String>() {

			@Override
			public void onStart() {
				super.onStart();
				progress = ProgressDialog.show(UserFollowActivity.this);
				progress.show();

				ULog.d(this.getRequestUrl());
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				progress.dismiss();
				ULog.d(responseInfo.result);

				try {
					CollectBean collect = JsonAnalytic.getInstance().analyseJsonTInfoDF(responseInfo.result, CollectBean.class);
					ULog.d(collect.toString());

					ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
					for (CollectItem c : collect.getList()) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("t", c.toString());
						data.add(map);
					}

					SimpleAdapter adapter = new SimpleAdapter(UserFollowActivity.this, data,
							android.R.layout.simple_list_item_1, new String[] { "t" }, new int[] { android.R.id.text1 });

					listView.setAdapter(adapter);
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
