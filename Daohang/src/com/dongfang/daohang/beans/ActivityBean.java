package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ActivityBean implements Parcelable {
	// {
	// "activityId": "2",
	// "placeId": "10",
	// "shopId": "1",
	// "name": "满减折扣活动",
	// "logo": "http://211.149.200.227:30003/upload/2014/05/30/1050f78c0fc6264796666f0f1969636f.jpg",
	// "type": "1",
	// "description": "购物满100减10元",
	// "startTime": "2014-05-22 00:00:00",
	// "endTime": "2014-12-30 00:00:00",
	// "shopName": "优衣库1",
	// "shopLogo": "http://211.149.200.227:30003/upload/2014/06/03/1050f78c0fc6264796666f0f1969636f.jpg"
	// }
	private String activityId;
	private String placeId;
	private String shopId;
	private String name;
	private String logo;
	private String type;
	private String description;

	private String startTime;
	private String endTime;
	private String shopName;
	private String shopLogo;

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopLogo() {
		return shopLogo;
	}

	public void setShopLogo(String shopLogo) {
		this.shopLogo = shopLogo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("activityId = ").append(activityId).append("\n");
		sb.append("placeId = ").append(placeId).append("\n");
		sb.append("shopId = ").append(shopId).append("\n");
		sb.append("name = ").append(name).append("\n");
		sb.append("logo = ").append(logo).append("\n");
		sb.append("type = ").append(type).append("\n");
		sb.append("description = ").append(description).append("\n");
		sb.append("startTime = ").append(startTime).append("\n");
		sb.append("endTime = ").append(endTime).append("\n");
		sb.append("shopName = ").append(shopName).append("\n");
		sb.append("shopLogo = ").append(shopLogo).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(activityId);
		dest.writeString(placeId);
		dest.writeString(shopId);
		dest.writeString(name);
		dest.writeString(logo);
		dest.writeString(type);
		dest.writeString(description);

		dest.writeString(startTime);
		dest.writeString(endTime);
		dest.writeString(shopName);
		dest.writeString(shopLogo);

	}

	public static final Parcelable.Creator<ActivityBean> CREATOR = new Creator<ActivityBean>() {

		@Override
		public ActivityBean createFromParcel(Parcel source) {
			ActivityBean item = new ActivityBean();
			item.activityId = source.readString();
			item.placeId = source.readString();
			item.shopId = source.readString();
			item.name = source.readString();
			item.logo = source.readString();
			item.type = source.readString();
			item.description = source.readString();

			item.startTime = source.readString();
			item.endTime = source.readString();
			item.shopName = source.readString();
			item.shopLogo = source.readString();
			return item;
		}

		@Override
		public ActivityBean[] newArray(int size) {
			return new ActivityBean[size];
		}

	};

}
