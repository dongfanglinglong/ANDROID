package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Point implements Parcelable {

	private String name;
	private String time;
	private String info;
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

}
