package org.cloud.panzer.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MemberBean implements Serializable {
    @SerializedName("adv_list")
    private ArrayList<AdvListBean> advList = new ArrayList<>();
    @SerializedName("auth_pic_src")
    private String authPicSrc = "";
    @SerializedName("auth_type")
    private String authType = "";
    @SerializedName("avatar")
    private String avatar = "";
    @SerializedName("dis_store_id")
    private String disStoreId = "";
    @SerializedName("favorites_goods")
    private String favoritesGoods = "";
    @SerializedName("favorites_store")
    private String favoritesStore = "";
    @SerializedName("goods_browse")
    private String goodsBrowse = "";
    @SerializedName("invite_code")
    private String inviteCode = "";
    @SerializedName("level")
    private String level = "";
    @SerializedName("level_name")
    private String levelName = "";
    @SerializedName("member_commision")
    private MemberCommisionBean memberCommision = null;
    @SerializedName("member_grade_id")
    private String memberGradeId = "";
    @SerializedName("member_grade_name")
    private String memberGradeName = "";
    @SerializedName("member_id")
    private String memberId = "";
    @SerializedName("member_mobile")
    private String memberMobile = "";
    @SerializedName("member_to_friend")
    private MemberToFriendBean memberToFriend = null;
    @SerializedName("member_to_peer")
    private MemberToPeerBean memberToPeer = null;
    @SerializedName("micro_url")
    private String microUrl = "";
    private boolean mobielState;
    @SerializedName("order_noeval_count")
    private String orderNoevalCount = "";
    @SerializedName("order_nopay_count")
    private String orderNopayCount = "";
    @SerializedName("order_noreceipt_count")
    private String orderNoreceiptCount = "";
    @SerializedName("order_notakes_count")
    private String orderNotakesCount = "";
    @SerializedName("parent_member_id")
    private String parentMemberId = "";
    @SerializedName("repacket_count")
    private String repacketCount = "";
    @SerializedName("return")
    private String returnX = "";
    @SerializedName("rpacket_count")
    private String rpacketCount = "";
    @SerializedName("true_name")
    private String truename = "";
    private String userMobile;
    @SerializedName("user_name")
    private String userName = "";

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String str) {
        this.userName = str;
    }

    public String getTruename() {
        return this.truename;
    }

    public void setTruename(String str) {
        this.truename = str;
    }

    public String getMemberMobile() {
        return this.memberMobile;
    }

    public void setMemberMobile(String str) {
        this.memberMobile = str;
    }

    public String getDisStoreId() {
        return this.disStoreId;
    }

    public void setDisStoreId(String str) {
        this.disStoreId = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getAuthType() {
        return this.authType;
    }

    public void setAuthType(String str) {
        this.authType = str;
    }

    public String getAuthPicSrc() {
        return this.authPicSrc;
    }

    public void setAuthPicSrc(String str) {
        this.authPicSrc = str;
    }

    public String getMemberGradeId() {
        return this.memberGradeId;
    }

    public void setMemberGradeId(String str) {
        this.memberGradeId = str;
    }

    public String getMemberGradeName() {
        return this.memberGradeName;
    }

    public void setMemberGradeName(String str) {
        this.memberGradeName = str;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public String getInviteCode() {
        return this.inviteCode;
    }

    public void setInviteCode(String str) {
        this.inviteCode = str;
    }

    public String getParentMemberId() {
        return this.parentMemberId;
    }

    public void setParentMemberId(String str) {
        this.parentMemberId = str;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String str) {
        this.level = str;
    }

    public String getLevelName() {
        return this.levelName;
    }

    public void setLevelName(String str) {
        this.levelName = str;
    }

    public String getFavoritesStore() {
        return this.favoritesStore;
    }

    public void setFavoritesStore(String str) {
        this.favoritesStore = str;
    }

    public String getFavoritesGoods() {
        return this.favoritesGoods;
    }

    public void setFavoritesGoods(String str) {
        this.favoritesGoods = str;
    }

    public String getGoodsBrowse() {
        return this.goodsBrowse;
    }

    public void setGoodsBrowse(String str) {
        this.goodsBrowse = str;
    }

    public String getRpacketCount() {
        return this.rpacketCount;
    }

    public void setRpacketCount(String str) {
        this.rpacketCount = str;
    }

    public String getOrderNopayCount() {
        return this.orderNopayCount;
    }

    public void setOrderNopayCount(String str) {
        this.orderNopayCount = str;
    }

    public String getOrderNoreceiptCount() {
        return this.orderNoreceiptCount;
    }

    public void setOrderNoreceiptCount(String str) {
        this.orderNoreceiptCount = str;
    }

    public String getOrderNotakesCount() {
        return this.orderNotakesCount;
    }

    public void setOrderNotakesCount(String str) {
        this.orderNotakesCount = str;
    }

    public String getOrderNoevalCount() {
        return this.orderNoevalCount;
    }

    public void setOrderNoevalCount(String str) {
        this.orderNoevalCount = str;
    }

    public String getRepacketCount() {
        return this.repacketCount;
    }

    public void setRepacketCount(String str) {
        this.repacketCount = str;
    }

    public String getReturnX() {
        return this.returnX;
    }

    public void setReturnX(String str) {
        this.returnX = str;
    }

    public MemberCommisionBean getMemberCommision() {
        return this.memberCommision;
    }

    public void setMemberCommision(MemberCommisionBean memberCommisionBean) {
        this.memberCommision = memberCommisionBean;
    }

    public MemberToFriendBean getMemberToFriend() {
        return this.memberToFriend;
    }

    public void setMemberToFriend(MemberToFriendBean memberToFriendBean) {
        this.memberToFriend = memberToFriendBean;
    }

    public MemberToPeerBean getMemberToPeer() {
        return this.memberToPeer;
    }

    public void setMemberToPeer(MemberToPeerBean memberToPeerBean) {
        this.memberToPeer = memberToPeerBean;
    }

    public String getMicroUrl() {
        return this.microUrl;
    }

    public void setMicroUrl(String str) {
        this.microUrl = str;
    }

    public ArrayList<AdvListBean> getAdvList() {
        return this.advList;
    }

    public void setAdvList(ArrayList<AdvListBean> arrayList) {
        this.advList = arrayList;
    }

    public String getUserMobile() {
        return this.userMobile;
    }

    public void setUserMobile(String str) {
        this.userMobile = str;
    }

    public boolean isMobielState() {
        return this.mobielState;
    }

    public void setMobielState(boolean z) {
        this.mobielState = z;
    }

    public static class MemberCommisionBean {
        @SerializedName("adv_id")
        private String advId = "";
        @SerializedName("adv_image")
        private String advImage = "";
        @SerializedName("adv_name")
        private String advName = "";
        @SerializedName("adv_sort")
        private String advSort = "";
        @SerializedName("adv_type")
        private String advType = "";
        @SerializedName("adv_url")
        private String advUrl = "";

        public String getAdvId() {
            return this.advId;
        }

        public void setAdvId(String str) {
            this.advId = str;
        }

        public String getAdvType() {
            return this.advType;
        }

        public void setAdvType(String str) {
            this.advType = str;
        }

        public String getAdvName() {
            return this.advName;
        }

        public void setAdvName(String str) {
            this.advName = str;
        }

        public String getAdvImage() {
            return this.advImage;
        }

        public void setAdvImage(String str) {
            this.advImage = str;
        }

        public String getAdvUrl() {
            return this.advUrl;
        }

        public void setAdvUrl(String str) {
            this.advUrl = str;
        }

        public String getAdvSort() {
            return this.advSort;
        }

        public void setAdvSort(String str) {
            this.advSort = str;
        }
    }

    public static class MemberToFriendBean {
        @SerializedName("adv_id")
        private String advId = "";
        @SerializedName("adv_image")
        private String advImage = "";
        @SerializedName("adv_name")
        private String advName = "";
        @SerializedName("adv_sort")
        private String advSort = "";
        @SerializedName("adv_type")
        private String advType = "";
        @SerializedName("adv_url")
        private String advUrl = "";

        public String getAdvId() {
            return this.advId;
        }

        public void setAdvId(String str) {
            this.advId = str;
        }

        public String getAdvType() {
            return this.advType;
        }

        public void setAdvType(String str) {
            this.advType = str;
        }

        public String getAdvName() {
            return this.advName;
        }

        public void setAdvName(String str) {
            this.advName = str;
        }

        public String getAdvImage() {
            return this.advImage;
        }

        public void setAdvImage(String str) {
            this.advImage = str;
        }

        public String getAdvUrl() {
            return this.advUrl;
        }

        public void setAdvUrl(String str) {
            this.advUrl = str;
        }

        public String getAdvSort() {
            return this.advSort;
        }

        public void setAdvSort(String str) {
            this.advSort = str;
        }
    }

    public static class MemberToPeerBean {
        @SerializedName("adv_id")
        private String advId = "";
        @SerializedName("adv_image")
        private String advImage = "";
        @SerializedName("adv_name")
        private String advName = "";
        @SerializedName("adv_sort")
        private String advSort = "";
        @SerializedName("adv_type")
        private String advType = "";
        @SerializedName("adv_url")
        private String advUrl = "";

        public String getAdvId() {
            return this.advId;
        }

        public void setAdvId(String str) {
            this.advId = str;
        }

        public String getAdvType() {
            return this.advType;
        }

        public void setAdvType(String str) {
            this.advType = str;
        }

        public String getAdvName() {
            return this.advName;
        }

        public void setAdvName(String str) {
            this.advName = str;
        }

        public String getAdvImage() {
            return this.advImage;
        }

        public void setAdvImage(String str) {
            this.advImage = str;
        }

        public String getAdvUrl() {
            return this.advUrl;
        }

        public void setAdvUrl(String str) {
            this.advUrl = str;
        }

        public String getAdvSort() {
            return this.advSort;
        }

        public void setAdvSort(String str) {
            this.advSort = str;
        }
    }

    public static class AdvListBean {
        @SerializedName("adv_content")
        private String advContent = "";
        @SerializedName("adv_end_date")
        private String advEndDate = "";
        @SerializedName("adv_id")
        private String advId = "";
        @SerializedName("adv_pic")
        private String advPic = "";
        @SerializedName("adv_pic_url")
        private String advPicUrl = "";
        @SerializedName("adv_start_date")
        private String advStartDate = "";
        @SerializedName("adv_title")
        private String advTitle = "";
        @SerializedName("ap_id")
        private String apId = "";
        @SerializedName("buy_style")
        private String buyStyle = "";
        @SerializedName("click_num")
        private String clickNum = "";
        @SerializedName("goldpay")
        private String goldpay = "";
        @SerializedName("is_allow")
        private String isAllow = "";
        @SerializedName("member_id")
        private String memberId = "";
        @SerializedName("member_name")
        private String memberName = "";
        @SerializedName("slide_sort")
        private String slideSort = "";

        public String getAdvId() {
            return this.advId;
        }

        public void setAdvId(String str) {
            this.advId = str;
        }

        public String getApId() {
            return this.apId;
        }

        public void setApId(String str) {
            this.apId = str;
        }

        public String getAdvTitle() {
            return this.advTitle;
        }

        public void setAdvTitle(String str) {
            this.advTitle = str;
        }

        public String getAdvContent() {
            return this.advContent;
        }

        public void setAdvContent(String str) {
            this.advContent = str;
        }

        public String getAdvStartDate() {
            return this.advStartDate;
        }

        public void setAdvStartDate(String str) {
            this.advStartDate = str;
        }

        public String getAdvEndDate() {
            return this.advEndDate;
        }

        public void setAdvEndDate(String str) {
            this.advEndDate = str;
        }

        public String getSlideSort() {
            return this.slideSort;
        }

        public void setSlideSort(String str) {
            this.slideSort = str;
        }

        public String getMemberId() {
            return this.memberId;
        }

        public void setMemberId(String str) {
            this.memberId = str;
        }

        public String getMemberName() {
            return this.memberName;
        }

        public void setMemberName(String str) {
            this.memberName = str;
        }

        public String getClickNum() {
            return this.clickNum;
        }

        public void setClickNum(String str) {
            this.clickNum = str;
        }

        public String getIsAllow() {
            return this.isAllow;
        }

        public void setIsAllow(String str) {
            this.isAllow = str;
        }

        public String getBuyStyle() {
            return this.buyStyle;
        }

        public void setBuyStyle(String str) {
            this.buyStyle = str;
        }

        public String getGoldpay() {
            return this.goldpay;
        }

        public void setGoldpay(String str) {
            this.goldpay = str;
        }

        public String getAdvPicUrl() {
            return this.advPicUrl;
        }

        public void setAdvPicUrl(String str) {
            this.advPicUrl = str;
        }

        public String getAdvPic() {
            return this.advPic;
        }

        public void setAdvPic(String str) {
            this.advPic = str;
        }
    }
}
