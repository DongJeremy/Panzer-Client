package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeBean implements Serializable {

    private AdvListBean advListBean = null;

    private GoodsBean goodsBean = null;

    private Goods1Bean goods1Bean = null;

    private Goods2Bean goods2Bean = null;

    private HomeNavBean homeNavBean = null;

    private Home1Bean home1Bean = null;

    private Home2Bean home2Bean = null;

    private Home3Bean home3Bean = null;

    private Home4Bean home4Bean = null;

    private Home5Bean home5Bean = null;

    private ArrayList<ArticleBean> articleList = new ArrayList<>();

    private String type = "advList";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<ArticleBean> getArticleList() {
        return articleList;
    }

    public void setArticleList(ArrayList<ArticleBean> articleList) {
        this.articleList = articleList;
    }

    public AdvListBean getAdvListBean() {
        return advListBean;
    }

    public void setAdvListBean(AdvListBean advListBean) {
        this.advListBean = advListBean;
    }

    public GoodsBean getGoodsBean() {
        return goodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        this.goodsBean = goodsBean;
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

    public HomeNavBean getHomeNavBean() {
        return homeNavBean;
    }

    public void setHomeNavBean(HomeNavBean homeNavBean) {
        this.homeNavBean = homeNavBean;
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

    public static class AdvListBean {

        @SerializedName("item")
        private ArrayList<ItemBean> item = new ArrayList<>();

        public ArrayList<ItemBean> getItem() {
            return item;
        }

        public void setItem(ArrayList<ItemBean> item) {
            this.item = item;
        }

        public static class ItemBean {
            @SerializedName("data")
            private String data = "";
            @SerializedName("ico_color")
            private String icoColor = "";
            @SerializedName("ico_name")
            private String icoName = "";
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

            public String getIcoColor() {
                return icoColor;
            }

            public void setIcoColor(String icoColor) {
                this.icoColor = icoColor;
            }

            public String getIcoName() {
                return icoName;
            }

            public void setIcoName(String icoName) {
                this.icoName = icoName;
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

    public static class HomeNavBean {
        @SerializedName("item")
        private ArrayList<ItemBean> item = new ArrayList<>();
        @SerializedName("title")
        private String title = "";

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public ArrayList<ItemBean> getItem() {
            return this.item;
        }

        public void setItem(ArrayList<ItemBean> arrayList) {
            this.item = arrayList;
        }

        public static class ItemBean {
            @SerializedName("data")
            private String data = "";
            @SerializedName("image")
            private String image = "";
            @SerializedName("type")
            private String type = "";
            @SerializedName("word")
            private String word = "";

            public String getImage() {
                return this.image;
            }

            public void setImage(String str) {
                this.image = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public String getData() {
                return this.data;
            }

            public void setData(String str) {
                this.data = str;
            }

            public String getWord() {
                return this.word;
            }

            public void setWord(String str) {
                this.word = str;
            }
        }
    }

    public static class Home1Bean {
        @SerializedName("data")
        private String data;
        @SerializedName("image")
        private String image;
        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private String type;

        public Home1Bean() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getData() {
            return this.data;
        }

        public void setData(String str) {
            this.data = str;
        }
    }

    public class Home2Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data;
        @SerializedName("rectangle1_image")
        private String rectangle1Image;
        @SerializedName("rectangle1_type")
        private String rectangle1Type;
        @SerializedName("rectangle2_data")
        private String rectangle2Data;
        @SerializedName("rectangle2_image")
        private String rectangle2Image;
        @SerializedName("rectangle2_type")
        private String rectangle2Type;
        @SerializedName("square_data")
        private String squareData;
        @SerializedName("square_image")
        private String squareImage;
        @SerializedName("square_type")
        private String squareType;
        @SerializedName("title")
        private String title;

        public Home2Bean() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getSquareImage() {
            return this.squareImage;
        }

        public void setSquareImage(String str) {
            this.squareImage = str;
        }

        public String getSquareType() {
            return this.squareType;
        }

        public void setSquareType(String str) {
            this.squareType = str;
        }

        public String getSquareData() {
            return this.squareData;
        }

        public void setSquareData(String str) {
            this.squareData = str;
        }

        public String getRectangle1Image() {
            return this.rectangle1Image;
        }

        public void setRectangle1Image(String str) {
            this.rectangle1Image = str;
        }

        public String getRectangle1Type() {
            return this.rectangle1Type;
        }

        public void setRectangle1Type(String str) {
            this.rectangle1Type = str;
        }

        public String getRectangle1Data() {
            return this.rectangle1Data;
        }

        public void setRectangle1Data(String str) {
            this.rectangle1Data = str;
        }

        public String getRectangle2Image() {
            return this.rectangle2Image;
        }

        public void setRectangle2Image(String str) {
            this.rectangle2Image = str;
        }

        public String getRectangle2Type() {
            return this.rectangle2Type;
        }

        public void setRectangle2Type(String str) {
            this.rectangle2Type = str;
        }

        public String getRectangle2Data() {
            return this.rectangle2Data;
        }

        public void setRectangle2Data(String str) {
            this.rectangle2Data = str;
        }
    }

    public class Home3Bean {
        @SerializedName("item")
        private ArrayList<ItemBean> item;
        @SerializedName("title")
        private String title;

        public Home3Bean() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public ArrayList<ItemBean> getItem() {
            return this.item;
        }

        public void setItem(ArrayList<ItemBean> arrayList) {
            this.item = arrayList;
        }

        public class ItemBean {
            @SerializedName("data")
            private String data;
            @SerializedName("image")
            private String image;
            @SerializedName("type")
            private String type;

            public ItemBean() {
            }

            public String getImage() {
                return this.image;
            }

            public void setImage(String str) {
                this.image = str;
            }

            public String getType() {
                return this.type;
            }

            public void setType(String str) {
                this.type = str;
            }

            public String getData() {
                return this.data;
            }

            public void setData(String str) {
                this.data = str;
            }
        }
    }

    public class Home4Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data;
        @SerializedName("rectangle1_image")
        private String rectangle1Image;
        @SerializedName("rectangle1_type")
        private String rectangle1Type;
        @SerializedName("rectangle2_data")
        private String rectangle2Data;
        @SerializedName("rectangle2_image")
        private String rectangle2Image;
        @SerializedName("rectangle2_type")
        private String rectangle2Type;
        @SerializedName("square_data")
        private String squareData;
        @SerializedName("square_image")
        private String squareImage;
        @SerializedName("square_type")
        private String squareType;
        @SerializedName("title")
        private String title;

        public Home4Bean() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getRectangle1Image() {
            return this.rectangle1Image;
        }

        public void setRectangle1Image(String str) {
            this.rectangle1Image = str;
        }

        public String getRectangle1Type() {
            return this.rectangle1Type;
        }

        public void setRectangle1Type(String str) {
            this.rectangle1Type = str;
        }

        public String getRectangle1Data() {
            return this.rectangle1Data;
        }

        public void setRectangle1Data(String str) {
            this.rectangle1Data = str;
        }

        public String getRectangle2Image() {
            return this.rectangle2Image;
        }

        public void setRectangle2Image(String str) {
            this.rectangle2Image = str;
        }

        public String getRectangle2Type() {
            return this.rectangle2Type;
        }

        public void setRectangle2Type(String str) {
            this.rectangle2Type = str;
        }

        public String getRectangle2Data() {
            return this.rectangle2Data;
        }

        public void setRectangle2Data(String str) {
            this.rectangle2Data = str;
        }

        public String getSquareImage() {
            return this.squareImage;
        }

        public void setSquareImage(String str) {
            this.squareImage = str;
        }

        public String getSquareType() {
            return this.squareType;
        }

        public void setSquareType(String str) {
            this.squareType = str;
        }

        public String getSquareData() {
            return this.squareData;
        }

        public void setSquareData(String str) {
            this.squareData = str;
        }
    }

    public class Home5Bean {
        @SerializedName("rectangle1_data")
        private String rectangle1Data;
        @SerializedName("rectangle1_image")
        private String rectangle1Image;
        @SerializedName("rectangle1_type")
        private String rectangle1Type;
        @SerializedName("rectangle2_data")
        private String rectangle2Data;
        @SerializedName("rectangle2_image")
        private String rectangle2Image;
        @SerializedName("rectangle2_type")
        private String rectangle2Type;
        @SerializedName("rectangle3_data")
        private String rectangle3Data;
        @SerializedName("rectangle3_image")
        private String rectangle3Image;
        @SerializedName("rectangle3_type")
        private String rectangle3Type;
        @SerializedName("square_data")
        private String squareData;
        @SerializedName("square_image")
        private String squareImage;
        @SerializedName("square_type")
        private String squareType;
        @SerializedName("stitle")
        private String stitle;
        @SerializedName("title")
        private String title;

        public Home5Bean() {
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getStitle() {
            return this.stitle;
        }

        public void setStitle(String str) {
            this.stitle = str;
        }

        public String getSquareImage() {
            return this.squareImage;
        }

        public void setSquareImage(String str) {
            this.squareImage = str;
        }

        public String getSquareType() {
            return this.squareType;
        }

        public void setSquareType(String str) {
            this.squareType = str;
        }

        public String getSquareData() {
            return this.squareData;
        }

        public void setSquareData(String str) {
            this.squareData = str;
        }

        public String getRectangle1Image() {
            return this.rectangle1Image;
        }

        public void setRectangle1Image(String str) {
            this.rectangle1Image = str;
        }

        public String getRectangle1Type() {
            return this.rectangle1Type;
        }

        public void setRectangle1Type(String str) {
            this.rectangle1Type = str;
        }

        public String getRectangle1Data() {
            return this.rectangle1Data;
        }

        public void setRectangle1Data(String str) {
            this.rectangle1Data = str;
        }

        public String getRectangle2Image() {
            return this.rectangle2Image;
        }

        public void setRectangle2Image(String str) {
            this.rectangle2Image = str;
        }

        public String getRectangle2Type() {
            return this.rectangle2Type;
        }

        public void setRectangle2Type(String str) {
            this.rectangle2Type = str;
        }

        public String getRectangle2Data() {
            return this.rectangle2Data;
        }

        public void setRectangle2Data(String str) {
            this.rectangle2Data = str;
        }

        public String getRectangle3Image() {
            return this.rectangle3Image;
        }

        public void setRectangle3Image(String str) {
            this.rectangle3Image = str;
        }

        public String getRectangle3Type() {
            return this.rectangle3Type;
        }

        public void setRectangle3Type(String str) {
            this.rectangle3Type = str;
        }

        public String getRectangle3Data() {
            return this.rectangle3Data;
        }

        public void setRectangle3Data(String str) {
            this.rectangle3Data = str;
        }
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
        private ArrayList<ItemBean> item = new ArrayList<>();

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
}
