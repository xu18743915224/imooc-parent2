package com.imooc.server.common;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PageQuery implements Serializable {
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;
}