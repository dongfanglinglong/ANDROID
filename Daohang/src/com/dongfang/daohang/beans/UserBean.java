package com.dongfang.daohang.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class UserBean implements Parcelable {
	private String createTime;
	private String id;
	private Number isDelete;
	private String mobile;
	private String name;
	private String nickName;
	private Number sex;
	private Number status;
	private String userToken;

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Number getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Number isDelete) {
		this.isDelete = isDelete;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Number getSex() {
		return this.sex;
	}

	public void setSex(Number sex) {
		this.sex = sex;
	}

	public Number getStatus() {
		return this.status;
	}

	public void setStatus(Number status) {
		this.status = status;
	}

	public String getUserToken() {
		return this.userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("createTime").append(" = ").append(createTime).append("\n");
		sb.append("id        ").append(" = ").append(id).append("\n");
		sb.append("isDelete  ").append(" = ").append(isDelete).append("\n");
		sb.append("mobile    ").append(" = ").append(mobile).append("\n");
		sb.append("name      ").append(" = ").append(name).append("\n");
		sb.append("nickName  ").append(" = ").append(nickName).append("\n");
		sb.append("sex       ").append(" = ").append(sex).append("\n");
		sb.append("status    ").append(" = ").append(status).append("\n");
		sb.append("userToken ").append(" = ").append(userToken).append("\n");
		return sb.toString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(createTime);
		dest.writeString(id);
		dest.writeInt(isDelete.intValue());
		dest.writeString(mobile);
		dest.writeString(name);
		dest.writeString(nickName);
		dest.writeInt(sex.intValue());
		dest.writeInt(status.intValue());
		dest.writeString(userToken);
	}

	public static final Parcelable.Creator<UserBean> CREATOR = new Creator<UserBean>() {

		@Override
		public UserBean[] newArray(int size) {
			return new UserBean[size];
		}

		// 将Parcel对象反序列化为ParcelableDate
		@Override
		public UserBean createFromParcel(Parcel source) {
			UserBean bean = new UserBean();
			bean.createTime = source.readString();
			bean.id = source.readString();
			bean.isDelete = source.readInt();
			bean.mobile = source.readString();
			bean.name = source.readString();
			bean.nickName = source.readString();
			bean.sex = source.readInt();
			bean.status = source.readInt();
			bean.userToken = source.readString();
			return bean;
		}
	};
}
