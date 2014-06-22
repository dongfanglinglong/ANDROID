package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 记录点
 * 
 * @author dongfang
 *
 */
public class RecordBean implements Parcelable {

	private String areaId;
	private String placeId;
	private String createTime;
	private String areaname;
	private String mername;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getMername() {
		return mername;
	}

	public void setMername(String mername) {
		this.mername = mername;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("areaId     = ").append(areaId).append("\n");
		sb.append("placeId    = ").append(placeId).append("\n");
		sb.append("createTime = ").append(createTime).append("\n");
		sb.append("areaname   = ").append(areaname).append("\n");
		sb.append("mername    = ").append(mername).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(areaId);
		dest.writeString(placeId);
		dest.writeString(createTime);
		dest.writeString(areaname);
		dest.writeString(mername);
	}

	public static final Parcelable.Creator<RecordBean> CREATOR = new Creator<RecordBean>() {

		@Override
		public RecordBean[] newArray(int size) {
			return new RecordBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public RecordBean createFromParcel(Parcel source) {
			RecordBean bean = new RecordBean();
			bean.areaId = source.readString();
			bean.placeId = source.readString();
			bean.createTime = source.readString();
			bean.areaname = source.readString();
			bean.mername = source.readString();
			return bean;
		}
	};

}
