package org.cloud.panzer.bean;

public class BaseBean {
    private int code = 0;

    private String datas = "";

    private boolean hasmore = false;

    private int pageTotal = 0;

    public BaseBean() {
    }

    public BaseBean(int code, String datas, boolean hasmore, int pageTotal) {
        this.code = code;
        this.datas = datas;
        this.hasmore = hasmore;
        this.pageTotal = pageTotal;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }
}
