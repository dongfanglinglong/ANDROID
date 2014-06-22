package com.dongfang.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dongfang.daohang.R;
import com.dongfang.utils.BitmapHelp;
import com.dongfang.utils.ULog;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;

/**
 * 网络下载 图
 * 
 * @author ericzhxi
 * 
 */
public class MyImageView extends RelativeLayout {
	public static final String TAG = MyImageView.class.getSimpleName();

	private Context context;
	private ImageView imageView;
	BitmapUtils bitmapUtils = null;
	BitmapDisplayConfig bigPicDisplayConfig = null;

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	public MyImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyImageView(Context context) {
		this(context, null);
	}

	public void init(Context context, AttributeSet attrs, int defStyle) {
		this.context = context;
		View view = LayoutInflater.from(context).inflate(R.layout.my_imageview, null);

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageView, defStyle, 0);
		Drawable d = a.getDrawable(R.styleable.ImageView_src);

		ULog.d("d != null " + (d != null));

		if (d != null && (view instanceof ImageView)) {
			imageView = (ImageView) view;
			imageView.setImageDrawable(d);
		}

		this.setGravity(Gravity.CENTER);
		addView(view, new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	/**
	 * 
	 * @param url
	 */
	public void setImage(String url) {

		if (TextUtils.isEmpty(url)) {
			imageView.setBackgroundColor(0xF5F5F5);
		}
		else {
			if (bitmapUtils == null) {
				bitmapUtils = BitmapHelp.getBitmapUtils(context);
			}

			bitmapUtils.display(imageView, url);
		}

	}

	public void setImage(String url, int maxWidth, int maxHeight) {

		if (TextUtils.isEmpty(url)) {
			imageView.setBackgroundColor(0xF5F5F5);
		}
		else {
			if (bitmapUtils == null) {
				bitmapUtils = BitmapHelp.getBitmapUtils(context);
			}
			if (bigPicDisplayConfig == null) {
				bigPicDisplayConfig = BitmapHelp.getBitmapDisplayConfig();
				// bigPicDisplayConfig.setShowOriginal(true);
				bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
			}
			BitmapSize bSize = new BitmapSize(maxWidth, maxHeight);
			bigPicDisplayConfig.setBitmapMaxSize(bSize);

			bitmapUtils.display(imageView, url, bigPicDisplayConfig);
		}

	}

	public void setImageResource(int res) {
		imageView.setImageResource(res);
	}

	public void setImageBitmap(Bitmap bm) {
		imageView.setImageBitmap(bm);
	}

	/*
	 * public void setBitmapDisplayConfig(int width, int height) { if (bigPicDisplayConfig == null) {
	 * bigPicDisplayConfig = BitmapHelp.getBitmapDisplayConfig(); //bigPicDisplayConfig.setShowOriginal(true);
	 * bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565); } BitmapSize bSize = new BitmapSize();
	 * bSize.setWidth(width); bSize.setHeight(height); bigPicDisplayConfig.setBitmapMaxSize(bSize); }
	 */
}