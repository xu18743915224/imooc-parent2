package com.imooc.server.common;

import java.util.List;
import java.util.Map;

public class PageResult extends Page {
    private static final long serialVersionUID = -1762399253732416203L;
    private long total = 0L;
    private List<?> data;
    private Map<String, Object> summary;

    public PageResult() {
    }

    public PageResult(long pageIndex, long pageSize, long total, List<?> data) {
        super(pageIndex, pageSize);
        this.total = total;
        this.data = data;
    }

    public long getPageCount() {
        if (this.total > 0L && this.getPageSize() > 0L) {
            return this.total % this.getPageSize() == 0L ? this.total / this.getPageSize() : this.total / this.getPageSize() + 1L;
        } else {
            return 0L;
        }
    }

    public List<?> getData() {
        return this.data;
    }

    public PageResult setData(List<?> data) {
        this.data = data;
        return this;
    }

    public Map<String, Object> getSummary() {
        return this.summary;
    }

    public PageResult setSummary(Map<String, Object> summary) {
        this.summary = summary;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public PageResult setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getLastPageIndex() {
        if (this.total > 0L && this.getPageSize() > 0L) {
            return this.total % this.getPageSize() == 0L ? this.total / this.getPageSize() - 1L : this.total / this.getPageSize();
        } else {
            return 0L;
        }
    }
}
