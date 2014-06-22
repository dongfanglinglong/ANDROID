package com.dongfang.v4.app;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/***
 * 重写FragmentTabHost
 * 
 * @author dongfang
 * 
 */
public class FragmentTabHostDF extends FragmentTabHost {

	public FragmentTabHostDF(Context context) {
		this(context, null);
	}

	public FragmentTabHostDF(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void setCurrentTab(int index) {
		if (null != beforeChangeTab) {
			int i = beforeChangeTab.onBeforeChangeTab(index);
			if (i > -1)
				return;
		}
		super.setCurrentTab(index);
	}

	private OnBeforeChangeTab beforeChangeTab;

	public OnBeforeChangeTab getOnBeforeChangeTab() {
		return beforeChangeTab;
	}

	public void setOnBeforeChangeTab(OnBeforeChangeTab beforeChangeTab) {
		this.beforeChangeTab = beforeChangeTab;
	}

	public interface OnBeforeChangeTab {
		public int onBeforeChangeTab(int index);
	}

}
