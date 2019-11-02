package com.zzx.design.pattern.zzxdesignpattern.factory_method;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class Main {

    public static void main(String[] args) {
        ExportPdfFactory factory = new ExportPdfFactory();
        factory.factory("financial").export("财政报表");
    }
}
