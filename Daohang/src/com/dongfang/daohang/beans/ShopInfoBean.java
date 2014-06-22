package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopInfoBean implements Parcelable {
	//
	// {
	// "code": 0,
	// "msg": "OK",
	// "info": {
	// "id": "1",
	// "areaCode": "A03",
	// "name": "优衣库1",
	// "logo": "http://211.149.200.227:30003/upload/2014/06/03/1050f78c0fc6264796666f0f1969636f.jpg",
	// "linkMan": "付尼玛",
	// "phone": "12345678901",
	// "tel": "021-88888888",
	// "address": "上海市长宁区xx商场",
	// "description": "优衣库优衣库优衣库优衣库优衣库优衣库优衣库优衣库优衣库优衣库",
	// "placeId": "10",
	// "placeName": "龙之梦"
	// }
	// }
	//

	private String id;
	private String areaCode;
	private String name;
	private String logo;
	private String linkMan;
	private String phone;
	private String tel;
	private String address;
	private String description;
	private String placeId;
	private String placeName;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id = ").append(id).append("\n");
		sb.append("areaCode = ").append(areaCode).append("\n");
		sb.append("name = ").append(name).append("\n");
		sb.append("logo = ").append(logo).append("\n");
		sb.append("linkMan = ").append(linkMan).append("\n");
		sb.append("phone = ").append(phone).append("\n");
		sb.append("tel = ").append(tel).append("\n");
		sb.append("address = ").append(address).append("\n");
		sb.append("description = ").append(description).append("\n");
		sb.append("placeId = ").append(placeId).append("\n");
		sb.append("placeName = ").append(placeName).append("\n");
		return sb.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(areaCode);
		dest.writeString(name);
		dest.writeString(logo);
		dest.writeString(linkMan);
		dest.writeString(phone);
		dest.writeString(tel);
		dest.writeString(address);
		dest.writeString(description);
		dest.writeString(placeId);
		dest.writeString(placeName);
	}

	public static final Parcelable.Creator<ShopInfoBean> CREATOR = new Creator<ShopInfoBean>() {

		@Override
		public ShopInfoBean[] newArray(int size) {
			return new ShopInfoBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public ShopInfoBean createFromParcel(Parcel source) {
			ShopInfoBean bean = new ShopInfoBean();
			bean.id = source.readString();
			bean.areaCode = source.readString();
			bean.name = source.readString();
			bean.logo = source.readString();
			bean.linkMan = source.readString();
			bean.phone = source.readString();
			bean.tel = source.readString();
			bean.address = source.readString();
			bean.description = source.readString();
			bean.placeId = source.readString();
			bean.placeName = source.readString();
			return bean;
		}
	};

}
