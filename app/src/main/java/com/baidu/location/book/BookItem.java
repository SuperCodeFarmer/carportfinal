package com.baidu.location.book;

import android.provider.ContactsContract;

import org.litepal.crud.DataSupport;

/**
 * Created by yly on 2018/12/31.
 */

public class BookItem extends DataSupport{
    private String carportAddress;
    private String carportCost;
    private String carportTimeout;//出租时长
    private String carportTime;//发布时间
    private String carportDetail;//出租详情
    private byte[] carportImage;//存储图片


    public void setCarportTime(String carportTime) {
        this.carportTime = carportTime;
    }

    public void setCarportDetail(String carportDetail) {
        this.carportDetail = carportDetail;
    }


    public void setCarportAddress(String carportAddress) {
        this.carportAddress = carportAddress;
    }

    public void setCarportCost(String carportCost) {
        this.carportCost = carportCost;
    }

    public void setCarportTimeout(String carportTimeout) {
        this.carportTimeout = carportTimeout;
    }

    public void setCarportImage(byte[] carportImage) {
        this.carportImage = carportImage;
    }

    public String getCarportAddress() {
        return carportAddress;
    }

    public String getCarportCost() {
        return carportCost;
    }

    public String getCarportTimeout() {
        return carportTimeout;
    }

    public String getCarportTime() {
        return carportTime;
    }

    public String getCarportDetail() {
        return carportDetail;
    }

    public byte[] getCarportImage() {
        return carportImage;
    }
}
