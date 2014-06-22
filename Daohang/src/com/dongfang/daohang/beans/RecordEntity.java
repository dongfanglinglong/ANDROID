package com.dongfang.daohang.beans;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 记录点
 * 
 * @author dongfang
 *
 */
public class RecordEntity implements Parcelable {

	private int total;
	private int nowPage;
	private int nowSize;
	private List<RecordBean> list;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getNowSize() {
		return nowSize;
	}

	public void setNowSize(int nowSize) {
		this.nowSize = nowSize;
	}

	public List<RecordBean> getRecords() {
		return list;
	}

	public void setRecords(List<RecordBean> records) {
		this.list = records;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("total  ").append(" = ").append(total).append("\n");
		sb.append("nowPage ").append(" = ").append(nowPage).append("\n");
		sb.append("nowSize").append(" = ").append(nowSize).append("\n");
		if (null != list) {
			for (int i = 0, l = list.size(); i < l; i++)
				sb.append("records[").append(i).append("] = ").append(list.get(i).toString()).append("\n");
		}
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(total);
		dest.writeInt(nowPage);
		dest.writeInt(nowSize);
		dest.writeTypedList(list);
	}

	public static final Parcelable.Creator<RecordEntity> CREATOR = new Creator<RecordEntity>() {

		@Override
		public RecordEntity[] newArray(int size) {
			return new RecordEntity[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public RecordEntity createFromParcel(Parcel source) {
			RecordEntity bean = new RecordEntity();
			bean.total = source.readInt();
			bean.nowPage = source.readInt();
			bean.nowSize = source.readInt();
			source.readTypedList(bean.list, RecordBean.CREATOR);
			return bean;
		}
	};

}
