package com.dongfang.daohang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dongfang.daohang.beans.BaseEntity;
import com.dongfang.daohang.beans.ShopInfoBean;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.dialog.ProgressDialog;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.dongfang.v4.app.BaseActivity;
import com.dongfang.views.MyImageView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

/***
 * 商户详情
 * 
 * @author dongfang
 *
 */
public class ShopInfoActivity extends BaseActivity {
	@ViewInject(R.id.top_bar_tv_title)
	private TextView title;

	@ViewInject(R.id.activity_shopinfo_name)
	private TextView name;
	@ViewInject(R.id.activity_shopinfo_tel)
	private TextView tel;
	@ViewInject(R.id.activity_shopinfo_address)
	private TextView address;
	@ViewInject(R.id.activity_shopinfo_tv_description)
	private TextView description;
	@ViewInject(R.id.activity_shopinfo_iv_logo)
	private MyImageView ivLogo;

	private ShopInfoBean shopInfo;
	private int placeId = -1, shopId = -1;

	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopinfo);
		context = this;

		title.setText("优衣库");

		Intent intent = getIntent();
		if (null != intent) {
			title.setText(intent.getStringExtra("title"));
			placeId = intent.getIntExtra("placeId", placeId);
			shopId = intent.getIntExtra("shopId", shopId);
		}

		if (null == shopInfo && null != savedInstanceState && savedInstanceState.containsKey("shopInfo")) {
			shopInfo = savedInstanceState.getParcelable("shopInfo");
		}
		init(shopInfo);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable("shopInfo", shopInfo);
		super.onSaveInstanceState(outState);

	}

	@Override
	protected void onStart() {
		super.onStart();

		if (null == shopInfo && -1 != placeId && -1 != shopId) {
			new HttpUtils().send(HttpMethod.GET, HttpActions.getShop(this, placeId, shopId),
					new RequestCallBack<String>() {
						ProgressDialog progressBar = ProgressDialog.show(context);

						@Override
						public void onStart() {
							progressBar.show();
							super.onStart();
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							progressBar.dismiss();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							progressBar.dismiss();

							try {
								shopInfo = JsonAnalytic.getInstance().analyseJsonTInfoDF(arg0.result, ShopInfoBean.class);
								ULog.d(shopInfo.toString());
							} catch (DFException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							init(shopInfo);
						}

					});
		}
	}

	private void init(ShopInfoBean shopInfo) {
		if (null == shopInfo)
			return;

		title.setText(shopInfo.getName());
		name.setText(shopInfo.getName());
		tel.setText(shopInfo.getTel());
		address.setText(shopInfo.getAddress());
		description.setText(shopInfo.getDescription());
		ivLogo.setImage(shopInfo.getLogo());

	}

	@OnClick({ R.id.top_bar_btn_back, R.id.activity_shopinfo_btn_addCollect, R.id.activity_shopinfo_btn_addrecord })
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.top_bar_btn_back:
			finish();
			break;
		case R.id.activity_shopinfo_btn_addCollect: {
			if (-1 == placeId)
				return;
			new HttpUtils().send(HttpMethod.GET, HttpActions.addCollect(context, placeId, 1),
					new RequestCallBack<String>() {
						ProgressDialog progressBar = ProgressDialog.show(context);

						@Override
						public void onStart() {
							progressBar.show();
							super.onStart();
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							progressBar.dismiss();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							progressBar.dismiss();
							try {
								BaseEntity baseEntity = JsonAnalytic.getInstance(context).analyseJsonTDF(arg0.result,
										BaseEntity.class);

								Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show();

							} catch (DFException e) {
								Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
							}

						}
					});
		}
			break;
		case R.id.activity_shopinfo_btn_addrecord: {
			if (-1 == placeId)
				return;
			new HttpUtils().send(HttpMethod.GET, HttpActions.addRecord(context, shopInfo.getAreaCode(), placeId),
					new RequestCallBack<String>() {
						ProgressDialog progressBar = ProgressDialog.show(context);

						@Override
						public void onStart() {
							progressBar.show();
							super.onStart();
						}

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							progressBar.dismiss();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							progressBar.dismiss();
							try {
								BaseEntity baseEntity = JsonAnalytic.getInstance().analyseJsonTDF(arg0.result,
										BaseEntity.class);

								Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show();

							} catch (DFException e) {
								Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
							}

						}
					});
		}
			break;
		default:
			break;
		}
	}
}
