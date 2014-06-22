package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class AreaBean implements Parcelable {

	// "areaId": "322",
	// "areaName": "9",
	// "areaType": "10"
	// "floor": "B3",
	// "floorId": "36",
	// "placeName": "测试商场",

	// {
	// "areaId": "359",
	// "areaType": "10",
	// "areaname": "A02",
	// "facilityId": null,
	// "facilityName": "",
	// "floorId": "28",
	// "shopId": null
	// "shopName": "",
	// }

	private String areaId;
	private String areaName;
	private String areaType;
	private String areaname;
	private String facilityId;
	private String facilityName;
	private String floor;
	private String floorId;
	private String marketId;
	private String placeName;
	private String shopId;
	private String shopName;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getFloorId() {
		return floorId;
	}

	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return TextUtils.isEmpty(areaname) ? areaName : areaname;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("areaId =").append(areaId).append("\n");
		sb.append("areaName =").append(areaName).append("\n");
		sb.append("areaType =").append(areaType).append("\n");
		sb.append("areaname =").append(areaname).append("\n");
		sb.append("facilityId =").append(facilityId).append("\n");
		sb.append("facilityName =").append(facilityName).append("\n");
		sb.append("floor =").append(floor).append("\n");
		sb.append("floorId =").append(floorId).append("\n");
		sb.append("marketId =").append(marketId).append("\n");
		sb.append("placeName =").append(placeName).append("\n");
		sb.append("shopId =").append(shopId).append("\n");
		sb.append("shopName =").append(shopName).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(areaId);
		dest.writeString(areaName);
		dest.writeString(areaType);
		dest.writeString(areaname);
		dest.writeString(facilityId);
		dest.writeString(facilityName);
		dest.writeString(floor);
		dest.writeString(floorId);
		dest.writeString(marketId);
		dest.writeString(placeName);
		dest.writeString(shopId);
		dest.writeString(shopName);
	}

	public static final Parcelable.Creator<AreaBean> CREATOR = new Creator<AreaBean>() {

		@Override
		public AreaBean[] newArray(int size) {
			return new AreaBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public AreaBean createFromParcel(Parcel source) {
			AreaBean bean = new AreaBean();
			bean.areaId = source.readString();
			bean.areaName = source.readString();
			bean.areaType = source.readString();
			bean.areaname = source.readString();
			bean.facilityId = source.readString();
			bean.facilityName = source.readString();
			bean.floor = source.readString();
			bean.floorId = source.readString();
			bean.marketId = source.readString();
			bean.placeName = source.readString();
			bean.shopId = source.readString();
			bean.shopName = source.readString();
			return bean;
		}
	};

}
