package com.zzx.design.pattern.zzxdesignpattern.factory_method;

/**
 * @author zhouzhixiang
 * @company 叮当快药科技集团有限公司
 * @Date 2019-10-29
 */
public class ExportHtmlFactory implements ExportFactory {
    @Override
    public ExportFile factory(String type) {
        if (type != null && type.equals("standard")) {
            return new ExportStanardHtmlFile();
        } else if (type != null && type.equals("financial")) {
            return new ExportFinancialHtmlFile();
        } else {
            throw new RuntimeException("未知类型");
        }
    }
}
