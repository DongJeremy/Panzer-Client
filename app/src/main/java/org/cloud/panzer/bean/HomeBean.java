package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeBean implements Serializable {

    public AdvListBean advListBean = null;

    public GoodsBean goodsBean = null;

    public Home7Bean home7Bean = null;

    private String type = "advList";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class ShortcutBean {
        public String image;
        public String type;
        public String data;
        public String iconName;
        public String iconColor;

        public ShortcutBean(String image, String type, String data, String iconName, String iconColor) {
            this.image = image;
            this.type = type;
            this.data = data;
            this.iconName = iconName;
            this.iconColor = iconColor;
        }

        public ShortcutBean() {
        }
    }

    public static class AdvListBean {

        public String data = "";

        public String image = "";

        public String type = "";
    }

    public static class Goods1Bean {
        public ArrayList<ItemBean> item = new ArrayList<ItemBean>();

        public String title = "";

        public static class ItemBean {
            @SerializedName("end_time")
            public String endTime = "";

            @SerializedName("gc_id_1")
            public String gcId1 = "";

            @SerializedName("goods_id")
            public String goodsId = "";

            @SerializedName("goods_image")
            public String goodsImage = "";

            @SerializedName("goods_name")
            public String goodsName = "";

            @SerializedName("goods_price")
            public String goodsPrice = "";

            @SerializedName("lower_limit")
            public String lowerLimit = "";

            @SerializedName("start_time")
            public String startTime = "";

            @SerializedName("state")
            public String state = "";

            @SerializedName("store_id")
            public String storeId = "";

            @SerializedName("time")
            public String time = "";

            @SerializedName("xianshi_explain")
            public String xianshiExplain = "";

            @SerializedName("xianshi_goods_id")
            public String xianshiGoodsId = "";

            @SerializedName("xianshi_id")
            public String xianshiId = "";

            @SerializedName("xianshi_name")
            public String xianshiName = "";

            @SerializedName("xianshi_price")
            public String xianshiPrice = "";

            @SerializedName("xianshi_recommend")
            public String xianshiRecommend = "";

            @SerializedName("xianshi_title")
            public String xianshiTitle = "";
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
        public ArrayList<ItemBean> item = new ArrayList<>();

        @SerializedName("title")
        public String title = "";

        public static class ItemBean {
            @SerializedName("goods_id")
            public String goodsId = "";

            @SerializedName("goods_image")
            public String goodsImage = "";

            @SerializedName("goods_name")
            public String goodsName = "";

            @SerializedName("goods_promotion_price")
            public String goodsSalePrice = "";
        }
    }

    public static class Home7Bean {
        @SerializedName("rectangle1_data")
        public String rectangle1Data = "";

        @SerializedName("rectangle1_ico_color")
        public String rectangle1IcoColor = "";

        @SerializedName("rectangle1_ico_name")
        public String rectangle1IcoName = "";

        @SerializedName("rectangle1_image")
        public String rectangle1Image = "";

        @SerializedName("rectangle1_type")
        public String rectangle1Type = "";

        @SerializedName("rectangle2_data")
        public String rectangle2Data = "";

        @SerializedName("rectangle2_ico_color")
        public String rectangle2IcoColor = "";

        @SerializedName("rectangle2_ico_name")
        public String rectangle2IcoName = "";

        @SerializedName("rectangle2_image")
        public String rectangle2Image = "";

        @SerializedName("rectangle2_type")
        public String rectangle2Type = "";

        @SerializedName("rectangle3_data")
        public String rectangle3Data = "";

        @SerializedName("rectangle3_ico_color")
        public String rectangle3IcoColor = "";

        @SerializedName("rectangle3_ico_name")
        public String rectangle3IcoName = "";

        @SerializedName("rectangle3_image")
        public String rectangle3Image = "";

        @SerializedName("rectangle3_type")
        public String rectangle3Type = "";

        @SerializedName("rectangle4_data")
        public String rectangle4Data = "";

        @SerializedName("rectangle4_ico_color")
        public String rectangle4IcoColor = "";

        @SerializedName("rectangle4_ico_name")
        public String rectangle4IcoName = "";

        @SerializedName("rectangle4_image")
        public String rectangle4Image = "";

        @SerializedName("rectangle4_type")
        public String rectangle4Type = "";

        @SerializedName("rectangle5_data")
        public String rectangle5Data = "";

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
