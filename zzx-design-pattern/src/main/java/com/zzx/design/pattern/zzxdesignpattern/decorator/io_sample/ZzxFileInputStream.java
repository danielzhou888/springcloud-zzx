package com.zzx.design.pattern.zzxdesignpattern.decorator.io_sample;

import java.io.IOException;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public class ZzxFileInputStream extends ZzxInputStream {

    @Override
    public int read() throws IOException {
        return 0;
    }

    public static void main(String[] args) {
        int[] src = {1,2,3,4,5,6,7,8};
        int[] dest = new int[4];
        System.arraycopy(src, 2, dest, 0, 4);
        System.out.println(dest);
    }

}
