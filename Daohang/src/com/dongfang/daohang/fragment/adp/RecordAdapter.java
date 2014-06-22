package com.dongfang.daohang.fragment.adp;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.RecordBean;

public class RecordAdapter extends BaseAdapter {

	private List<com.dongfang.daohang.beans.RecordBean> list;

	private LayoutInflater inflater;// 动态布局映射

	public RecordAdapter(Context context, List<RecordBean> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public List<com.dongfang.daohang.beans.RecordBean> getList() {
		return list;
	}

	public void setList(List<com.dongfang.daohang.beans.RecordBean> list) {
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
		if (null == v)
			v = inflater.inflate(R.layout.fragment_record_item, null);
		TextView name = (TextView) v.findViewById(R.id.fragment_record_item_name);
		TextView detail = (TextView) v.findViewById(R.id.fragment_record_item_detail);
		TextView time = (TextView) v.findViewById(R.id.fragment_record_item_time);

		name.setText(position + ":" + list.get(position).getAreaname());
		detail.setText(list.get(position).getAreaname());
		time.setText(list.get(position).getCreateTime());

		return v;
	}

}
