package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

public class LoginBean {

    public static class Request {
        private String act;
        private String op;
        private String username;
        private String password;
        private String client;

        public String getAct() {
            return act;
        }

        public void setAct(String act) {
            this.act = act;
        }

        public String getOp() {
            return op;
        }

        public void setOp(String op) {
            this.op = op;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }
    }

    public static class Response {
        @SerializedName("code")
        private int code;
        @SerializedName("datas")
        private Item datas;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Item getDatas() {
            return datas;
        }

        public void setDatas(Item datas) {
            this.datas = datas;
        }

        public static class Item {
            @SerializedName("username")
            private String username;
            @SerializedName("userid")
            private String userid;
            @SerializedName("key")
            private String key;
            @SerializedName("sell")
            private String[] sell;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String[] getSell() {
                return sell;
            }

            public void setSell(String[] sell) {
                this.sell = sell;
            }
        }
    }
}
