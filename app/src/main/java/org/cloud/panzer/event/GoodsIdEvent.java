package org.cloud.panzer.event;

public class GoodsIdEvent {

    private String goodsId;

    public GoodsIdEvent(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
