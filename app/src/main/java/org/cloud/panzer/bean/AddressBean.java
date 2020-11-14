package org.cloud.panzer.bean;

//import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressBean implements Serializable {
    @SerializedName("address")
    private String address;
    @SerializedName("address_id")
    private String addressId;
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_info")
    private String areaInfo;
    @SerializedName("city_id")
    private String cityId;
    @SerializedName("dlyp_id")
    private String dlypId;
    @SerializedName("is_default")
    private String isDefault;
    @SerializedName("member_id")
    private String memberId;
    @SerializedName("mob_phone")
    private String mobPhone;
    @SerializedName("tel_phone")
    private Object telPhone;
    @SerializedName("true_name")
    private String trueName;

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String str) {
        this.addressId = str;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public String getTrueName() {
        return this.trueName;
    }

    public void setTrueName(String str) {
        this.trueName = str;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String str) {
        this.areaId = str;
    }

    public String getCityId() {
        return this.cityId;
    }

    public void setCityId(String str) {
        this.cityId = str;
    }

    public String getAreaInfo() {
        return this.areaInfo;
    }

    public void setAreaInfo(String str) {
        this.areaInfo = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public Object getTelPhone() {
        return this.telPhone;
    }

    public void setTelPhone(Object obj) {
        this.telPhone = obj;
    }

    public String getMobPhone() {
        return this.mobPhone;
    }

    public void setMobPhone(String str) {
        this.mobPhone = str;
    }

    public String getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(String str) {
        this.isDefault = str;
    }

    public String getDlypId() {
        return this.dlypId;
    }

    public void setDlypId(String str) {
        this.dlypId = str;
    }

}
