package com.dongfang.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.dongfang.constants.Constants;

/**
 * 能够兼容ViewPager的ScrollView
 * 
 * @Description: 解决了ViewPager在ScrollView中的滑动反弹问题
 * @author dongfang
 */
public class ScrollViewExtend extends ScrollView {
	// 滑动距离及坐标
	private float xDistance, yDistance, xLast, yLast;

	private boolean allow_match = true;

	public boolean isAllow_match() {
		return allow_match;
	}

	public void setAllow_match(boolean allow_match) {
		this.allow_match = allow_match;
	}

	public ScrollViewExtend(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (!allow_match && (getMeasuredHeight() * 2 > Constants.SCREEN_HEIGHT_PORTRAIT)) {
			setMeasuredDimension(getMeasuredWidth(), Constants.SCREEN_HEIGHT_PORTRAIT / 2);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDistance = yDistance = 0f;
			xLast = ev.getX();
			yLast = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			final float curX = ev.getX();
			final float curY = ev.getY();

			xDistance += Math.abs(curX - xLast);
			yDistance += Math.abs(curY - yLast);
			xLast = curX;
			yLast = curY;

			if (xDistance > yDistance) {
				return false;
			}
		}

		return super.onInterceptTouchEvent(ev);
	}
}
