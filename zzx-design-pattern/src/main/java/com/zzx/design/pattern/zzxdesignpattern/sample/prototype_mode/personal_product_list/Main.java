package com.zzx.design.pattern.zzxdesignpattern.sample.prototype_mode.personal_product_list;

/**
 * https://app.yinxiang.com/fx/7c731069-0bb0-448e-b444-0da2cb61dadc
 * 原型模式-实时生成个性化的商品推荐列表
 * @author 周志祥
 */
public class Main {
    public static void main(String[] args) {
        // 原型商品
        ConcreteProduct product = new ConcreteProduct("原型商品", 100.0);

        // 克隆第一个推荐商品
        Product recommendProduct1 = product.clone();
        recommendProduct1.setDiscount(0.1);
        recommendProduct1.display();

        // 克隆第二个推荐商品
        Product recommendProduct2 = product.clone();
        recommendProduct2.setPrice(88.0);
        recommendProduct2.setDiscount(0.2);
        recommendProduct2.display();

        recommendProduct1.display();
    }
}
