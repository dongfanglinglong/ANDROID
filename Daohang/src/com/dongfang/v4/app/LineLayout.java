package com.dongfang.v4.app;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.dongfang.utils.ULog;

/**
 * 内容会自动换行
 * 
 * @author dongfang
 * 
 */
public class LineLayout extends ViewGroup {
	private final static int	VIEW_MARGIN	= 5;

	public LineLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public LineLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		ULog.d("getChildCount =  " + getChildCount());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// ULog.d("widthMeasureSpec = " + widthMeasureSpec +
		// ",heightMeasureSpec =  " + heightMeasureSpec);
		int r = getMeasuredWidth();
		// ULog.d("getMeasuredWidth = " + r);

		int row = 0;// which row lay you view relative to parent
		int lengthX = 0; // right position of child relative to parent
		int lengthY = 0; // bottom position of child relative to parent

		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);

			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();
			lengthX += width + VIEW_MARGIN;
			if (lengthX > r) {
				lengthX = width + VIEW_MARGIN;
				row++;
			}
			lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
		}

		setMeasuredDimension(widthMeasureSpec, lengthY + VIEW_MARGIN);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// ULog.d("changed = " + changed + ",l =  " + l + ",t =  " + t +
		// ",r =  " + r + ",b =  " + b);

		final int count = getChildCount();
		int row = 0;// which row lay you view relative to parent
		int lengthX = l; // right position of child relative to parent
		int lengthY = t; // bottom position of child relative to parent
		for (int i = 0; i < count; i++) {
			final View child = this.getChildAt(i);
			int width = child.getMeasuredWidth();
			int height = child.getMeasuredHeight();

			lengthX += width + VIEW_MARGIN;
			lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
			// if it can't drawing on a same line , skip to next line
			if (lengthX > r) {
				lengthX = width + VIEW_MARGIN + l;
				row++;
				lengthY = row * (height + VIEW_MARGIN) + VIEW_MARGIN + height;
			}
			child.layout(lengthX - width, lengthY - height, lengthX, lengthY);

			// ULog.d("lengthX - width = " + (lengthX - width) +
			// ",lengthY - height =  " + (lengthY - height)
			// + ",lengthX =  " + lengthX + ",lengthY =  " + lengthY);
		}
	}

	public int getSelectPosition() {
		final int count = getChildCount();
		int position = 0;
		for (int i = 0; i < count; i++) {
			
			if (position == 0 && getChildAt(i).isSelected())
				position = i;
			else
				getChildAt(i).setSelected(false);;
		}
		ULog.i("position-->" + position);
		return position;
	}

	public void setNOSelect() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).setSelected(false);
		}
	}

	public void setNOEnable(List<Integer> indexPosition) {
		final int count = getChildCount();
		
		for (int i = 0; i < count; i++) {
			if (indexPosition.get(i)!=i) {
				indexPosition.add(i, -1);
				getChildAt(i).setEnabled(false);
				getChildAt(i).setSelected(false);
			}else
				getChildAt(i).setEnabled(true);
		}
	}

}
