package com.dongfang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.dongfang.daohang.R;

/**
 * 进度条对话框
 * 
 * @author chenwansong
 * 
 */
public class ProgressDialog extends Dialog {

	private static ProgressDialog myProgressDialog;

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	/**
	 * 
	 * @param context
	 *            需要显示的文字 ；传入null或者""则不显示文字
	 * @param message
	 * @return
	 */
	public static ProgressDialog show(Context context, String title, String message, boolean cancelable) {
		myProgressDialog = new ProgressDialog(context, R.style.CustomProgressDialog);
		myProgressDialog.setContentView(R.layout.progress_dialog);
		myProgressDialog.setCancelable(cancelable);
		myProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		TextView tv_message = (TextView) myProgressDialog.findViewById(R.id.tv_message);
		if (null == message || "".equals(message))
			tv_message.setVisibility(View.GONE);
		else
			tv_message.setText(message);
		return myProgressDialog;
	}

	public static ProgressDialog show(Context context, String title, String message) {
		return show(context, title, message, false);
	}

	public static ProgressDialog show(Context context, String message) {
		return show(context, "", message, false);
	}

	public static ProgressDialog show(Context context) {
		return show(context, "");
	}

	@Override
	public void cancel() {
		super.cancel();
		myProgressDialog = null;
	}

	@Override
	public void dismiss() {
		super.dismiss();
		myProgressDialog = null;
	}

	// @Override
	// public void onWindowFocusChanged(boolean hasFocus) {
	// super.onWindowFocusChanged(hasFocus);
	// // if (myProgressDialog != null) {
	// // ImageView imageView = (ImageView) myProgressDialog.findViewById(R.id.loadingImageView);
	// // AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
	// // animationDrawable.start();
	// // }
	// }
}