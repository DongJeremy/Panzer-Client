package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * FileName: StoreInfoBean
 * Author: Admin
 * Date: 2020/11/11 14:14
 * Description: StoreInfoBean
 */
public class StoreInfoBean implements Serializable {
    @SerializedName("goods_count")
    private int goodsCount;
    @SerializedName("is_favorate")
    private boolean isFavorate;
    @SerializedName("is_own_shop")
    private boolean isOwnShop;
    @SerializedName("mb_sliders")
    private List<MbSlidersBean> mbSliders;
    @SerializedName("mb_title_img")
    private String mbTitleImg;
    @SerializedName("member_id")
    private String memberId;
    @SerializedName("node_chat")
    private boolean nodeChat;
    @SerializedName("store_avatar")
    private String storeAvatar;
    @SerializedName("store_collect")
    private int storeCollect;
    @SerializedName("store_credit_text")
    private String storeCreditText;
    @SerializedName("store_grade")
    private String storeGrade;
    @SerializedName("store_id")
    private String storeId;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("store_qq")
    private String storeQq;

    public String getStoreId() {
        return this.storeId;
    }

    public void setStoreId(String str) {
        this.storeId = str;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public String getStoreQq() {
        return this.storeQq;
    }

    public void setStoreQq(String str) {
        this.storeQq = str;
    }

    public boolean isNodeChat() {
        return this.nodeChat;
    }

    public void setNodeChat(boolean z) {
        this.nodeChat = z;
    }

    public String getStoreAvatar() {
        return this.storeAvatar;
    }

    public void setStoreAvatar(String str) {
        this.storeAvatar = str;
    }

    public int getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsCount(int i) {
        this.goodsCount = i;
    }

    public int getStoreCollect() {
        return this.storeCollect;
    }

    public void setStoreCollect(int i) {
        this.storeCollect = i;
    }

    public boolean isIsFavorate() {
        return this.isFavorate;
    }

    public void setIsFavorate(boolean z) {
        this.isFavorate = z;
    }

    public boolean isIsOwnShop() {
        return this.isOwnShop;
    }

    public void setIsOwnShop(boolean z) {
        this.isOwnShop = z;
    }

    public String getStoreCreditText() {
        return this.storeCreditText;
    }

    public void setStoreCreditText(String str) {
        this.storeCreditText = str;
    }

    public String getMbTitleImg() {
        return this.mbTitleImg;
    }

    public void setMbTitleImg(String str) {
        this.mbTitleImg = str;
    }

    public boolean isFavorate() {
        return this.isFavorate;
    }

    public void setFavorate(boolean z) {
        this.isFavorate = z;
    }

    public boolean isOwnShop() {
        return this.isOwnShop;
    }

    public void setOwnShop(boolean z) {
        this.isOwnShop = z;
    }

    public String getStoreGrade() {
        return this.storeGrade;
    }

    public void setStoreGrade(String str) {
        this.storeGrade = str;
    }

    public List<MbSlidersBean> getMbSliders() {
        return this.mbSliders;
    }

    public void setMbSliders(List<MbSlidersBean> list) {
        this.mbSliders = list;
    }

    public class MbSlidersBean {
        @SerializedName("img")
        private String img;
        @SerializedName("imgUrl")
        private String imgUrl;
        @SerializedName("link")
        private String link;
        @SerializedName("type")
        private String type;

        public MbSlidersBean() {
        }

        public String getImg() {
            return this.img;
        }

        public void setImg(String str) {
            this.img = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getLink() {
            return this.link;
        }

        public void setLink(String str) {
            this.link = str;
        }

        public String getImgUrl() {
            return this.imgUrl;
        }

        public void setImgUrl(String str) {
            this.imgUrl = str;
        }
    }
}
