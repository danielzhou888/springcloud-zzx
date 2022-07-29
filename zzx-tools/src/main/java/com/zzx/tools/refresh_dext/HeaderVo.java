//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zzx.tools.refresh_dext;

import java.io.Serializable;

public class HeaderVo implements Serializable {
    private static final long serialVersionUID = -8038655560302326675L;
    private String key;
    private String value;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HeaderVo(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
