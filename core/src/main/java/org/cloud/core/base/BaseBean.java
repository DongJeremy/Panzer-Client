package org.cloud.core.base;

import java.io.Serializable;

public class BaseBean implements Serializable {
    private int code;
    private String datas;
    private int pageTotal;
    private boolean hasmore;

    public BaseBean() {
    }

    public BaseBean(int code, String datas) {
        this.code = code;
        this.datas = datas;
    }

    public BaseBean(int code, String datas, int pageTotal, boolean hasmore) {
        this.code = code;
        this.datas = datas;
        this.pageTotal = pageTotal;
        this.hasmore = hasmore;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
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
}
