package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderBean implements Serializable {
    @SerializedName("add_time")
    private String addTime;
    @SerializedName("order_list")
    private ArrayList<OrderListBean> orderList;
    @SerializedName("pay_amount")
    private String payAmount;
    @SerializedName("pay_sn")
    private String paySn;

    public String getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(String str) {
        this.payAmount = str;
    }

    public String getAddTime() {
        return this.addTime;
    }

    public void setAddTime(String str) {
        this.addTime = str;
    }

    public String getPaySn() {
        return this.paySn;
    }

    public void setPaySn(String str) {
        this.paySn = str;
    }

    public ArrayList<OrderListBean> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(ArrayList<OrderListBean> arrayList) {
        this.orderList = arrayList;
    }

    public class OrderListBean {
        @SerializedName("add_time")
        private String addTime;
        @SerializedName("api_pay_time")
        private String apiPayTime;
        @SerializedName("buyer_email")
        private String buyerEmail;
        @SerializedName("buyer_id")
        private String buyerId;
        @SerializedName("buyer_name")
        private String buyerName;
        @SerializedName("buyer_phone")
        private String buyerPhone;
        @SerializedName("chain_code")
        private String chainCode;
        @SerializedName("chain_id")
        private String chainId;
        @SerializedName("delay_time")
        private String delayTime;
        @SerializedName("delete_state")
        private String deleteState;
        @SerializedName("evaluation_again_state")
        private String evaluationAgainState;
        @SerializedName("evaluation_state")
        private String evaluationState;
        @SerializedName("extend_order_goods")
        private ArrayList<ExtendOrderGoodsBean> extendOrderGoods;
        @SerializedName("finnshed_time")
        private String finnshedTime;
        @SerializedName("goods_amount")
        private String goodsAmount;
        @SerializedName("if_cancel")
        private boolean ifCancel;
        @SerializedName("if_delete")
        private boolean ifDelete;
        @SerializedName("if_deliver")
        private boolean ifDeliver;
        @SerializedName("if_evaluation")
        private boolean ifEvaluation;
        @SerializedName("if_evaluation_again")
        private boolean ifEvaluationAgain;
        @SerializedName("if_lock")
        private boolean ifLock;
        @SerializedName("if_receive")
        private boolean ifReceive;
        @SerializedName("if_refund_cancel")
        private boolean ifRefundCancel;
        @SerializedName("lock_state")
        private String lockState;
        @SerializedName("order_amount")
        private String orderAmount;
        @SerializedName("order_from")
        private String orderFrom;
        @SerializedName("order_id")
        private String orderId;
        @SerializedName("order_sn")
        private String orderSn;
        @SerializedName("order_state")
        private String orderState;
        @SerializedName("order_type")
        private String orderType;
        @SerializedName("ownshop")
        private boolean ownshop;
        @SerializedName("pay_sn")
        private String paySn;
        @SerializedName("pay_sn1")
        private String paySn1;
        @SerializedName("payment_code")
        private String paymentCode;
        @SerializedName("payment_name")
        private String paymentName;
        @SerializedName("payment_time")
        private String paymentTime;
        @SerializedName("pd_amount")
        private String pdAmount;
        @SerializedName("points_money")
        private String pointsMoney;
        @SerializedName("points_number")
        private String pointsNumber;
        @SerializedName("rcb_amount")
        private String rcbAmount;
        @SerializedName("refund")
        private String refund;
        @SerializedName("refund_amount")
        private String refundAmount;
        @SerializedName("refund_state")
        private String refundState;
        @SerializedName("rpt_amount")
        private String rptAmount;
        @SerializedName("shipping_code")
        private String shippingCode;
        @SerializedName("shipping_fee")
        private String shippingFee;
        @SerializedName("state_desc")
        private String stateDesc;
        @SerializedName("store_id")
        private String storeId;
        @SerializedName("store_name")
        private String storeName;
        @SerializedName("trade_no")
        private String tradeNo;
        @SerializedName("zengpin_list")
        private ArrayList<ZengpinListBean> zengpinList;

        public OrderListBean() {
        }

        public String getOrderId() {
            return this.orderId;
        }

        public void setOrderId(String str) {
            this.orderId = str;
        }

        public String getOrderSn() {
            return this.orderSn;
        }

        public void setOrderSn(String str) {
            this.orderSn = str;
        }

        public String getPaySn() {
            return this.paySn;
        }

        public void setPaySn(String str) {
            this.paySn = str;
        }

        public String getPaySn1() {
            return this.paySn1;
        }

        public void setPaySn1(String str) {
            this.paySn1 = str;
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

        public String getBuyerId() {
            return this.buyerId;
        }

        public void setBuyerId(String str) {
            this.buyerId = str;
        }

        public String getBuyerName() {
            return this.buyerName;
        }

        public void setBuyerName(String str) {
            this.buyerName = str;
        }

        public String getBuyerEmail() {
            return this.buyerEmail;
        }

        public void setBuyerEmail(String str) {
            this.buyerEmail = str;
        }

        public String getBuyerPhone() {
            return this.buyerPhone;
        }

        public void setBuyerPhone(String str) {
            this.buyerPhone = str;
        }

        public String getAddTime() {
            return this.addTime;
        }

        public void setAddTime(String str) {
            this.addTime = str;
        }

        public String getPaymentCode() {
            return this.paymentCode;
        }

        public void setPaymentCode(String str) {
            this.paymentCode = str;
        }

        public String getPaymentTime() {
            return this.paymentTime;
        }

        public void setPaymentTime(String str) {
            this.paymentTime = str;
        }

        public String getFinnshedTime() {
            return this.finnshedTime;
        }

        public void setFinnshedTime(String str) {
            this.finnshedTime = str;
        }

        public String getGoodsAmount() {
            return this.goodsAmount;
        }

        public void setGoodsAmount(String str) {
            this.goodsAmount = str;
        }

        public String getOrderAmount() {
            return this.orderAmount;
        }

        public void setOrderAmount(String str) {
            this.orderAmount = str;
        }

        public String getRcbAmount() {
            return this.rcbAmount;
        }

        public void setRcbAmount(String str) {
            this.rcbAmount = str;
        }

        public String getPdAmount() {
            return this.pdAmount;
        }

        public void setPdAmount(String str) {
            this.pdAmount = str;
        }

        public String getPointsMoney() {
            return this.pointsMoney;
        }

        public void setPointsMoney(String str) {
            this.pointsMoney = str;
        }

        public String getPointsNumber() {
            return this.pointsNumber;
        }

        public void setPointsNumber(String str) {
            this.pointsNumber = str;
        }

        public String getShippingFee() {
            return this.shippingFee;
        }

        public void setShippingFee(String str) {
            this.shippingFee = str;
        }

        public String getEvaluationState() {
            return this.evaluationState;
        }

        public void setEvaluationState(String str) {
            this.evaluationState = str;
        }

        public String getEvaluationAgainState() {
            return this.evaluationAgainState;
        }

        public void setEvaluationAgainState(String str) {
            this.evaluationAgainState = str;
        }

        public String getOrderState() {
            return this.orderState;
        }

        public void setOrderState(String str) {
            this.orderState = str;
        }

        public String getRefundState() {
            return this.refundState;
        }

        public void setRefundState(String str) {
            this.refundState = str;
        }

        public String getLockState() {
            return this.lockState;
        }

        public void setLockState(String str) {
            this.lockState = str;
        }

        public String getDeleteState() {
            return this.deleteState;
        }

        public void setDeleteState(String str) {
            this.deleteState = str;
        }

        public String getRefundAmount() {
            return this.refundAmount;
        }

        public void setRefundAmount(String str) {
            this.refundAmount = str;
        }

        public String getDelayTime() {
            return this.delayTime;
        }

        public void setDelayTime(String str) {
            this.delayTime = str;
        }

        public String getOrderFrom() {
            return this.orderFrom;
        }

        public void setOrderFrom(String str) {
            this.orderFrom = str;
        }

        public String getShippingCode() {
            return this.shippingCode;
        }

        public void setShippingCode(String str) {
            this.shippingCode = str;
        }

        public String getOrderType() {
            return this.orderType;
        }

        public void setOrderType(String str) {
            this.orderType = str;
        }

        public String getApiPayTime() {
            return this.apiPayTime;
        }

        public void setApiPayTime(String str) {
            this.apiPayTime = str;
        }

        public String getChainId() {
            return this.chainId;
        }

        public void setChainId(String str) {
            this.chainId = str;
        }

        public String getChainCode() {
            return this.chainCode;
        }

        public void setChainCode(String str) {
            this.chainCode = str;
        }

        public String getRptAmount() {
            return this.rptAmount;
        }

        public void setRptAmount(String str) {
            this.rptAmount = str;
        }

        public String getTradeNo() {
            return this.tradeNo;
        }

        public void setTradeNo(String str) {
            this.tradeNo = str;
        }

        public String getStateDesc() {
            return this.stateDesc;
        }

        public void setStateDesc(String str) {
            this.stateDesc = str;
        }

        public String getPaymentName() {
            return this.paymentName;
        }

        public void setPaymentName(String str) {
            this.paymentName = str;
        }

        public String getRefund() {
            return this.refund;
        }

        public void setRefund(String str) {
            this.refund = str;
        }

        public boolean isIfCancel() {
            return this.ifCancel;
        }

        public void setIfCancel(boolean z) {
            this.ifCancel = z;
        }

        public boolean isIfRefundCancel() {
            return this.ifRefundCancel;
        }

        public void setIfRefundCancel(boolean z) {
            this.ifRefundCancel = z;
        }

        public boolean isIfReceive() {
            return this.ifReceive;
        }

        public void setIfReceive(boolean z) {
            this.ifReceive = z;
        }

        public boolean isIfLock() {
            return this.ifLock;
        }

        public void setIfLock(boolean z) {
            this.ifLock = z;
        }

        public boolean isIfDeliver() {
            return this.ifDeliver;
        }

        public void setIfDeliver(boolean z) {
            this.ifDeliver = z;
        }

        public boolean isIfEvaluation() {
            return this.ifEvaluation;
        }

        public void setIfEvaluation(boolean z) {
            this.ifEvaluation = z;
        }

        public boolean isIfEvaluationAgain() {
            return this.ifEvaluationAgain;
        }

        public void setIfEvaluationAgain(boolean z) {
            this.ifEvaluationAgain = z;
        }

        public boolean isIfDelete() {
            return this.ifDelete;
        }

        public void setIfDelete(boolean z) {
            this.ifDelete = z;
        }

        public boolean isOwnshop() {
            return this.ownshop;
        }

        public void setOwnshop(boolean z) {
            this.ownshop = z;
        }

        public ArrayList<ExtendOrderGoodsBean> getExtendOrderGoods() {
            return this.extendOrderGoods;
        }

        public void setExtendOrderGoods(ArrayList<ExtendOrderGoodsBean> arrayList) {
            this.extendOrderGoods = arrayList;
        }

        public ArrayList<ZengpinListBean> getZengpinList() {
            return this.zengpinList;
        }

        public void setZengpinList(ArrayList<ZengpinListBean> arrayList) {
            this.zengpinList = arrayList;
        }

        public class ExtendOrderGoodsBean {
            @SerializedName("extend_refund")
            private ExtendRefundBean extendRefund;
            @SerializedName("goods_id")
            private String goodsId;
            @SerializedName("goods_image_url")
            private String goodsImageUrl;
            @SerializedName("goods_name")
            private String goodsName;
            @SerializedName("goods_num")
            private String goodsNum;
            @SerializedName("goods_price")
            private String goodsPrice;
            @SerializedName("goods_spec")
            private String goodsSpec;
            @SerializedName("goods_type")
            private String goodsType;
            @SerializedName("invite_rates")
            private String inviteRates;
            @SerializedName("refund")
            private boolean refund;

            public ExtendOrderGoodsBean() {
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

            public String getGoodsType() {
                return this.goodsType;
            }

            public void setGoodsType(String str) {
                this.goodsType = str;
            }

            public String getGoodsSpec() {
                return this.goodsSpec;
            }

            public void setGoodsSpec(String str) {
                this.goodsSpec = str;
            }

            public String getInviteRates() {
                return this.inviteRates;
            }

            public void setInviteRates(String str) {
                this.inviteRates = str;
            }

            public boolean isRefund() {
                return this.refund;
            }

            public void setRefund(boolean z) {
                this.refund = z;
            }

            public ExtendRefundBean getExtendRefund() {
                return this.extendRefund;
            }

            public void setExtendRefund(ExtendRefundBean extendRefundBean) {
                this.extendRefund = extendRefundBean;
            }

            public String getGoodsImageUrl() {
                return this.goodsImageUrl;
            }

            public void setGoodsImageUrl(String str) {
                this.goodsImageUrl = str;
            }

            public class ExtendRefundBean {
                @SerializedName("add_time")
                private String addTime;
                @SerializedName("admin_time")
                private String adminTime;
                @SerializedName("buyer_id")
                private String buyerId;
                @SerializedName("buyer_name")
                private String buyerName;
                @SerializedName("goods_id")
                private String goodsId;
                @SerializedName("goods_image")
                private String goodsImage;
                @SerializedName("goods_name")
                private String goodsName;
                @SerializedName("goods_num")
                private String goodsNum;
                @SerializedName("goods_state")
                private String goodsState;
                @SerializedName("order_goods_id")
                private String orderGoodsId;
                @SerializedName("order_goods_type")
                private String orderGoodsType;
                @SerializedName("order_id")
                private String orderId;
                @SerializedName("order_lock")
                private String orderLock;
                @SerializedName("order_sn")
                private String orderSn;
                @SerializedName("pic_info")
                private String picInfo;
                @SerializedName("reason_id")
                private String reasonId;
                @SerializedName("reason_info")
                private String reasonInfo;
                @SerializedName("refund_amount")
                private String refundAmount;
                @SerializedName("refund_id")
                private String refundId;
                @SerializedName("refund_sn")
                private String refundSn;
                @SerializedName("refund_state")
                private String refundState;
                @SerializedName("refund_type")
                private String refundType;
                @SerializedName("return_type")
                private String returnType;
                @SerializedName("seller_state")
                private String sellerState;
                @SerializedName("seller_time")
                private String sellerTime;
                @SerializedName("store_id")
                private String storeId;
                @SerializedName("store_name")
                private String storeName;

                public ExtendRefundBean() {
                }

                public String getRefundId() {
                    return this.refundId;
                }

                public void setRefundId(String str) {
                    this.refundId = str;
                }

                public String getOrderId() {
                    return this.orderId;
                }

                public void setOrderId(String str) {
                    this.orderId = str;
                }

                public String getOrderSn() {
                    return this.orderSn;
                }

                public void setOrderSn(String str) {
                    this.orderSn = str;
                }

                public String getRefundSn() {
                    return this.refundSn;
                }

                public void setRefundSn(String str) {
                    this.refundSn = str;
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

                public String getBuyerId() {
                    return this.buyerId;
                }

                public void setBuyerId(String str) {
                    this.buyerId = str;
                }

                public String getBuyerName() {
                    return this.buyerName;
                }

                public void setBuyerName(String str) {
                    this.buyerName = str;
                }

                public String getGoodsId() {
                    return this.goodsId;
                }

                public void setGoodsId(String str) {
                    this.goodsId = str;
                }

                public String getOrderGoodsId() {
                    return this.orderGoodsId;
                }

                public void setOrderGoodsId(String str) {
                    this.orderGoodsId = str;
                }

                public String getGoodsName() {
                    return this.goodsName;
                }

                public void setGoodsName(String str) {
                    this.goodsName = str;
                }

                public String getGoodsNum() {
                    return this.goodsNum;
                }

                public void setGoodsNum(String str) {
                    this.goodsNum = str;
                }

                public String getRefundAmount() {
                    return this.refundAmount;
                }

                public void setRefundAmount(String str) {
                    this.refundAmount = str;
                }

                public String getGoodsImage() {
                    return this.goodsImage;
                }

                public void setGoodsImage(String str) {
                    this.goodsImage = str;
                }

                public String getOrderGoodsType() {
                    return this.orderGoodsType;
                }

                public void setOrderGoodsType(String str) {
                    this.orderGoodsType = str;
                }

                public String getRefundType() {
                    return this.refundType;
                }

                public void setRefundType(String str) {
                    this.refundType = str;
                }

                public String getSellerState() {
                    return this.sellerState;
                }

                public void setSellerState(String str) {
                    this.sellerState = str;
                }

                public String getRefundState() {
                    return this.refundState;
                }

                public void setRefundState(String str) {
                    this.refundState = str;
                }

                public String getReturnType() {
                    return this.returnType;
                }

                public void setReturnType(String str) {
                    this.returnType = str;
                }

                public String getOrderLock() {
                    return this.orderLock;
                }

                public void setOrderLock(String str) {
                    this.orderLock = str;
                }

                public String getGoodsState() {
                    return this.goodsState;
                }

                public void setGoodsState(String str) {
                    this.goodsState = str;
                }

                public String getAddTime() {
                    return this.addTime;
                }

                public void setAddTime(String str) {
                    this.addTime = str;
                }

                public String getSellerTime() {
                    return this.sellerTime;
                }

                public void setSellerTime(String str) {
                    this.sellerTime = str;
                }

                public String getAdminTime() {
                    return this.adminTime;
                }

                public void setAdminTime(String str) {
                    this.adminTime = str;
                }

                public String getReasonId() {
                    return this.reasonId;
                }

                public void setReasonId(String str) {
                    this.reasonId = str;
                }

                public String getReasonInfo() {
                    return this.reasonInfo;
                }

                public void setReasonInfo(String str) {
                    this.reasonInfo = str;
                }

                public String getPicInfo() {
                    return this.picInfo;
                }

                public void setPicInfo(String str) {
                    this.picInfo = str;
                }
            }
        }

        public class ZengpinListBean {
            @SerializedName("goods_id")
            private String goodsId;
            @SerializedName("goods_image_url")
            private String goodsImageUrl;
            @SerializedName("goods_name")
            private String goodsName;
            @SerializedName("goods_num")
            private String goodsNum;
            @SerializedName("goods_price")
            private String goodsPrice;
            @SerializedName("goods_spec")
            private String goodsSpec;
            @SerializedName("goods_type")
            private String goodsType;
            @SerializedName("invite_rates")
            private String inviteRates;
            @SerializedName("refund")
            private boolean refund;

            public ZengpinListBean() {
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

            public String getGoodsType() {
                return this.goodsType;
            }

            public void setGoodsType(String str) {
                this.goodsType = str;
            }

            public String getGoodsSpec() {
                return this.goodsSpec;
            }

            public void setGoodsSpec(String str) {
                this.goodsSpec = str;
            }

            public String getInviteRates() {
                return this.inviteRates;
            }

            public void setInviteRates(String str) {
                this.inviteRates = str;
            }

            public String getGoodsImageUrl() {
                return this.goodsImageUrl;
            }

            public void setGoodsImageUrl(String str) {
                this.goodsImageUrl = str;
            }

            public boolean isRefund() {
                return this.refund;
            }

            public void setRefund(boolean z) {
                this.refund = z;
            }
        }
    }
}
