package com.dongfang.daohang.beans;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectBean implements Parcelable {
	private int total;
	private int nowPage;
	private int nowSize;
	private List<CollectItem> list;

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

	public List<CollectItem> getList() {
		return list;
	}

	public void setList(List<CollectItem> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("total   = ").append(total).append("\n");
		sb.append("nowPage = ").append(nowPage).append("\n");
		sb.append("nowSize = ").append(nowSize).append("\n");
		if (null != list) {
			for (int i = 0, l = list.size(); i < l; i++) {
				sb.append("list[").append(i).append("] = ").append(list.get(i).toString()).append("\n");
			}
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

	public static final Parcelable.Creator<CollectBean> CREATOR = new Creator<CollectBean>() {

		@Override
		public CollectBean[] newArray(int size) {
			return new CollectBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public CollectBean createFromParcel(Parcel source) {
			CollectBean bean = new CollectBean();
			bean.total = source.readInt();
			bean.nowPage = source.readInt();
			bean.nowSize = source.readInt();
			source.readTypedList(bean.list, CollectItem.CREATOR);
			return bean;
		}
	};

}
