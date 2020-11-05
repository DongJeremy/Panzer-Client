package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CartBean implements Serializable {
    private boolean check = true;
    @SerializedName("goods")
    private ArrayList<GoodsBean> goods;
    @SerializedName("mansong")
    private ArrayList<MansongBean> mansong;
    @SerializedName("store_id")
    private String storeId;
    @SerializedName("store_name")
    private String storeName;

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

    public ArrayList<GoodsBean> getGoods() {
        return this.goods;
    }

    public void setGoods(ArrayList<GoodsBean> arrayList) {
        this.goods = arrayList;
    }

    public ArrayList<MansongBean> getMansong() {
        return this.mansong;
    }

    public void setMansong(ArrayList<MansongBean> arrayList) {
        this.mansong = arrayList;
    }

    public boolean isCheck() {
        return this.check;
    }

    public void setCheck(boolean z) {
        this.check = z;
    }

    public class GoodsBean {
        @SerializedName("bl_id")
        private String blId;
        @SerializedName("book_down_payment")
        private String bookDownPayment;
        @SerializedName("book_down_time")
        private String bookDownTime;
        @SerializedName("book_final_payment")
        private String bookFinalPayment;
        @SerializedName("buyer_id")
        private String buyerId;
        @SerializedName("cart_id")
        private String cartId;
        private boolean check = true;
        @SerializedName("gc_id")
        private String gcId;
        @SerializedName("goods_commonid")
        private String goodsCommonid;
        @SerializedName("goods_freight")
        private String goodsFreight;
        @SerializedName("goods_id")
        private String goodsId;
        @SerializedName("goods_image")
        private String goodsImage;
        @SerializedName("goods_image_url")
        private String goodsImageUrl;
        @SerializedName("goods_name")
        private String goodsName;
        @SerializedName("goods_num")
        private String goodsNum;
        @SerializedName("goods_price")
        private String goodsPrice;
        @SerializedName("goods_storage")
        private String goodsStorage;
        @SerializedName("goods_storage_alarm")
        private String goodsStorageAlarm;
        @SerializedName("goods_total")
        private String goodsTotal;
        @SerializedName("goods_trans_v")
        private String goodsTransV;
        @SerializedName("goods_vat")
        private String goodsVat;
        @SerializedName("have_gift")
        private String haveGift;
        @SerializedName("ifgroupbuy")
        private boolean ifgroupbuy;
        @SerializedName("ifsole")
        private boolean ifsole;
        @SerializedName("ifxianshi")
        private boolean ifxianshi;
        @SerializedName("is_book")
        private String isBook;
        @SerializedName("is_chain")
        private String isChain;
        @SerializedName("is_fcode")
        private String isFcode;
        @SerializedName("state")
        private boolean state;
        @SerializedName("storage_state")
        private boolean storageState;
        @SerializedName("store_id")
        private String storeId;
        @SerializedName("store_name")
        private String storeName;
        @SerializedName("transport_id")
        private String transportId;

        public GoodsBean() {
        }

        public String getCartId() {
            return this.cartId;
        }

        public void setCartId(String str) {
            this.cartId = str;
        }

        public String getBuyerId() {
            return this.buyerId;
        }

        public void setBuyerId(String str) {
            this.buyerId = str;
        }

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

        public String getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(String str) {
            this.goodsId = str;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public void setGoodsName(String str) {
            this.goodsName = str;
        }

        public String getGoodsPrice() {
            return this.goodsPrice;
        }

        public void setGoodsPrice(String str) {
            this.goodsPrice = str;
        }

        public String getGoodsNum() {
            return this.goodsNum;
        }

        public void setGoodsNum(String str) {
            this.goodsNum = str;
        }

        public String getGoodsImage() {
            return this.goodsImage;
        }

        public void setGoodsImage(String str) {
            this.goodsImage = str;
        }

        public String getBlId() {
            return this.blId;
        }

        public void setBlId(String str) {
            this.blId = str;
        }

        public boolean isState() {
            return this.state;
        }

        public void setState(boolean z) {
            this.state = z;
        }

        public boolean isStorageState() {
            return this.storageState;
        }

        public void setStorageState(boolean z) {
            this.storageState = z;
        }

        public String getGoodsCommonid() {
            return this.goodsCommonid;
        }

        public void setGoodsCommonid(String str) {
            this.goodsCommonid = str;
        }

        public String getGcId() {
            return this.gcId;
        }

        public void setGcId(String str) {
            this.gcId = str;
        }

        public String getTransportId() {
            return this.transportId;
        }

        public void setTransportId(String str) {
            this.transportId = str;
        }

        public String getGoodsFreight() {
            return this.goodsFreight;
        }

        public void setGoodsFreight(String str) {
            this.goodsFreight = str;
        }

        public String getGoodsTransV() {
            return this.goodsTransV;
        }

        public void setGoodsTransV(String str) {
            this.goodsTransV = str;
        }

        public String getGoodsVat() {
            return this.goodsVat;
        }

        public void setGoodsVat(String str) {
            this.goodsVat = str;
        }

        public String getGoodsStorage() {
            return this.goodsStorage;
        }

        public void setGoodsStorage(String str) {
            this.goodsStorage = str;
        }

        public String getGoodsStorageAlarm() {
            return this.goodsStorageAlarm;
        }

        public void setGoodsStorageAlarm(String str) {
            this.goodsStorageAlarm = str;
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

        public String getIsBook() {
            return this.isBook;
        }

        public void setIsBook(String str) {
            this.isBook = str;
        }

        public String getBookDownPayment() {
            return this.bookDownPayment;
        }

        public void setBookDownPayment(String str) {
            this.bookDownPayment = str;
        }

        public String getBookFinalPayment() {
            return this.bookFinalPayment;
        }

        public void setBookFinalPayment(String str) {
            this.bookFinalPayment = str;
        }

        public String getBookDownTime() {
            return this.bookDownTime;
        }

        public void setBookDownTime(String str) {
            this.bookDownTime = str;
        }

        public String getIsChain() {
            return this.isChain;
        }

        public void setIsChain(String str) {
            this.isChain = str;
        }

        public boolean isIfsole() {
            return this.ifsole;
        }

        public void setIfsole(boolean ifsole) {
            this.ifsole = ifsole;
        }

        public boolean isIfxianshi() {
            return this.ifxianshi;
        }

        public void setIfxianshi(boolean ifxianshi) {
            this.ifxianshi = ifxianshi;
        }

        public boolean isIfgroupbuy() {
            return this.ifgroupbuy;
        }

        public void setIfgroupbuy(boolean z) {
            this.ifgroupbuy = z;
        }

        public String getGoodsImageUrl() {
            return this.goodsImageUrl;
        }

        public void setGoodsImageUrl(String str) {
            this.goodsImageUrl = str;
        }

        public String getGoodsTotal() {
            return this.goodsTotal;
        }

        public void setGoodsTotal(String str) {
            this.goodsTotal = str;
        }

        public boolean isCheck() {
            return this.check;
        }

        public void setCheck(boolean z) {
            this.check = z;
        }
    }

    public class MansongBean {
        @SerializedName("desc")
        private String desc;
        @SerializedName("goods_name")
        private String goodsName;
        @SerializedName("url")
        private String url;

        public MansongBean() {
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public void setGoodsName(String str) {
            this.goodsName = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }
}
