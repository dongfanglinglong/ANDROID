package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectItem implements Parcelable {

	private String id;
	private String placeName;
	private String placeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id        = ").append(id).append("\n");
		sb.append("placeName = ").append(placeName).append("\n");
		sb.append("placeId   = ").append(placeId).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(placeName);
		dest.writeString(placeId);

	}

	public static final Parcelable.Creator<CollectItem> CREATOR = new Creator<CollectItem>() {

		@Override
		public CollectItem createFromParcel(Parcel source) {
			CollectItem item = new CollectItem();
			item.id = source.readString();
			item.placeName = source.readString();
			item.placeId = source.readString();
			return item;
		}

		@Override
		public CollectItem[] newArray(int size) {
			return new CollectItem[size];
		}

	};

}
