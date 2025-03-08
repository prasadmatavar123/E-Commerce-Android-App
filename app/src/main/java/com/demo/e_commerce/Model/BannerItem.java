package com.demo.e_commerce.Model;

public class BannerItem {
    private String mBimg;
    private String mId;
    private String cid;

    public BannerItem(String cid, String mBimg, String mId) {
        this.cid = cid;
        this.mBimg = mBimg;
        this.mId = mId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getmBimg() {
        return mBimg;
    }

    public void setmBimg(String mBimg) {
        this.mBimg = mBimg;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
