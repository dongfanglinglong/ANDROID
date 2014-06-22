package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceBean implements Parcelable {
	// {
	// "name": "龙之梦",
	// "linkMan": "Tree",
	// "phone": "12345678901",
	// "carportCount": "256",
	// "carportPrice": "10元/小时",
	// "useCarportCount": "123",
	// "marketId": "1"
	// }
	private String name;
	private String linkMan;
	private String phone;
	private String carportCount;
	private String carportPrice;
	private String useCarportCount;
	private String marketId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarportCount() {
		return carportCount;
	}

	public void setCarportCount(String carportCount) {
		this.carportCount = carportCount;
	}

	public String getCarportPrice() {
		return carportPrice;
	}

	public void setCarportPrice(String carportPrice) {
		this.carportPrice = carportPrice;
	}

	public String getUseCarportCount() {
		return useCarportCount;
	}

	public void setUseCarportCount(String useCarportCount) {
		this.useCarportCount = useCarportCount;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name = ").append(name).append("\n");
		sb.append("linkMan = ").append(linkMan).append("\n");
		sb.append("phone = ").append(phone).append("\n");
		sb.append("carportCount = ").append(carportCount).append("\n");
		sb.append("carportPrice = ").append(carportPrice).append("\n");
		sb.append("useCarportCount = ").append(useCarportCount).append("\n");
		sb.append("marketId = ").append(marketId).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(linkMan);
		dest.writeString(phone);
		dest.writeString(carportCount);
		dest.writeString(carportPrice);
		dest.writeString(useCarportCount);
		dest.writeString(marketId);

	}

	public static final Parcelable.Creator<PlaceBean> CREATOR = new Creator<PlaceBean>() {

		@Override
		public PlaceBean createFromParcel(Parcel source) {
			PlaceBean item = new PlaceBean();
			item.name = source.readString();
			item.linkMan = source.readString();
			item.phone = source.readString();
			item.carportCount = source.readString();
			item.carportPrice = source.readString();
			item.useCarportCount = source.readString();
			item.marketId = source.readString();
			return item;
		}

		@Override
		public PlaceBean[] newArray(int size) {
			return new PlaceBean[size];
		}

	};

}
