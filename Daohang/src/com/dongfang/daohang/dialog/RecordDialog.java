package com.dongfang.daohang.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dongfang.daohang.R;
import com.dongfang.daohang.beans.AreaBean;
import com.dongfang.daohang.beans.BaseEntity;
import com.dongfang.daohang.net.HttpActions;
import com.dongfang.daohang.params.ComParams;
import com.dongfang.utils.DFException;
import com.dongfang.utils.JsonAnalytic;
import com.dongfang.utils.ULog;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 添加记录点的对话框
 * 
 * @author dongfang
 *
 */
public class RecordDialog {

	public static Dialog show(final Context context, String str) {
		final Dialog dlg = new Dialog(context, R.style.CustomProgressDialog);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dialog_record, null);
		dlg.setContentView(layout);

		ProgressBar pb = (ProgressBar) layout.findViewById(R.id.dialog_record_progressbar);

		OnRecordListener listener = new OnRecordListener(context, dlg, str, pb);

		layout.findViewById(R.id.dialog_record_cancel).setOnClickListener(listener);
		layout.findViewById(R.id.dialog_record_recorded).setOnClickListener(listener);
		layout.findViewById(R.id.dialog_record_share_mm).setOnClickListener(listener);
		layout.findViewById(R.id.dialog_record_share_sms).setOnClickListener(listener);

		return dlg;
	}

	public static class OnRecordListener implements View.OnClickListener {
		private Dialog dialog;
		private String str;
		private Context context;
		private ProgressBar pb;

		public OnRecordListener(final Context context, Dialog dialog, String str, ProgressBar pb) {
			this.dialog = dialog;
			this.str = str;
			this.context = context;
			this.pb = pb;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.dialog_record_cancel:
				dialog.dismiss();
				break;
			case R.id.dialog_record_recorded: {
				ULog.d(str);
				AreaBean bean = null;
				try {
					bean = JsonAnalytic.getInstance().analyseJsonT(str, AreaBean.class);
				} catch (DFException e) {
					ULog.e(e.getMessage());
				}

				if (null == bean)
					return;

				pb.setVisibility(View.VISIBLE);

				new HttpUtils().send(HttpMethod.GET, HttpActions.addRecord(context, bean.getAreaId(), ComParams.BASE_PLACEID),
						new RequestCallBack<String>() {
							@Override
							public void onFailure(HttpException arg0, String arg1) {
								Toast.makeText(context, "添加记录点失败〒_〒", Toast.LENGTH_SHORT).show();
								dialog.dismiss();
							}

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								ULog.d(arg0.result);
								try {
									BaseEntity base = JsonAnalytic.getInstance().analyseJsonTDF(arg0.result,
											BaseEntity.class);
									ULog.d(base.getMsg());

									Toast.makeText(context, base.getMsg(), Toast.LENGTH_SHORT).show();
								} catch (DFException e) {
									ULog.d(e.getMessage());

									Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
								}

								dialog.dismiss();

							}
						});
			}
				break;
			case R.id.dialog_record_share_mm:
				break;
			case R.id.dialog_record_share_sms: {
				Uri uri = Uri.parse("smsto:");
				Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
				intent.putExtra("sms_body", str);
				context.startActivity(intent);
			}
				break;
			}

		}

	}

}
