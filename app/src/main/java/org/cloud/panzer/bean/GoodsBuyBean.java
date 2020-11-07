package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GoodsBuyBean implements Serializable {
    @SerializedName("address_id")
    private String addressId = "";
    @SerializedName("cart_id")
    private String cartId = "";
    @SerializedName("fcode")
    private String fcode = "";
    @SerializedName("ifcart")
    private String ifCart = "";
    @SerializedName("invoice_id")
    private String invoiceId = "0";
    @SerializedName("isPoint")
    private String isPoint = "0";
    @SerializedName("offpay_hash")
    private String offPayHash = "";
    @SerializedName("offpay_hash_batch")
    private String offPayHashBatch = "";
    @SerializedName("password")
    private String password = "";
    @SerializedName("pay_message")
    private String payMessage = "";
    @SerializedName("pay_name")
    private String payName = "";
    @SerializedName("pd_pay")
    private String pdPay = "0";
    @SerializedName("rcb_pay")
    private String rcbPay = "0";
    @SerializedName("rpt")
    private String rpt = "";
    @SerializedName("vat_hash")
    private String vatHash = "";
    @SerializedName("voucher")
    private String voucher = "";

    public String getIfCart() {
        return this.ifCart;
    }

    public void setIfCart(String str) {
        this.ifCart = str;
    }

    public String getCartId() {
        return this.cartId;
    }

    public void setCartId(String str) {
        this.cartId = str;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String str) {
        this.addressId = str;
    }

    public String getVatHash() {
        return this.vatHash;
    }

    public void setVatHash(String str) {
        this.vatHash = str;
    }

    public String getOffPayHash() {
        return this.offPayHash;
    }

    public void setOffPayHash(String str) {
        this.offPayHash = str;
    }

    public String getOffPayHashBatch() {
        return this.offPayHashBatch;
    }

    public void setOffPayHashBatch(String str) {
        this.offPayHashBatch = str;
    }

    public String getPayName() {
        return this.payName;
    }

    public void setPayName(String str) {
        this.payName = str;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String str) {
        this.invoiceId = str;
    }

    public String getVoucher() {
        return this.voucher;
    }

    public void setVoucher(String str) {
        this.voucher = str;
    }

    public String getPdPay() {
        return this.pdPay;
    }

    public void setPdPay(String str) {
        this.pdPay = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getFcode() {
        return this.fcode;
    }

    public void setFcode(String str) {
        this.fcode = str;
    }

    public String getRcbPay() {
        return this.rcbPay;
    }

    public void setRcbPay(String str) {
        this.rcbPay = str;
    }

    public String getRpt() {
        return this.rpt;
    }

    public void setRpt(String str) {
        this.rpt = str;
    }

    public String getPayMessage() {
        return this.payMessage;
    }

    public void setPayMessage(String str) {
        this.payMessage = str;
    }

    public String getIsPoint() {
        return this.isPoint;
    }

    public void setIsPoint(String str) {
        this.isPoint = str;
    }
}
