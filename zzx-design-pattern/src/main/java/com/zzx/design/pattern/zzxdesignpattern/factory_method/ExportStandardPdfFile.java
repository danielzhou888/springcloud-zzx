package com.zzx.design.pattern.zzxdesignpattern.factory_method;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class ExportStandardPdfFile implements ExportFile {
    @Override
    public void export(String data) {
        System.out.println("导出 standard pdf file" + data);
    }
}
