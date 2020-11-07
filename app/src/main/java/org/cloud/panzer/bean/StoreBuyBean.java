package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class StoreBuyBean implements Serializable {
    @SerializedName("freight")
    private String freight;
    @SerializedName("goods_list")
    private ArrayList<GoodsListBean> goodsList;
    private String key = "";
    private String logisticsMoney = "";
    private String message = "";
    @SerializedName("store_goods_total")
    private String storeGoodsTotal;
    @SerializedName("store_mansong_rule_list")
    private StoreMansongRuleListBean storeMansongRuleList;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("store_voucher_info")
    private StoreVoucherInfoBean storeVoucherInfo;
    private String totalMoney = "";

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getTotalMoney() {
        return this.totalMoney;
    }

    public void setTotalMoney(String str) {
        this.totalMoney = str;
    }

    public String getLogisticsMoney() {
        return this.logisticsMoney;
    }

    public void setLogisticsMoney(String str) {
        this.logisticsMoney = str;
    }

    public String getStoreGoodsTotal() {
        return this.storeGoodsTotal;
    }

    public void setStoreGoodsTotal(String str) {
        this.storeGoodsTotal = str;
    }

    public StoreMansongRuleListBean getStoreMansongRuleList() {
        return this.storeMansongRuleList;
    }

    public void setStoreMansongRuleList(StoreMansongRuleListBean storeMansongRuleListBean) {
        this.storeMansongRuleList = storeMansongRuleListBean;
    }

    public String getFreight() {
        return this.freight;
    }

    public void setFreight(String str) {
        this.freight = str;
    }

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String str) {
        this.storeName = str;
    }

    public ArrayList<GoodsListBean> getGoodsList() {
        return this.goodsList;
    }

    public void setGoodsList(ArrayList<GoodsListBean> arrayList) {
        this.goodsList = arrayList;
    }

    public StoreVoucherInfoBean getStoreVoucherInfo() {
        return this.storeVoucherInfo;
    }

    public void setStoreVoucherInfo(StoreVoucherInfoBean storeVoucherInfoBean) {
        this.storeVoucherInfo = storeVoucherInfoBean;
    }

    public class StoreMansongRuleListBean {
        @SerializedName("desc")
        private DescBean desc;
        @SerializedName("discount")
        private String discount;
        @SerializedName("end_time")
        private String endTime;
        @SerializedName("goods_id")
        private int goodsId;
        @SerializedName("goods_image")
        private String goodsImage;
        @SerializedName("goods_image_url")
        private String goodsImageUrl;
        @SerializedName("goods_storage")
        private String goodsStorage;
        @SerializedName("goods_url")
        private String goodsUrl;
        @SerializedName("mansong_goods_name")
        private String mansongGoodsName;
        @SerializedName("mansong_id")
        private String mansongId;
        @SerializedName("mansong_name")
        private String mansongName;
        @SerializedName("price")
        private String price;
        @SerializedName("rule_id")
        private String ruleId;
        @SerializedName("start_time")
        private String startTime;

        public StoreMansongRuleListBean() {
        }

        public String getRuleId() {
            return this.ruleId;
        }

        public void setRuleId(String str) {
            this.ruleId = str;
        }

        public String getMansongId() {
            return this.mansongId;
        }

        public void setMansongId(String str) {
            this.mansongId = str;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public String getDiscount() {
            return this.discount;
        }

        public void setDiscount(String str) {
            this.discount = str;
        }

        public String getMansongGoodsName() {
            return this.mansongGoodsName;
        }

        public void setMansongGoodsName(String str) {
            this.mansongGoodsName = str;
        }

        public int getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(int i) {
            this.goodsId = i;
        }

        public String getGoodsImage() {
            return this.goodsImage;
        }

        public void setGoodsImage(String str) {
            this.goodsImage = str;
        }

        public String getGoodsImageUrl() {
            return this.goodsImageUrl;
        }

        public void setGoodsImageUrl(String str) {
            this.goodsImageUrl = str;
        }

        public String getGoodsStorage() {
            return this.goodsStorage;
        }

        public void setGoodsStorage(String str) {
            this.goodsStorage = str;
        }

        public String getGoodsUrl() {
            return this.goodsUrl;
        }

        public void setGoodsUrl(String str) {
            this.goodsUrl = str;
        }

        public String getMansongName() {
            return this.mansongName;
        }

        public void setMansongName(String str) {
            this.mansongName = str;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public DescBean getDesc() {
            return this.desc;
        }

        public void setDesc(DescBean descBean) {
            this.desc = descBean;
        }

        public class DescBean {
            @SerializedName("desc")
            private String desc;
            @SerializedName("goods_name")
            private String goodsName;
            @SerializedName("url")
            private String url;

            public DescBean() {
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

    public class StoreVoucherInfoBean {
        @SerializedName("desc")
        private String desc;
        @SerializedName("voucher_active_date")
        private String voucherActiveDate;
        @SerializedName("voucher_code")
        private String voucherCode;
        @SerializedName("voucher_desc")
        private String voucherDesc;
        @SerializedName("voucher_end_date")
        private String voucherEndDate;
        @SerializedName("voucher_id")
        private String voucherId;
        @SerializedName("voucher_limit")
        private String voucherLimit;
        @SerializedName("voucher_order_id")
        private String voucherOrderId;
        @SerializedName("voucher_owner_id")
        private String voucherOwnerId;
        @SerializedName("voucher_owner_name")
        private String voucherOwnerName;
        @SerializedName("voucher_price")
        private String voucherPrice;
        @SerializedName("voucher_pwd")
        private String voucherPwd;
        @SerializedName("voucher_pwd2")
        private String voucherPwd2;
        @SerializedName("voucher_start_date")
        private String voucherStartDate;
        @SerializedName("voucher_state")
        private String voucherState;
        @SerializedName("voucher_store_id")
        private String voucherStoreId;
        @SerializedName("voucher_t_id")
        private String voucherTId;
        @SerializedName("voucher_title")
        private String voucherTitle;
        @SerializedName("voucher_type")
        private String voucherType;

        public StoreVoucherInfoBean() {
        }

        public String getVoucherId() {
            return this.voucherId;
        }

        public void setVoucherId(String str) {
            this.voucherId = str;
        }

        public String getVoucherCode() {
            return this.voucherCode;
        }

        public void setVoucherCode(String str) {
            this.voucherCode = str;
        }

        public String getVoucherTId() {
            return this.voucherTId;
        }

        public void setVoucherTId(String str) {
            this.voucherTId = str;
        }

        public String getVoucherTitle() {
            return this.voucherTitle;
        }

        public void setVoucherTitle(String str) {
            this.voucherTitle = str;
        }

        public String getVoucherDesc() {
            return this.voucherDesc;
        }

        public void setVoucherDesc(String str) {
            this.voucherDesc = str;
        }

        public String getVoucherStartDate() {
            return this.voucherStartDate;
        }

        public void setVoucherStartDate(String str) {
            this.voucherStartDate = str;
        }

        public String getVoucherEndDate() {
            return this.voucherEndDate;
        }

        public void setVoucherEndDate(String str) {
            this.voucherEndDate = str;
        }

        public String getVoucherPrice() {
            return this.voucherPrice;
        }

        public void setVoucherPrice(String str) {
            this.voucherPrice = str;
        }

        public String getVoucherLimit() {
            return this.voucherLimit;
        }

        public void setVoucherLimit(String str) {
            this.voucherLimit = str;
        }

        public String getVoucherStoreId() {
            return this.voucherStoreId;
        }

        public void setVoucherStoreId(String str) {
            this.voucherStoreId = str;
        }

        public String getVoucherState() {
            return this.voucherState;
        }

        public void setVoucherState(String str) {
            this.voucherState = str;
        }

        public String getVoucherActiveDate() {
            return this.voucherActiveDate;
        }

        public void setVoucherActiveDate(String str) {
            this.voucherActiveDate = str;
        }

        public String getVoucherType() {
            return this.voucherType;
        }

        public void setVoucherType(String str) {
            this.voucherType = str;
        }

        public String getVoucherOwnerId() {
            return this.voucherOwnerId;
        }

        public void setVoucherOwnerId(String str) {
            this.voucherOwnerId = str;
        }

        public String getVoucherOwnerName() {
            return this.voucherOwnerName;
        }

        public void setVoucherOwnerName(String str) {
            this.voucherOwnerName = str;
        }

        public String getVoucherOrderId() {
            return this.voucherOrderId;
        }

        public void setVoucherOrderId(String str) {
            this.voucherOrderId = str;
        }

        public String getVoucherPwd() {
            return this.voucherPwd;
        }

        public void setVoucherPwd(String str) {
            this.voucherPwd = str;
        }

        public String getVoucherPwd2() {
            return this.voucherPwd2;
        }

        public void setVoucherPwd2(String str) {
            this.voucherPwd2 = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }
    }

    public class GoodsListBean {
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

        public GoodsListBean() {
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

        public String getGoodsTotal() {
            return this.goodsTotal;
        }

        public void setGoodsTotal(String str) {
            this.goodsTotal = str;
        }

        public String getGoodsImageUrl() {
            return this.goodsImageUrl;
        }

        public void setGoodsImageUrl(String str) {
            this.goodsImageUrl = str;
        }
    }
}
