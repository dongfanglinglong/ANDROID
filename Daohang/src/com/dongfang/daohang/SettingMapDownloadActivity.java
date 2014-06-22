package com.dongfang.daohang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.dongfang.daohang.dialog.MMAlert;
import com.dongfang.daohang.dialog.MMAlert.OnAlertSelectId;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseActivity;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 意见反馈
 * 
 * @author dongfang
 *
 */
public class SettingMapDownloadActivity extends BaseActivity {

	@ViewInject(R.id.activity_setting_mapdownload_lists)
	private ListView listMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting_mapdownload);

		listMap.setAdapter(new SimpleAdapter(this, getData(), android.R.layout.simple_list_item_1,
				new String[] { "title" }, new int[] { android.R.id.text1 })

		);

		listMap.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

				MMAlert.showAlert(SettingMapDownloadActivity.this, null, new String[] { "下载" }, null,
						new OnAlertSelectId() {
							@Override
							public void onClick(int whichButton) {
								ULog.d(whichButton + " ----- " + position);
							}
						});
			}

		});
	}

	@Override
	public void onClick(View v) {}

	/* M 实现数据源 */
	protected ArrayList<Map<String, Object>> getData() {
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("info", "google1");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		/* 重写实例化， 获取第二批数据 */
		map = new HashMap<String, Object>();
		map.put("title", "G2");
		map.put("info", "google2");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		/* 重写实例化， 获取第二批数据 */
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google3");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		return list;
	}
}
