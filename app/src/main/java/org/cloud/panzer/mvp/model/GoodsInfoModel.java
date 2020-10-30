package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.HomeContract;

import java.util.List;

import io.reactivex.Observable;

public class GoodsInfoModel {
//        extends BaseModel implements HomeContract.Model {
//    @Override
//    public Observable<HomeInfo> getHomeInfoData() {
//        return RetrofitUtils.getHttpService().getHomeInfoData();
//    }
//
//    public static class HomeInfo {
//
//        private int code;
//        private Data datas;
//
//        public int getCode() {
//            return code;
//        }
//
//        public void setCode(int code) {
//            this.code = code;
//        }
//
//        public Data getDatas() {
//            return datas;
//        }
//
//        public void setDatas(Data datas) {
//            this.datas = datas;
//        }
//
//        public static class Data {
//            private List<HomeBanner> homeBannerList;
//            private List<HomeShortcut> homeShortcutList;
//            private List<Goods> goodsList;
//
//            public List<HomeBanner> getHomeBannerList() {
//                return homeBannerList;
//            }
//
//            public void setHomeBannerList(List<HomeBanner> homeBannerList) {
//                this.homeBannerList = homeBannerList;
//            }
//
//            public List<HomeShortcut> getHomeShortcutList() {
//                return homeShortcutList;
//            }
//
//            public void setHomeProductList(List<HomeShortcut> homeShortcutList) {
//                this.homeShortcutList = homeShortcutList;
//            }
//
//            public List<Goods> getGoodsList() {
//                return goodsList;
//            }
//
//            public void setGoodsList(List<Goods> goodsList) {
//                this.goodsList = goodsList;
//            }
//        }
//
//        public static class HomeBanner {
//            private int id;
//            private String image;
//            private String type;
//            private String data;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getData() {
//                return data;
//            }
//
//            public void setData(String data) {
//                this.data = data;
//            }
//        }
//
//        public static class HomeShortcut {
//            private int id;
//            private String image;
//            private String type;
//            private String data;
//            private String name;
//            private String color;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
//
//            public String getType() {
//                return type;
//            }
//
//            public void setType(String type) {
//                this.type = type;
//            }
//
//            public String getData() {
//                return data;
//            }
//
//            public void setData(String data) {
//                this.data = data;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getColor() {
//                return color;
//            }
//
//            public void setColor(String color) {
//                this.color = color;
//            }
//        }
//
//        public static class Goods {
//            private int id;
//            private String name;
//            private String image;
//            private double price;
//            private String origin;
//            private int count;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getImage() {
//                return image;
//            }
//
//            public void setImage(String image) {
//                this.image = image;
//            }
//
//            public double getPrice() {
//                return price;
//            }
//
//            public void setPrice(double price) {
//                this.price = price;
//            }
//
//            public String getOrigin() {
//                return origin;
//            }
//
//            public void setOrigin(String origin) {
//                this.origin = origin;
//            }
//
//            public int getCount() {
//                return count;
//            }
//
//            public void setCount(int count) {
//                this.count = count;
//            }
//        }
//    }
}
