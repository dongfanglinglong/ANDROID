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

public class TextNavAdapter extends BaseAdapter {

	private List<com.dongfang.daohang.beans.TextNavBean> list;

	private LayoutInflater inflater;// 动态布局映射

	public TextNavAdapter(Context context, List<com.dongfang.daohang.beans.TextNavBean> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	public List<com.dongfang.daohang.beans.TextNavBean> getList() {
		return list;
	}

	public void setList(List<com.dongfang.daohang.beans.TextNavBean> list) {
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
			v = inflater.inflate(R.layout.activity_takeme_text_navigation_adp_item, null);

		ImageView logo = (ImageView) v.findViewById(R.id.imageView1);
		TextView floor = (TextView) v.findViewById(android.R.id.text1);

		if (0 == position) {
			logo.setBackgroundResource(R.drawable.icon_text_nav_0);
		}
		else if ((getCount() - 1) == position) {
			logo.setBackgroundResource(R.drawable.icon_text_nav_2);
		}
		else{
			logo.setBackgroundResource(R.drawable.icon_text_nav_3);
		}

		floor.setText(list.get(position).getText());;

		return v;
	}

}
