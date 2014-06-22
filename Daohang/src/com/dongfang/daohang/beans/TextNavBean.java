package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class TextNavBean implements Parcelable {

	// {
	// "text": "看到C01右转",
	// "x": 521.50033201439,
	// "y": 1614.9936458173,
	// "floor": 28,
	// "len": 708.93411485471
	// }

	private String text;
	private String x;
	private String y;
	private String floor;
	private String len;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("areaId =").append(text).append("\n");
		sb.append("x      =").append(x).append("\n");
		sb.append("y      =").append(y).append("\n");
		sb.append("floor  =").append(floor).append("\n");
		sb.append("len    =").append(len).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeString(x);
		dest.writeString(y);
		dest.writeString(floor);
		dest.writeString(len);
	}

	public static final Parcelable.Creator<TextNavBean> CREATOR = new Creator<TextNavBean>() {

		@Override
		public TextNavBean[] newArray(int size) {
			return new TextNavBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public TextNavBean createFromParcel(Parcel source) {
			TextNavBean bean = new TextNavBean();
			bean.text = source.readString();
			bean.x = source.readString();
			bean.y = source.readString();
			bean.floor = source.readString();
			bean.len = source.readString();
			return bean;
		}
	};

}
