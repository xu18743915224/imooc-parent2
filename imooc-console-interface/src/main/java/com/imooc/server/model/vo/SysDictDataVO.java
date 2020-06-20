package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Data
@Accessors(chain = true)
public class SysDictDataVO extends BaseRequest implements Serializable {

    private Integer id;			//主键ID
    private String dictType;	//字典类型
    private String dictCode;	//字典编码
    private String dictName;	//字典名
    private String remark;		//备注
    private Date createTime;	//创建时间
    private Date updateTime;	//更新时间
    private Integer no;			//顺序号
    private String isValid;		//是否有效

    //分页信息
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;

	@Override
	public void checkParam() throws CommonServiceException {

	}
}
