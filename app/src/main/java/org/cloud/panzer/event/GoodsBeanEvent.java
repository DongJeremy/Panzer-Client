package org.cloud.panzer.event;

public class GoodsBeanEvent {

    private String goodsBean;

    public GoodsBeanEvent(String goodsBean) {
        this.goodsBean = goodsBean;
    }

    public String getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(String goodsBean) {
        this.goodsBean = goodsBean;
    }
}
