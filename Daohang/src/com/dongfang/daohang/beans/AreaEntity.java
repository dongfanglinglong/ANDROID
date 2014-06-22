package com.dongfang.daohang.beans;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class AreaEntity implements Parcelable {

	// {"total":126,"psize":10,"pno":1,"list":
	// [{"areaId":"322","placeName":"测试商场","floor":"B3","floorId":"36","areaName":"9","areaType":"10"},
	// {"areaId":"322","placeName":"测试商场","floor":"B3","floorId":"36","areaName":"9","areaType":"10"}]}}

	private int total;
	private int psize;
	private int pno;
	private List<AreaBean> list;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public List<AreaBean> getList() {
		return list;
	}

	public void setList(List<AreaBean> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("total = ").append(total).append("\n");
		sb.append("psize = ").append(psize).append("\n");
		sb.append("pno   = ").append(pno).append("\n");
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
		dest.writeInt(psize);
		dest.writeInt(pno);
		dest.writeTypedList(list);
	}

	public static final Parcelable.Creator<AreaEntity> CREATOR = new Creator<AreaEntity>() {

		@Override
		public AreaEntity[] newArray(int size) {
			return new AreaEntity[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public AreaEntity createFromParcel(Parcel source) {
			AreaEntity bean = new AreaEntity();
			bean.total = source.readInt();
			bean.psize = source.readInt();
			bean.pno = source.readInt();
			source.readTypedList(bean.list, AreaBean.CREATOR);
			return bean;
		}
	};

}
