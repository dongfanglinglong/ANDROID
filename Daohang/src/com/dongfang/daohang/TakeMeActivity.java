package com.dongfang.daohang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dongfang.daohang.beans.AreaBean;
import com.dongfang.daohang.beans.TextNavBean;
import com.dongfang.daohang.bridge.ProxyBridge;
import com.dongfang.daohang.fragment.adp.TextNavAdapter;
import com.dongfang.daohang.params.ComParams;
import com.dongfang.daohang.views.MyWebView;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseActivity;
import com.dongfang.v4.app.MCaptureActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class TakeMeActivity extends BaseActivity {

	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;
	@ViewInject(R.id.dialog_goto_tv_qidian)
	private TextView qidian;
	@ViewInject(R.id.dialog_goto_tv_zhongdian)
	private TextView zhongdian;

	@ViewInject(R.id.activity_takeme_rl_navigation)
	private RelativeLayout rlTextNavigation;
	@ViewInject(R.id.activity_takeme_text_navigation_listview)
	private ListView lvTextNavigation;

	private ProxyBridge proxyBridge;
	private MyWebView webView;
	private ArrayList<TextNavBean> listData;
	private TextNavAdapter textNavAdapter;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_takeme);
		context = this;

		title.setText("带我去");
		webView = new MyWebView(context);
		proxyBridge = new ProxyBridge(context, webView);
		webView.addJavascriptInterface(proxyBridge);
		webView.loadUrl(ComParams.BASE_URL);
		webView.setOnLoadCompleted(new MyWebView.OnLoadCompleted() {
			@Override
			public void toDO() {
				if (start.containsKey("name") && start.containsKey("areaId")) {
					qidian.setText(start.get("name"));
					proxyBridge.setPosition(start.get("areaId"), 1);
				}

			}
		});

		textNavAdapter = new TextNavAdapter(context, listData);
		lvTextNavigation.setAdapter(textNavAdapter);

		if (null != getIntent() && null != getIntent().getExtras() && getIntent().getExtras().containsKey("result")) {
			String s = getIntent().getExtras().getString("result");
			ULog.d("---> " + s);

			if (s.startsWith("http") && s.contains("&s=")) {
				start.put("name", "我的位置");
				start.put("areaId", s.substring(s.indexOf("&s=") + 3));
			}
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK && null != data && data.hasExtra("result")) {
			switch (requestCode) {
			case ComParams.REQUESTCODE_START_QR:
			case ComParams.REQUESTCODE_START:
				analyticJson(1, data.getStringExtra("result"), data.getStringExtra("from"));
				qidian.setText(start.get("name"));
				proxyBridge.setPosition(start.get("areaId"), 1);
				break;
			case ComParams.REQUESTCODE_END:
				analyticJson(2, data.getStringExtra("result"), data.getStringExtra("from"));
				zhongdian.setText(end.get("name"));
				proxyBridge.setPosition(end.get("areaId"), 2);
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("requestCode = ").append(requestCode);
		sb.append("\n").append("resultCode = ").append(resultCode);
		if (null != data && data.hasExtra("from"))
			sb.append("\n").append("from = ").append(data.getStringExtra("from"));
		if (null != data && data.hasExtra("result"))
			sb.append("\n").append("result = ").append(data.getStringExtra("result"));
		ULog.e(sb.toString());
	}

	private Map<String, String> start = new HashMap<String, String>();
	private Map<String, String> end = new HashMap<String, String>();

	private void analyticJson(int type, String json, String from) {
		JSONObject jsonObject;
		try {
			if (1 == type) {
				jsonObject = new JSONObject(json);
				start.put("areaId", jsonObject.getString("areaId"));
				if (jsonObject.has("shopName") && !TextUtils.isEmpty(jsonObject.getString("shopName")))
					start.put("name", jsonObject.getString("shopName"));
				else if (jsonObject.has("areaName") && !TextUtils.isEmpty(jsonObject.getString("areaName")))
					start.put("name", jsonObject.getString("areaName"));
				else if (jsonObject.has("areaname") && !TextUtils.isEmpty(jsonObject.getString("areaname")))
					start.put("name", jsonObject.getString("areaname"));
			}
			else if (2 == type) {
				jsonObject = new JSONObject(json);
				end.put("areaId", jsonObject.getString("areaId"));
				if (jsonObject.has("shopName") && !TextUtils.isEmpty(jsonObject.getString("shopName")))
					end.put("name", jsonObject.getString("shopName"));
				else if (jsonObject.has("areaName") && !TextUtils.isEmpty(jsonObject.getString("areaName")))
					end.put("name", jsonObject.getString("areaName"));
				else if (jsonObject.has("areaname") && !TextUtils.isEmpty(jsonObject.getString("areaname")))
					end.put("name", jsonObject.getString("areaname"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public void initTextNavigation(String s) {
		Message msg = new Message();
		msg.what = 1;
		msg.obj = s;
		mHandler.sendMessage(msg);
	}

	@OnClick({ R.id.top_bar_btn_back, R.id.dialog_goto_im_qr, R.id.dialog_goto_tv_qidian,
			R.id.dialog_goto_tv_zhongdian, R.id.activity_takeme_btn_1, R.id.activity_takeme_btn_2,
			R.id.activity_takeme_btn_3, R.id.activity_takeme_text_navigation_btn_share,
			R.id.activity_takeme_text_navigation_btn_goto })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.dialog_goto_im_qr:
			startActivityForResult(new Intent(this, MCaptureActivity.class), ComParams.REQUESTCODE_START_QR);
			break;
		case R.id.dialog_goto_tv_qidian:
			startActivityForResult(new Intent(this, TakeMeSelectActivity.class), ComParams.REQUESTCODE_START);
			break;
		case R.id.dialog_goto_tv_zhongdian:
			startActivityForResult(new Intent(this, TakeMeSelectActivity.class), ComParams.REQUESTCODE_END);
			break;
		case R.id.activity_takeme_btn_1:
			proxyBridge.getRoadTextList(0);
			break;
		case R.id.activity_takeme_btn_2:
			proxyBridge.getRoadTextList(1);
			break;
		case R.id.activity_takeme_btn_3:
			proxyBridge.getRoadTextList(2);
			break;
		case R.id.activity_takeme_text_navigation_btn_share: {
			Uri uri = Uri.parse("smsto:");
			Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", ComParams.BASE_URL + "&s=" + start.get("areaId") + "&e=" + end.get("areaId"));
			context.startActivity(intent);
		}
			break;
		case R.id.activity_takeme_text_navigation_btn_goto: {
			Intent resultIntent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("start", start.get("areaId"));
			bundle.putString("end", end.get("areaId"));
			bundle.putParcelableArrayList("textlist", listData);
			resultIntent.putExtras(bundle);
			setResult(RESULT_OK, resultIntent);
			finish();
		}
			break;
		case R.id.top_bar_btn_back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && rlTextNavigation.isShown()) {
			rlTextNavigation.setVisibility(View.GONE);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);

			ULog.d(msg.obj.toString());
			listData = new Gson().fromJson(msg.obj.toString(), new TypeToken<List<TextNavBean>>() {}.getType());
			textNavAdapter.setList(listData);
			textNavAdapter.notifyDataSetChanged();

			// List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
			// for (TextNavBean tnb : listData) {
			// // ULog.d(tnb.toString());
			// HashMap<String, String> map = new HashMap<String, String>();
			// map.put("text", tnb.getText());
			// data.add(map);
			// }
			//
			// SimpleAdapter adpAdapter = new SimpleAdapter(TakeMeActivity.this, data,
			// R.layout.activity_takeme_text_navigation_adp_item, new String[] { "text" }, new int[] {
			// android.R.id.text1 });
			//
			// lvTextNavigation.setAdapter(adpAdapter);
			rlTextNavigation.setVisibility(View.VISIBLE);

		}

	};

}
