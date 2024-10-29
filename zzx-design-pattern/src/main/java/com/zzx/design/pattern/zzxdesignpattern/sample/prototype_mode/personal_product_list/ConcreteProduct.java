package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.personal_product_list;

public class ConcreteProduct implements Product {

    private String name;
    private double price;
    private double discount;

    public ConcreteProduct(String name, double price) {
        this.name = name;
        this.price = price;
        this.discount = 0.0;
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void display() {
        System.out.println(String.format("商品名称：%s, 价格：%s, 折扣：%s", name, price, discount));
    }
}
