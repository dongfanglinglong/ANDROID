package com.dongfang.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class TextView extends android.widget.TextView {
	public TextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public TextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public TextView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable[] drawables = getCompoundDrawables();
		if (drawables != null) {
			Drawable drawableLeft = drawables[0];
			if (drawableLeft != null) {
				float textWidth = getPaint().measureText(getText().toString());
				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = 0;
				drawableWidth = drawableLeft.getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				canvas.translate((getWidth() - bodyWidth) / 2, 0);
			}
		}
		super.onDraw(canvas);
	}
}
