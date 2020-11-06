package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArticleBean implements Serializable {
    @SerializedName("ac_id")
    private String acId;
    @SerializedName("article_content")
    private String articleContent;
    @SerializedName("article_id")
    private String articleId;
    @SerializedName("article_position")
    private String articlePosition;
    @SerializedName("article_show")
    private String articleShow;
    @SerializedName("article_sort")
    private String articleSort;
    @SerializedName("article_time")
    private String articleTime;
    @SerializedName("article_title")
    private String articleTitle;
    @SerializedName("article_url")
    private String articleUrl;
    @SerializedName("click_num")
    private String clickNum;

    public String getArticleId() {
        return this.articleId;
    }

    public void setArticleId(String str) {
        this.articleId = str;
    }

    public String getAcId() {
        return this.acId;
    }

    public void setAcId(String str) {
        this.acId = str;
    }

    public String getArticleUrl() {
        return this.articleUrl;
    }

    public void setArticleUrl(String str) {
        this.articleUrl = str;
    }

    public String getArticleShow() {
        return this.articleShow;
    }

    public void setArticleShow(String str) {
        this.articleShow = str;
    }

    public String getArticlePosition() {
        return this.articlePosition;
    }

    public void setArticlePosition(String str) {
        this.articlePosition = str;
    }

    public String getArticleSort() {
        return this.articleSort;
    }

    public void setArticleSort(String str) {
        this.articleSort = str;
    }

    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String str) {
        this.articleTitle = str;
    }

    public String getArticleContent() {
        return this.articleContent;
    }

    public void setArticleContent(String str) {
        this.articleContent = str;
    }

    public String getArticleTime() {
        return this.articleTime;
    }

    public void setArticleTime(String str) {
        this.articleTime = str;
    }

    public String getClickNum() {
        return this.clickNum;
    }

    public void setClickNum(String str) {
        this.clickNum = str;
    }
}
