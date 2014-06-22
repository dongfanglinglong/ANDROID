package com.dongfang.daohang.fragment.adp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongfang.daohang.R;

public class DetailActivityAdapter extends BaseAdapter {

	private List<com.dongfang.daohang.beans.ActivityBean> list;

	private LayoutInflater inflater;// 动态布局映射

	public DetailActivityAdapter(Context context, List<com.dongfang.daohang.beans.ActivityBean> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public List<com.dongfang.daohang.beans.ActivityBean> getList() {
		return list;
	}

	public void setList(List<com.dongfang.daohang.beans.ActivityBean> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
		return null == list ? 0 : list.size();
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
		if (null == v)
			v = inflater.inflate(R.layout.fragment_detail_activity_adp_item, null);

		((TextView) v).setText("    " + list.get(position).getName());
		if ("1".equals(list.get(position).getShopId())) {
			((TextView) v).setCompoundDrawablesWithIntrinsicBounds(R.drawable.activity_shops, 0, 0, 0);
		}
		else {
			((TextView) v).setCompoundDrawablesWithIntrinsicBounds(R.drawable.activity_market, 0, 0, 0);
		}

		return v;
	}

}
