package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.personal_product_list;

/**
 * 可克隆的商品接口
 */
public interface Product extends Cloneable {
    Product clone();
    void setPrice(double price);
    void setDiscount(double discount);
    void display();
}
