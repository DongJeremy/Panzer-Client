package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeBean implements Serializable {

    private AdvListBean advListBean = null;

    private Goods1Bean goods1Bean = null;

    private Goods2Bean goods2Bean = null;

    private GoodsBean goodsBean = null;

    private Home1Bean home1Bean = null;

    private Home2Bean home2Bean = null;

    private Home3Bean home3Bean = null;

    private Home4Bean home4Bean = null;

    private Home5Bean home5Bean = null;

    private Home7Bean home7Bean = null;

    private String type = "advList";

    public AdvListBean getAdvListBean() {
        return advListBean;
    }

    public void setAdvListBean(AdvListBean advListBean) {
        this.advListBean = advListBean;
    }

    public Goods1Bean getGoods1Bean() {
        return goods1Bean;
    }

    public void setGoods1Bean(Goods1Bean goods1Bean) {
        this.goods1Bean = goods1Bean;
    }

    public Goods2Bean getGoods2Bean() {
        return goods2Bean;
    }

    public void setGoods2Bean(Goods2Bean goods2Bean) {
        this.goods2Bean = goods2Bean;
    }

    public GoodsBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
    }

    public Home1Bean getHome1Bean() {
        return home1Bean;
    }

    public void setHome1Bean(Home1Bean home1Bean) {
        this.home1Bean = home1Bean;
    }

    public Home2Bean getHome2Bean() {
        return home2Bean;
    }

    public void setHome2Bean(Home2Bean home2Bean) {
        this.home2Bean = home2Bean;
    }

    public Home3Bean getHome3Bean() {
        return home3Bean;
    }

    public void setHome3Bean(Home3Bean home3Bean) {
        this.home3Bean = home3Bean;
    }

    public Home4Bean getHome4Bean() {
        return home4Bean;
    }

    public void setHome4Bean(Home4Bean home4Bean) {
        this.home4Bean = home4Bean;
    }

    public Home5Bean getHome5Bean() {
        return home5Bean;
    }

    public void setHome5Bean(Home5Bean home5Bean) {
        this.home5Bean = home5Bean;
    }

    public Home7Bean getHome7Bean() {
        return home7Bean;
    }

    public void setHome7Bean(Home7Bean home7Bean) {
        this.home7Bean = home7Bean;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class AdvListBean {

        private String data = "";

        private String image = "";

        private String type = "";

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Goods1Bean {
        private ArrayList<ItemBean> item = new ArrayList<ItemBean>();

        private String title = "";

        public ArrayList<ItemBean> getItem() {
            return item;
        }

        public void setItem(ArrayList<ItemBean> item) {
            this.item = item;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ItemBean {
            @SerializedName("end_time")
            private String endTime = "";

            @SerializedName("gc_id_1")
            private String gcId1 = "";

            @SerializedName("goods_id")
            private String goodsId = "";

            @SerializedName("goods_image")
            private String goodsImage = "";

            @SerializedName("goods_name")
            private String goodsName = "";

            @SerializedName("goods_price")
            private String goodsPrice = "";

            @SerializedName("lower_limit")
            private String lowerLimit = "";

            @SerializedName("start_time")
            private String startTime = "";

            @SerializedName("state")
            private String state = "";

            @SerializedName("store_id")
            private String storeId = "";

            @SerializedName("time")
            private String time = "";

            @SerializedName("xianshi_explain")
            private String xianshiExplain = "";

            @SerializedName("xianshi_goods_id")
            private String xianshiGoodsId = "";

            @SerializedName("xianshi_id")
            private String xianshiId = "";

            @SerializedName("xianshi_name")
            private String xianshiName = "";

            @SerializedName("xianshi_price")
            private String xianshiPrice = "";

            @SerializedName("xianshi_recommend")
            private String xianshiRecommend = "";

            @SerializedName("xianshi_title")
            private String xianshiTitle = "";

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getGcId1() {
                return gcId1;
            }

            public void setGcId1(String gcId1) {
                this.gcId1 = gcId1;
            }

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
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

            public String getLowerLimit() {
                return lowerLimit;
            }

            public void setLowerLimit(String lowerLimit) {
                this.lowerLimit = lowerLimit;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getXianshiExplain() {
                return xianshiExplain;
            }

            public void setXianshiExplain(String xianshiExplain) {
                this.xianshiExplain = xianshiExplain;
            }

            public String getXianshiGoodsId() {
                return xianshiGoodsId;
            }

            public void setXianshiGoodsId(String xianshiGoodsId) {
                this.xianshiGoodsId = xianshiGoodsId;
            }

            public String getXianshiId() {
                return xianshiId;
            }

            public void setXianshiId(String xianshiId) {
                this.xianshiId = xianshiId;
            }

            public String getXianshiName() {
                return xianshiName;
            }

            public void setXianshiName(String xianshiName) {
                this.xianshiName = xianshiName;
            }

            public String getXianshiPrice() {
                return xianshiPrice;
            }

            public void setXianshiPrice(String xianshiPrice) {
                this.xianshiPrice = xianshiPrice;
            }

            public String getXianshiRecommend() {
                return xianshiRecommend;
            }

            public void setXianshiRecommend(String xianshiRecommend) {
                this.xianshiRecommend = xianshiRecommend;
            }

            public String getXianshiTitle() {
                return xianshiTitle;
            }

            public void setXianshiTitle(String xianshiTitle) {
                this.xianshiTitle = xianshiTitle;
            }
        }
    }

    public static class Goods2Bean {
        @SerializedName("item")
        private ArrayList<ItemBean> item = new ArrayList<ItemBean>();

        @SerializedName("title")
        private String title = "";

        public ArrayList<ItemBean> getItem() {
            return item;
        }

        public void setItem(ArrayList<ItemBean> item) {
            this.item = item;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ItemBean {
            @SerializedName("goods_id")
            private String goodsId = "";

            @SerializedName("goods_image")
            private String goodsImage = "";

            @SerializedName("goods_name")
            private String goodsName = "";

            @SerializedName("goods_promotion_price")
            private String goodsPromotionPrice = "";

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsPromotionPrice() {
                return goodsPromotionPrice;
            }

            public void setGoodsPromotionPrice(String goodsPromotionPrice) {
                this.goodsPromotionPrice = goodsPromotionPrice;
            }
        }
    }

    public static class GoodsBean {
        @SerializedName("item")
        private ArrayList<ItemBean> item = new ArrayList<ItemBean>();

        @SerializedName("title")
        private String title = "";

        public ArrayList<ItemBean> getItem() {
            return item;
        }

        public void setItem(ArrayList<ItemBean> item) {
            this.item = item;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ItemBean {
            @SerializedName("goods_id")
            private String goodsId = "";

            @SerializedName("goods_image")
            private String goodsImage = "";

            @SerializedName("goods_name")
            private String goodsName = "";

            @SerializedName("goods_sale_price")
            private String goodsSalePrice = "";

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsImage() {
                return goodsImage;
            }

            public void setGoodsImage(String goodsImage) {
                this.goodsImage = goodsImage;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsSalePrice() {
                return goodsSalePrice;
            }

            public void setGoodsSalePrice(String goodsSalePrice) {
                this.goodsSalePrice = goodsSalePrice;
            }
        }
    }

    public static class Home1Bean {
        @SerializedName("data")
        private String data = "";

        @SerializedName("image")
        private String image = "";

        @SerializedName("title")
        private String title = "";

        @SerializedName("type")
        private String type = "";

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Home2Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data = "";

        @SerializedName("rectangle1_image")
        private String rectangle1Image = "";

        @SerializedName("rectangle1_type")
        private String rectangle1Type = "";

        @SerializedName("rectangle2_data")
        private String rectangle2Data = "";

        @SerializedName("rectangle2_image")
        private String rectangle2Image = "";

        @SerializedName("rectangle2_type")
        private String rectangle2Type = "";

        @SerializedName("square_data")
        private String squareData = "";

        @SerializedName("square_image")
        private String squareImage = "";

        @SerializedName("square_type")
        private String squareType = "";

        @SerializedName("title")
        private String title = "";

        public String getRectangle1Data() {
            return rectangle1Data;
        }

        public void setRectangle1Data(String rectangle1Data) {
            this.rectangle1Data = rectangle1Data;
        }

        public String getRectangle1Image() {
            return rectangle1Image;
        }

        public void setRectangle1Image(String rectangle1Image) {
            this.rectangle1Image = rectangle1Image;
        }

        public String getRectangle1Type() {
            return rectangle1Type;
        }

        public void setRectangle1Type(String rectangle1Type) {
            this.rectangle1Type = rectangle1Type;
        }

        public String getRectangle2Data() {
            return rectangle2Data;
        }

        public void setRectangle2Data(String rectangle2Data) {
            this.rectangle2Data = rectangle2Data;
        }

        public String getRectangle2Image() {
            return rectangle2Image;
        }

        public void setRectangle2Image(String rectangle2Image) {
            this.rectangle2Image = rectangle2Image;
        }

        public String getRectangle2Type() {
            return rectangle2Type;
        }

        public void setRectangle2Type(String rectangle2Type) {
            this.rectangle2Type = rectangle2Type;
        }

        public String getSquareData() {
            return squareData;
        }

        public void setSquareData(String squareData) {
            this.squareData = squareData;
        }

        public String getSquareImage() {
            return squareImage;
        }

        public void setSquareImage(String squareImage) {
            this.squareImage = squareImage;
        }

        public String getSquareType() {
            return squareType;
        }

        public void setSquareType(String squareType) {
            this.squareType = squareType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Home3Bean {
        @SerializedName("item")
        private ArrayList<ItemBean> item = new ArrayList<ItemBean>();

        @SerializedName("title")
        private String title = "";

        public ArrayList<ItemBean> getItem() {
            return item;
        }

        public void setItem(ArrayList<ItemBean> item) {
            this.item = item;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class ItemBean {
            @SerializedName("data")
            private String data = "";

            @SerializedName("image")
            private String image = "";

            @SerializedName("type")
            private String type = "";

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

    }

    public static class Home4Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data = "";

        @SerializedName("rectangle1_image")
        private String rectangle1Image = "";

        @SerializedName("rectangle1_type")
        private String rectangle1Type = "";

        @SerializedName("rectangle2_data")
        private String rectangle2Data = "";

        @SerializedName("rectangle2_image")
        private String rectangle2Image = "";

        @SerializedName("rectangle2_type")
        private String rectangle2Type = "";

        @SerializedName("square_data")
        private String squareData = "";

        @SerializedName("square_image")
        private String squareImage = "";

        @SerializedName("square_type")
        private String squareType = "";

        @SerializedName("title")
        private String title = "";

        public String getRectangle1Data() {
            return rectangle1Data;
        }

        public void setRectangle1Data(String rectangle1Data) {
            this.rectangle1Data = rectangle1Data;
        }

        public String getRectangle1Image() {
            return rectangle1Image;
        }

        public void setRectangle1Image(String rectangle1Image) {
            this.rectangle1Image = rectangle1Image;
        }

        public String getRectangle1Type() {
            return rectangle1Type;
        }

        public void setRectangle1Type(String rectangle1Type) {
            this.rectangle1Type = rectangle1Type;
        }

        public String getRectangle2Data() {
            return rectangle2Data;
        }

        public void setRectangle2Data(String rectangle2Data) {
            this.rectangle2Data = rectangle2Data;
        }

        public String getRectangle2Image() {
            return rectangle2Image;
        }

        public void setRectangle2Image(String rectangle2Image) {
            this.rectangle2Image = rectangle2Image;
        }

        public String getRectangle2Type() {
            return rectangle2Type;
        }

        public void setRectangle2Type(String rectangle2Type) {
            this.rectangle2Type = rectangle2Type;
        }

        public String getSquareData() {
            return squareData;
        }

        public void setSquareData(String squareData) {
            this.squareData = squareData;
        }

        public String getSquareImage() {
            return squareImage;
        }

        public void setSquareImage(String squareImage) {
            this.squareImage = squareImage;
        }

        public String getSquareType() {
            return squareType;
        }

        public void setSquareType(String squareType) {
            this.squareType = squareType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Home5Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data = "";

        @SerializedName("rectangle1_image")
        private String rectangle1Image = "";

        @SerializedName("rectangle1_type")
        private String rectangle1Type = "";

        @SerializedName("rectangle2_data")
        private String rectangle2Data = "";

        @SerializedName("rectangle2_image")
        private String rectangle2Image = "";

        @SerializedName("rectangle2_type")
        private String rectangle2Type = "";

        @SerializedName("rectangle3_data")
        private String rectangle3Data = "";

        @SerializedName("rectangle3_image")
        private String rectangle3Image = "";

        @SerializedName("rectangle3_type")
        private String rectangle3Type = "";

        @SerializedName("square_data")
        private String squareData = "";

        @SerializedName("square_image")
        private String squareImage = "";

        @SerializedName("square_type")
        private String squareType = "";

        @SerializedName("stitle")
        private String stitle = "";

        @SerializedName("title")
        private String title = "";

        public String getRectangle1Data() {
            return rectangle1Data;
        }

        public void setRectangle1Data(String rectangle1Data) {
            this.rectangle1Data = rectangle1Data;
        }

        public String getRectangle1Image() {
            return rectangle1Image;
        }

        public void setRectangle1Image(String rectangle1Image) {
            this.rectangle1Image = rectangle1Image;
        }

        public String getRectangle1Type() {
            return rectangle1Type;
        }

        public void setRectangle1Type(String rectangle1Type) {
            this.rectangle1Type = rectangle1Type;
        }

        public String getRectangle2Data() {
            return rectangle2Data;
        }

        public void setRectangle2Data(String rectangle2Data) {
            this.rectangle2Data = rectangle2Data;
        }

        public String getRectangle2Image() {
            return rectangle2Image;
        }

        public void setRectangle2Image(String rectangle2Image) {
            this.rectangle2Image = rectangle2Image;
        }

        public String getRectangle2Type() {
            return rectangle2Type;
        }

        public void setRectangle2Type(String rectangle2Type) {
            this.rectangle2Type = rectangle2Type;
        }

        public String getRectangle3Data() {
            return rectangle3Data;
        }

        public void setRectangle3Data(String rectangle3Data) {
            this.rectangle3Data = rectangle3Data;
        }

        public String getRectangle3Image() {
            return rectangle3Image;
        }

        public void setRectangle3Image(String rectangle3Image) {
            this.rectangle3Image = rectangle3Image;
        }

        public String getRectangle3Type() {
            return rectangle3Type;
        }

        public void setRectangle3Type(String rectangle3Type) {
            this.rectangle3Type = rectangle3Type;
        }

        public String getSquareData() {
            return squareData;
        }

        public void setSquareData(String squareData) {
            this.squareData = squareData;
        }

        public String getSquareImage() {
            return squareImage;
        }

        public void setSquareImage(String squareImage) {
            this.squareImage = squareImage;
        }

        public String getSquareType() {
            return squareType;
        }

        public void setSquareType(String squareType) {
            this.squareType = squareType;
        }

        public String getStitle() {
            return stitle;
        }

        public void setStitle(String stitle) {
            this.stitle = stitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class Home7Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data = "";

        @SerializedName("rectangle1_ico_color")
        private String rectangle1IcoColor = "";

        @SerializedName("rectangle1_ico_name")
        private String rectangle1IcoName = "";

        @SerializedName("rectangle1_image")
        private String rectangle1Image = "";

        @SerializedName("rectangle1_type")
        private String rectangle1Type = "";

        @SerializedName("rectangle2_data")
        private String rectangle2Data = "";

        @SerializedName("rectangle2_ico_color")
        private String rectangle2IcoColor = "";

        @SerializedName("rectangle2_ico_name")
        private String rectangle2IcoName = "";

        @SerializedName("rectangle2_image")
        private String rectangle2Image = "";

        @SerializedName("rectangle2_type")
        private String rectangle2Type = "";

        @SerializedName("rectangle3_data")
        private String rectangle3Data = "";

        @SerializedName("rectangle3_ico_color")
        private String rectangle3IcoColor = "";

        @SerializedName("rectangle3_ico_name")
        private String rectangle3IcoName = "";

        @SerializedName("rectangle3_image")
        private String rectangle3Image = "";

        @SerializedName("rectangle3_type")
        private String rectangle3Type = "";

        @SerializedName("rectangle4_data")
        private String rectangle4Data = "";

        @SerializedName("rectangle4_ico_color")
        private String rectangle4IcoColor = "";

        @SerializedName("rectangle4_ico_name")
        private String rectangle4IcoName = "";

        @SerializedName("rectangle4_image")
        private String rectangle4Image = "";

        @SerializedName("rectangle4_type")
        private String rectangle4Type = "";

        @SerializedName("rectangle5_data")
        private String rectangle5Data = "";

        @SerializedName("rectangle5_ico_color")
        private String rectangle5IcoColor = "";

        @SerializedName("rectangle5_ico_name")
        private String rectangle5IcoName = "";

        @SerializedName("rectangle5_image")
        private String rectangle5Image = "";

        @SerializedName("rectangle5_type")
        private String rectangle5Type = "";

        @SerializedName("rectangle6_data")
        private String rectangle6Data = "";

        @SerializedName("rectangle6_ico_color")
        private String rectangle6IcoColor = "";

        @SerializedName("rectangle6_ico_name")
        private String rectangle6IcoName = "";

        @SerializedName("rectangle6_image")
        private String rectangle6Image = "";

        @SerializedName("rectangle6_type")
        private String rectangle6Type = "";

        @SerializedName("rectangle7_data")
        private String rectangle7Data = "";

        @SerializedName("rectangle7_ico_color")
        private String rectangle7IcoColor = "";

        @SerializedName("rectangle7_ico_name")
        private String rectangle7IcoName = "";

        @SerializedName("rectangle7_image")
        private String rectangle7Image = "";

        @SerializedName("rectangle7_type")
        private String rectangle7Type = "";

        @SerializedName("rectangle8_data")
        private String rectangle8Data = "";

        @SerializedName("rectangle8_ico_color")
        private String rectangle8IcoColor = "";

        @SerializedName("rectangle8_ico_name")
        private String rectangle8IcoName = "";

        @SerializedName("rectangle8_image")
        private String rectangle8Image = "";

        @SerializedName("rectangle8_type")
        private String rectangle8Type = "";

        @SerializedName("rectangle9_data")
        private String rectangle9Data = "";

        @SerializedName("rectangle9_ico_color")
        private String rectangle9IcoColor = "";

        @SerializedName("rectangle9_ico_name")
        private String rectangle9IcoName = "";

        @SerializedName("rectangle9_image")
        private String rectangle9Image = "";

        @SerializedName("rectangle9_type")
        private String rectangle9Type = "";

        @SerializedName("square_data")
        private String squareData = "";

        @SerializedName("square_ico_color")
        private String squareIcoColor = "";

        @SerializedName("square_ico_name")
        private String squareIcoName = "";

        @SerializedName("square_image")
        private String squareImage = "";

        @SerializedName("square_type")
        private String squareType = "";

        @SerializedName("title")
        private String title = "";

        public String getRectangle1Data() {
            return rectangle1Data;
        }

        public void setRectangle1Data(String rectangle1Data) {
            this.rectangle1Data = rectangle1Data;
        }

        public String getRectangle1IcoColor() {
            return rectangle1IcoColor;
        }

        public void setRectangle1IcoColor(String rectangle1IcoColor) {
            this.rectangle1IcoColor = rectangle1IcoColor;
        }

        public String getRectangle1IcoName() {
            return rectangle1IcoName;
        }

        public void setRectangle1IcoName(String rectangle1IcoName) {
            this.rectangle1IcoName = rectangle1IcoName;
        }

        public String getRectangle1Image() {
            return rectangle1Image;
        }

        public void setRectangle1Image(String rectangle1Image) {
            this.rectangle1Image = rectangle1Image;
        }

        public String getRectangle1Type() {
            return rectangle1Type;
        }

        public void setRectangle1Type(String rectangle1Type) {
            this.rectangle1Type = rectangle1Type;
        }

        public String getRectangle2Data() {
            return rectangle2Data;
        }

        public void setRectangle2Data(String rectangle2Data) {
            this.rectangle2Data = rectangle2Data;
        }

        public String getRectangle2IcoColor() {
            return rectangle2IcoColor;
        }

        public void setRectangle2IcoColor(String rectangle2IcoColor) {
            this.rectangle2IcoColor = rectangle2IcoColor;
        }

        public String getRectangle2IcoName() {
            return rectangle2IcoName;
        }

        public void setRectangle2IcoName(String rectangle2IcoName) {
            this.rectangle2IcoName = rectangle2IcoName;
        }

        public String getRectangle2Image() {
            return rectangle2Image;
        }

        public void setRectangle2Image(String rectangle2Image) {
            this.rectangle2Image = rectangle2Image;
        }

        public String getRectangle2Type() {
            return rectangle2Type;
        }

        public void setRectangle2Type(String rectangle2Type) {
            this.rectangle2Type = rectangle2Type;
        }

        public String getRectangle3Data() {
            return rectangle3Data;
        }

        public void setRectangle3Data(String rectangle3Data) {
            this.rectangle3Data = rectangle3Data;
        }

        public String getRectangle3IcoColor() {
            return rectangle3IcoColor;
        }

        public void setRectangle3IcoColor(String rectangle3IcoColor) {
            this.rectangle3IcoColor = rectangle3IcoColor;
        }

        public String getRectangle3IcoName() {
            return rectangle3IcoName;
        }

        public void setRectangle3IcoName(String rectangle3IcoName) {
            this.rectangle3IcoName = rectangle3IcoName;
        }

        public String getRectangle3Image() {
            return rectangle3Image;
        }

        public void setRectangle3Image(String rectangle3Image) {
            this.rectangle3Image = rectangle3Image;
        }

        public String getRectangle3Type() {
            return rectangle3Type;
        }

        public void setRectangle3Type(String rectangle3Type) {
            this.rectangle3Type = rectangle3Type;
        }

        public String getRectangle4Data() {
            return rectangle4Data;
        }

        public void setRectangle4Data(String rectangle4Data) {
            this.rectangle4Data = rectangle4Data;
        }

        public String getRectangle4IcoColor() {
            return rectangle4IcoColor;
        }

        public void setRectangle4IcoColor(String rectangle4IcoColor) {
            this.rectangle4IcoColor = rectangle4IcoColor;
        }

        public String getRectangle4IcoName() {
            return rectangle4IcoName;
        }

        public void setRectangle4IcoName(String rectangle4IcoName) {
            this.rectangle4IcoName = rectangle4IcoName;
        }

        public String getRectangle4Image() {
            return rectangle4Image;
        }

        public void setRectangle4Image(String rectangle4Image) {
            this.rectangle4Image = rectangle4Image;
        }

        public String getRectangle4Type() {
            return rectangle4Type;
        }

        public void setRectangle4Type(String rectangle4Type) {
            this.rectangle4Type = rectangle4Type;
        }

        public String getRectangle5Data() {
            return rectangle5Data;
        }

        public void setRectangle5Data(String rectangle5Data) {
            this.rectangle5Data = rectangle5Data;
        }

        public String getRectangle5IcoColor() {
            return rectangle5IcoColor;
        }

        public void setRectangle5IcoColor(String rectangle5IcoColor) {
            this.rectangle5IcoColor = rectangle5IcoColor;
        }

        public String getRectangle5IcoName() {
            return rectangle5IcoName;
        }

        public void setRectangle5IcoName(String rectangle5IcoName) {
            this.rectangle5IcoName = rectangle5IcoName;
        }

        public String getRectangle5Image() {
            return rectangle5Image;
        }

        public void setRectangle5Image(String rectangle5Image) {
            this.rectangle5Image = rectangle5Image;
        }

        public String getRectangle5Type() {
            return rectangle5Type;
        }

        public void setRectangle5Type(String rectangle5Type) {
            this.rectangle5Type = rectangle5Type;
        }

        public String getRectangle6Data() {
            return rectangle6Data;
        }

        public void setRectangle6Data(String rectangle6Data) {
            this.rectangle6Data = rectangle6Data;
        }

        public String getRectangle6IcoColor() {
            return rectangle6IcoColor;
        }

        public void setRectangle6IcoColor(String rectangle6IcoColor) {
            this.rectangle6IcoColor = rectangle6IcoColor;
        }

        public String getRectangle6IcoName() {
            return rectangle6IcoName;
        }

        public void setRectangle6IcoName(String rectangle6IcoName) {
            this.rectangle6IcoName = rectangle6IcoName;
        }

        public String getRectangle6Image() {
            return rectangle6Image;
        }

        public void setRectangle6Image(String rectangle6Image) {
            this.rectangle6Image = rectangle6Image;
        }

        public String getRectangle6Type() {
            return rectangle6Type;
        }

        public void setRectangle6Type(String rectangle6Type) {
            this.rectangle6Type = rectangle6Type;
        }

        public String getRectangle7Data() {
            return rectangle7Data;
        }

        public void setRectangle7Data(String rectangle7Data) {
            this.rectangle7Data = rectangle7Data;
        }

        public String getRectangle7IcoColor() {
            return rectangle7IcoColor;
        }

        public void setRectangle7IcoColor(String rectangle7IcoColor) {
            this.rectangle7IcoColor = rectangle7IcoColor;
        }

        public String getRectangle7IcoName() {
            return rectangle7IcoName;
        }

        public void setRectangle7IcoName(String rectangle7IcoName) {
            this.rectangle7IcoName = rectangle7IcoName;
        }

        public String getRectangle7Image() {
            return rectangle7Image;
        }

        public void setRectangle7Image(String rectangle7Image) {
            this.rectangle7Image = rectangle7Image;
        }

        public String getRectangle7Type() {
            return rectangle7Type;
        }

        public void setRectangle7Type(String rectangle7Type) {
            this.rectangle7Type = rectangle7Type;
        }

        public String getRectangle8Data() {
            return rectangle8Data;
        }

        public void setRectangle8Data(String rectangle8Data) {
            this.rectangle8Data = rectangle8Data;
        }

        public String getRectangle8IcoColor() {
            return rectangle8IcoColor;
        }

        public void setRectangle8IcoColor(String rectangle8IcoColor) {
            this.rectangle8IcoColor = rectangle8IcoColor;
        }

        public String getRectangle8IcoName() {
            return rectangle8IcoName;
        }

        public void setRectangle8IcoName(String rectangle8IcoName) {
            this.rectangle8IcoName = rectangle8IcoName;
        }

        public String getRectangle8Image() {
            return rectangle8Image;
        }

        public void setRectangle8Image(String rectangle8Image) {
            this.rectangle8Image = rectangle8Image;
        }

        public String getRectangle8Type() {
            return rectangle8Type;
        }

        public void setRectangle8Type(String rectangle8Type) {
            this.rectangle8Type = rectangle8Type;
        }

        public String getRectangle9Data() {
            return rectangle9Data;
        }

        public void setRectangle9Data(String rectangle9Data) {
            this.rectangle9Data = rectangle9Data;
        }

        public String getRectangle9IcoColor() {
            return rectangle9IcoColor;
        }

        public void setRectangle9IcoColor(String rectangle9IcoColor) {
            this.rectangle9IcoColor = rectangle9IcoColor;
        }

        public String getRectangle9IcoName() {
            return rectangle9IcoName;
        }

        public void setRectangle9IcoName(String rectangle9IcoName) {
            this.rectangle9IcoName = rectangle9IcoName;
        }

        public String getRectangle9Image() {
            return rectangle9Image;
        }

        public void setRectangle9Image(String rectangle9Image) {
            this.rectangle9Image = rectangle9Image;
        }

        public String getRectangle9Type() {
            return rectangle9Type;
        }

        public void setRectangle9Type(String rectangle9Type) {
            this.rectangle9Type = rectangle9Type;
        }

        public String getSquareData() {
            return squareData;
        }

        public void setSquareData(String squareData) {
            this.squareData = squareData;
        }

        public String getSquareIcoColor() {
            return squareIcoColor;
        }

        public void setSquareIcoColor(String squareIcoColor) {
            this.squareIcoColor = squareIcoColor;
        }

        public String getSquareIcoName() {
            return squareIcoName;
        }

        public void setSquareIcoName(String squareIcoName) {
            this.squareIcoName = squareIcoName;
        }

        public String getSquareImage() {
            return squareImage;
        }

        public void setSquareImage(String squareImage) {
            this.squareImage = squareImage;
        }

        public String getSquareType() {
            return squareType;
        }

        public void setSquareType(String squareType) {
            this.squareType = squareType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
