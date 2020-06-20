package com.imooc.server.common;

import java.io.Serializable;

public class Page implements Serializable {
    private long pageSize = 20L;
    private long pageIndex = 0L;

    public Page() {
    }

    public Page(long pageIndex, long pageSize) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageIndex() {
        return this.pageIndex;
    }

    public Page setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }
}
