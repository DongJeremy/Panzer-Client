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
