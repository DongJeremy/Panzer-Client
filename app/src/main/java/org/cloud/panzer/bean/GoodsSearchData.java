package org.cloud.panzer.bean;

import java.io.Serializable;

public class GoodsSearchData implements Serializable {
    private String areaId = "";
    private String brandId = "";
    private String ci = "";
    private String gcId = "";
    private String gift = "";
    private String groupbuy = "";
    private String image = "";
    private String imageSm = "";
    private String key = "";
    private String keyword = "";
    private String order = "";
    private String ownShop = "";
    private String page = "1";
    private String priceFrom = "0";
    private String priceTo = "";
    private String stcId = "";
    private String storeGradeid = "";
    private String storeId = "";
    private String virtual = "";
    private String xianshi = "";

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getGcId() {
        return this.gcId;
    }

    public void setGcId(String str) {
        this.gcId = str;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String str) {
        this.page = str;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String str) {
        this.order = str;
    }

    public String getStcId() {
        return this.stcId;
    }

    public void setStcId(String str) {
        this.stcId = str;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String str) {
        this.storeId = str;
    }

    public String getBrandId() {
        return this.brandId;
    }

    public void setBrandId(String str) {
        this.brandId = str;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public String getPriceTo() {
        return this.priceTo;
    }

    public void setPriceTo(String str) {
        this.priceTo = str;
    }

    public String getPriceFrom() {
        return this.priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getGift() {
        return this.gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getGroupbuy() {
        return this.groupbuy;
    }

    public void setGroupbuy(String groupbuy) {
        this.groupbuy = groupbuy;
    }

    public String getXianshi() {
        return this.xianshi;
    }

    public void setXianshi(String xianshi) {
        this.xianshi = xianshi;
    }

    public String getVirtual() {
        return this.virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }

    public String getOwnShop() {
        return this.ownShop;
    }

    public void setOwnShop(String ownShop) {
        this.ownShop = ownShop;
    }

    public String getStoreGradeid() {
        return this.storeGradeid;
    }

    public void setStoreGradeid(String str) {
        this.storeGradeid = str;
    }

    public String getCi() {
        return this.ci;
    }

    public void setCi(String str) {
        this.ci = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageSm() {
        return this.imageSm;
    }

    public void setImageSm(String imageSm) {
        this.imageSm = imageSm;
    }
}
