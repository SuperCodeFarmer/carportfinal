package com.baidu.location.my;

/**
 * Created by yly on 2019/1/1.
 */

public class MyItem {
    private int itemimage;
    private String itemtext;
    public MyItem(int itemimage,String itemtext){
        this.itemimage= itemimage;
        this.itemtext=itemtext;
    }

    public void setItemimage(int itemimage) {
        this.itemimage = itemimage;
    }

    public void setItemtext(String itemtext) {
        this.itemtext = itemtext;
    }

    public int getItemimage() {
        return itemimage;
    }

    public String getItemtext() {
        return itemtext;
    }
}
