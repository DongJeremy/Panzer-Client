package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class EvaluateGoodsBean implements Serializable {
    @SerializedName("geval_addtime")
    private String gevalAddtime = "";

    @SerializedName("geval_addtime_again")
    private String gevalAddtimeAgain = "";

    @SerializedName("geval_addtime_again_date")
    private String gevalAddtimeAgainDate = "";

    @SerializedName("geval_addtime_date")
    private String gevalAddtimeDate = "";

    @SerializedName("geval_content")
    private String gevalContent = "";

    @SerializedName("geval_content_again")
    private String gevalContentAgain = "";

    @SerializedName("geval_explain")
    private String gevalExplain = "";

    @SerializedName("geval_explain_again")
    private String gevalExplainAgain = "";

    @SerializedName("geval_frommemberid")
    private String gevalFrommemberid = "";

    @SerializedName("geval_frommembername")
    private String gevalFrommembername = "";

    @SerializedName("geval_image_1024")
    private ArrayList<String> gevalImage1024 = new ArrayList<String>();

    @SerializedName("geval_image_240")
    private ArrayList<String> gevalImage240 = new ArrayList<String>();

    @SerializedName("geval_image_again_1024")
    private ArrayList<String> gevalImageAgain1024 = new ArrayList<String>();

    @SerializedName("geval_image_again_240")
    private ArrayList<String> gevalImageAgain240 = new ArrayList<String>();

    @SerializedName("geval_scores")
    private String gevalScores = "";

    @SerializedName("member_avatar")
    private String memberAvatar = "";

    public String getGevalAddtime() {
        return gevalAddtime;
    }

    public void setGevalAddtime(String gevalAddtime) {
        this.gevalAddtime = gevalAddtime;
    }

    public String getGevalAddtimeAgain() {
        return gevalAddtimeAgain;
    }

    public void setGevalAddtimeAgain(String gevalAddtimeAgain) {
        this.gevalAddtimeAgain = gevalAddtimeAgain;
    }

    public String getGevalAddtimeAgainDate() {
        return gevalAddtimeAgainDate;
    }

    public void setGevalAddtimeAgainDate(String gevalAddtimeAgainDate) {
        this.gevalAddtimeAgainDate = gevalAddtimeAgainDate;
    }

    public String getGevalAddtimeDate() {
        return gevalAddtimeDate;
    }

    public void setGevalAddtimeDate(String gevalAddtimeDate) {
        this.gevalAddtimeDate = gevalAddtimeDate;
    }

    public String getGevalContent() {
        return gevalContent;
    }

    public void setGevalContent(String gevalContent) {
        this.gevalContent = gevalContent;
    }

    public String getGevalContentAgain() {
        return gevalContentAgain;
    }

    public void setGevalContentAgain(String gevalContentAgain) {
        this.gevalContentAgain = gevalContentAgain;
    }

    public String getGevalExplain() {
        return gevalExplain;
    }

    public void setGevalExplain(String gevalExplain) {
        this.gevalExplain = gevalExplain;
    }

    public String getGevalExplainAgain() {
        return gevalExplainAgain;
    }

    public void setGevalExplainAgain(String gevalExplainAgain) {
        this.gevalExplainAgain = gevalExplainAgain;
    }

    public String getGevalFrommemberid() {
        return gevalFrommemberid;
    }

    public void setGevalFrommemberid(String gevalFrommemberid) {
        this.gevalFrommemberid = gevalFrommemberid;
    }

    public String getGevalFrommembername() {
        return gevalFrommembername;
    }

    public void setGevalFrommembername(String gevalFrommembername) {
        this.gevalFrommembername = gevalFrommembername;
    }

    public ArrayList<String> getGevalImage1024() {
        return gevalImage1024;
    }

    public void setGevalImage1024(ArrayList<String> gevalImage1024) {
        this.gevalImage1024 = gevalImage1024;
    }

    public ArrayList<String> getGevalImage240() {
        return gevalImage240;
    }

    public void setGevalImage240(ArrayList<String> gevalImage240) {
        this.gevalImage240 = gevalImage240;
    }

    public ArrayList<String> getGevalImageAgain1024() {
        return gevalImageAgain1024;
    }

    public void setGevalImageAgain1024(ArrayList<String> gevalImageAgain1024) {
        this.gevalImageAgain1024 = gevalImageAgain1024;
    }

    public ArrayList<String> getGevalImageAgain240() {
        return gevalImageAgain240;
    }

    public void setGevalImageAgain240(ArrayList<String> gevalImageAgain240) {
        this.gevalImageAgain240 = gevalImageAgain240;
    }

    public String getGevalScores() {
        return gevalScores;
    }

    public void setGevalScores(String gevalScores) {
        this.gevalScores = gevalScores;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }
}
