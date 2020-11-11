package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * FileName: GoodsBean
 * Author: Admin
 * Date: 2020/11/11 15:07
 * Description: GoodsBean
 */
public class GoodsBean implements Serializable {
    @SerializedName("evaluation_count")
    private String evaluationCount = "";
    @SerializedName("evaluation_good_star")
    private String evaluationGoodStar = "";
    @SerializedName("goods_authprice")
    private String goodsAuthprice = "";
    @SerializedName("goods_id")
    private String goodsId = "";
    @SerializedName("goods_image")
    private String goodsImage = "";
    @SerializedName("goods_image_url")
    private String goodsImageUrl = "";
    @SerializedName("goods_jingle")
    private String goodsJingle = "";
    @SerializedName("goods_marketprice")
    private String goodsMarketprice = "";
    @SerializedName("goods_name")
    private String goodsName = "";
    @SerializedName("goods_price")
    private String goodsPrice = "";
    @SerializedName("goods_salenum")
    private String goodsSalenum = "";
    @SerializedName("grade_price")
    private String gradePrice = "";
    @SerializedName("group_flag")
    private String groupFlag = "";
    @SerializedName("have_gift")
    private String haveGift = "";
    @SerializedName("hhr_price")
    private String hhrPrice = "";
    @SerializedName("is_fcode")
    private String isFcode = "";
    @SerializedName("is_own_shop")
    private String isOwnShop = "";
    @SerializedName("is_presell")
    private String isPresell = "";
    @SerializedName("is_virtual")
    private String isVirtual = "";
    @SerializedName("sole_flag")
    private String soleFlag = "";
    @SerializedName("store_id")
    private String storeId = "";
    @SerializedName("store_name")
    private String storeName = "";
    @SerializedName("xianshi_flag")
    private String xianshiFlag = "";

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String str) {
        this.storeId = str;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public String getGoodsJingle() {
        return this.goodsJingle;
    }

    public void setGoodsJingle(String str) {
        this.goodsJingle = str;
    }

    public String getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setGoodsPrice(String str) {
        this.goodsPrice = str;
    }

    public String getGoodsMarketprice() {
        return this.goodsMarketprice;
    }

    public void setGoodsMarketprice(String str) {
        this.goodsMarketprice = str;
    }

    public String getGoodsImage() {
        return this.goodsImage;
    }

    public void setGoodsImage(String str) {
        this.goodsImage = str;
    }

    public String getGoodsSalenum() {
        return this.goodsSalenum;
    }

    public void setGoodsSalenum(String str) {
        this.goodsSalenum = str;
    }

    public String getEvaluationGoodStar() {
        return this.evaluationGoodStar;
    }

    public void setEvaluationGoodStar(String str) {
        this.evaluationGoodStar = str;
    }

    public String getEvaluationCount() {
        return this.evaluationCount;
    }

    public void setEvaluationCount(String str) {
        this.evaluationCount = str;
    }

    public String getIsVirtual() {
        return this.isVirtual;
    }

    public void setIsVirtual(String str) {
        this.isVirtual = str;
    }

    public String getIsPresell() {
        return this.isPresell;
    }

    public void setIsPresell(String str) {
        this.isPresell = str;
    }

    public String getIsFcode() {
        return this.isFcode;
    }

    public void setIsFcode(String str) {
        this.isFcode = str;
    }

    public String getHaveGift() {
        return this.haveGift;
    }

    public void setHaveGift(String str) {
        this.haveGift = str;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public String getIsOwnShop() {
        return this.isOwnShop;
    }

    public void setIsOwnShop(String str) {
        this.isOwnShop = str;
    }

    public String getGoodsAuthprice() {
        return this.goodsAuthprice;
    }

    public void setGoodsAuthprice(String str) {
        this.goodsAuthprice = str;
    }

    public String getSoleFlag() {
        return this.soleFlag;
    }

    public void setSoleFlag(String str) {
        this.soleFlag = str;
    }

    public String getGroupFlag() {
        return this.groupFlag;
    }

    public void setGroupFlag(String str) {
        this.groupFlag = str;
    }

    public String getXianshiFlag() {
        return this.xianshiFlag;
    }

    public void setXianshiFlag(String str) {
        this.xianshiFlag = str;
    }

    public String getGoodsImageUrl() {
        return this.goodsImageUrl;
    }

    public void setGoodsImageUrl(String str) {
        this.goodsImageUrl = str;
    }

    public String getGradePrice() {
        return this.gradePrice;
    }

    public void setGradePrice(String str) {
        this.gradePrice = str;
    }

    public String getHhrPrice() {
        return this.hhrPrice;
    }

    public void setHhrPrice(String str) {
        this.hhrPrice = str;
    }
}
