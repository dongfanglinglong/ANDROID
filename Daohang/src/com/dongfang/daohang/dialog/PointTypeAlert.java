package com.dongfang.daohang.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dongfang.daohang.R;
import com.dongfang.daohang.dialog.MMAlert.OnAlertSelectId;

public class PointTypeAlert {

	private PointTypeAlert() {}

	@SuppressLint("ResourceAsColor")
	public static Dialog show(final Context context, final String[] items, final OnAlertSelectId alertDo) {
		final Dialog dlg = new Dialog(context, R.style.MMTheme_DataSheet);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		HorizontalScrollView layout = (HorizontalScrollView) inflater.inflate(R.layout.dialog_pointtype, null);
		final LinearLayout ll = (LinearLayout) layout.findViewById(R.id.dialog_pointtype_ll);
		LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-2, -2);
		llp.setMargins(5, 5, 5, 5);

		for (String s : items) {
			TextView view = new TextView(context);
			view.setText(s);
			view.setTextSize(18);
			view.setLayoutParams(llp);
			ll.addView(view);
		}

		Window w = dlg.getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.x = 0;
		final int cMakeBottom = -1000;
		lp.y = cMakeBottom;
		lp.gravity = Gravity.TOP;
		dlg.onWindowAttributesChanged(lp);
		dlg.setContentView(layout);
		dlg.show();
		return dlg;
	}
}

class PointTypeAdapter extends BaseAdapter {
	private Context context;
	private String[] items = new String[10];

	public PointTypeAdapter(Context context, String[] items) {
		this.context = context;
		this.items = items;
		// System.arraycopy(items, 0, this.items, 0, items.length);
	}

	@Override
	public int getCount() {
		return items.length;
	}

	@Override
	public Object getItem(int position) {
		return items[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (null == convertView) {
			TextView view = new TextView(context);
			view.setText(items[position]);
			view.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
			convertView = view;
		}
		((TextView) convertView).setText(items[position]);

		return convertView;
	}
}