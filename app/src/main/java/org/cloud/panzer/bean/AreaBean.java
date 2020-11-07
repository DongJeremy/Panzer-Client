package org.cloud.panzer.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaBean implements Serializable {
    @SerializedName("area_id")
    private String areaId;
    @SerializedName("area_name")
    private String areaName;

    public String getAreaId() {
        return this.areaId;
    }

    public void setAreaId(String str) {
        this.areaId = str;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String str) {
        this.areaName = str;
    }
}