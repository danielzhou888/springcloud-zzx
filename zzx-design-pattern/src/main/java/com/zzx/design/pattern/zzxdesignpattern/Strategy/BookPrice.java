package com.zzx.design.pattern.zzxdesignpattern.Strategy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 图书价格类
 */
@Data
@AllArgsConstructor
public class BookPrice {
    private Member member;

    public double calcute(Double bookPrice) {
        return member.bookCalcute(bookPrice);
    }
}
