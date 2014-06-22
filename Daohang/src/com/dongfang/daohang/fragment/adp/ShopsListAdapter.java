package com.dongfang.daohang.fragment.adp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.dongfang.daohang.R;

public class ShopsListAdapter extends BaseAdapter {

	private List<com.dongfang.daohang.beans.AreaBean> list;

	private LayoutInflater inflater;// 动态布局映射

	public ShopsListAdapter(Context context, List<com.dongfang.daohang.beans.AreaBean> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public List<com.dongfang.daohang.beans.AreaBean> getList() {
		return list;
	}

	public void setList(List<com.dongfang.daohang.beans.AreaBean> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);

	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		CView cviwe = null;
		if (null == v) {
			v = inflater.inflate(R.layout.shops_adapter_item, null);
			cviwe = new CView();
			cviwe.name = (TextView) v.findViewById(R.id.shops_adapter_item_tv_name);
			cviwe.book = (TextView) v.findViewById(R.id.shops_adapter_item_tv_book);
			cviwe.distance = (TextView) v.findViewById(R.id.shops_adapter_item_tv_name);
			cviwe.ratingbar = (RatingBar) v.findViewById(R.id.shops_adapter_item_ratingbar);
			cviwe.percapita = (TextView) v.findViewById(R.id.shops_adapter_item_tv_percapita);
			cviwe.address = (TextView) v.findViewById(R.id.shops_adapter_item_tv_address);
			cviwe.characteristic = (TextView) v.findViewById(R.id.shops_adapter_item_tv_characteristic);
			cviwe.btnGoto = (Button) v.findViewById(R.id.shops_adapter_item_btn_goto);
			cviwe.btnTel = (Button) v.findViewById(R.id.shops_adapter_item_btn_tel);

			v.setTag(cviwe);
		}
		else {
			cviwe = (CView) v.getTag();
		}

		com.dongfang.daohang.beans.AreaBean bean = list.get(position);

		cviwe.name.setText((position + 1) + ". " + bean.getAreaName());

		return v;
	}

	private class CView {
		TextView name;
		TextView book;
		TextView distance;
		RatingBar ratingbar;
		TextView percapita;
		TextView address;
		TextView characteristic;
		Button btnGoto;
		Button btnTel;
	}

}
