package com.dongfang.daohang.beans;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityEntity implements Parcelable {

	// {"total":126,"psize":10,"pno":1,"list":
	// [{"areaId":"322","placeName":"测试商场","floor":"B3","floorId":"36","areaName":"9","areaType":"10"},
	// {"areaId":"322","placeName":"测试商场","floor":"B3","floorId":"36","areaName":"9","areaType":"10"}]}}

	private int total;
	private int nowSize;
	private int nowPage;
	private List<ActivityBean> list;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getNowSize() {
		return nowSize;
	}

	public void setNowSize(int nowSize) {
		this.nowSize = nowSize;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public List<ActivityBean> getList() {
		return list;
	}

	public void setList(List<ActivityBean> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("total = ").append(total).append("\n");
		sb.append("nowSize = ").append(nowSize).append("\n");
		sb.append("nowPage = ").append(nowPage).append("\n");
		if (null != list) {
			for (int i = 0, l = list.size(); i < l; i++)
				sb.append("list[").append(i).append("] = ").append(list.get(i).toString()).append("\n");
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
		dest.writeInt(nowSize);
		dest.writeInt(nowPage);
		dest.writeTypedList(list);
	}

	public static final Parcelable.Creator<ActivityEntity> CREATOR = new Creator<ActivityEntity>() {

		@Override
		public ActivityEntity[] newArray(int size) {
			return new ActivityEntity[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public ActivityEntity createFromParcel(Parcel source) {
			ActivityEntity bean = new ActivityEntity();
			bean.total = source.readInt();
			bean.nowSize = source.readInt();
			bean.nowPage = source.readInt();
			source.readTypedList(bean.list, ActivityBean.CREATOR);
			return bean;
		}
	};

}
