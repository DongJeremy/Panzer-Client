package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsCommendBean implements Serializable {
    @SerializedName("goods_id")
    private String goodsId = "";

    @SerializedName("goods_image_url")
    private String goodsImageUrl = "";

    @SerializedName("goods_name")
    private String goodsName = "";

    @SerializedName("goods_price")
    private String goodsPrice = "";

    @SerializedName("goods_promotion_price")
    private String goodsSalePrice = "";

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSalePrice() {
        return goodsSalePrice;
    }

    public void setGoodsSalePrice(String goodsSalePrice) {
        this.goodsSalePrice = goodsSalePrice;
    }
}
