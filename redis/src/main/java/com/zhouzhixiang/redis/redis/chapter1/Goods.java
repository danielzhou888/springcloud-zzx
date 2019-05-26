package com.zhouzhixiang.redis.redis.chapter1;

public class Goods {
    private String goodsno;
    private String goodsname;
    private Double price;

    public Goods(String goodsno, String goodsname, Double price) {
        this.goodsno = goodsno;
        this.goodsname = goodsname;
        this.price = price;
    }

    public String getGoodsno() {
        return goodsno;
    }

    public void setGoodsno(String goodsno) {
        this.goodsno = goodsno;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
