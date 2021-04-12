package com.zzx.design.pattern.zzxdesignpattern.decorator.io_sample;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhouzhixiang
 * @Date 2020-09-23
 */
public abstract class ZzxInputStream implements Closeable {

    private static final int MAX_SKIP_BUFFER_SIZE = 2048;

    public abstract int read() throws IOException;

    public int read(byte[] b, int off, int len) throws IOException {
        return 0;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public long skip(long n) throws IOException {
        return 0;
    }

    public int available() throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {}

    public synchronized void mark(long readLimit) throws IOException {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }

}
