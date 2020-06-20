package com.imooc.server.model.vo;

import com.imooc.server.common.BaseRequest;
import com.imooc.server.exception.CommonServiceException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;


@Data
@Accessors(chain = true)
public class SysDictTypeVO extends BaseRequest implements Serializable {

    private Integer id;			//主键ID
    private String dictType;	//字典类型
    private String typeName;	//字典类型名name
    private String isValid;		//是否有效
    private Date createTime;	//创建时间
    private Date updateTime;	//更新时间

    //分页信息
    private int pageSize = 20;
    private int pageIndex = 0;
    private String sortField;
    private String sortOrder;

	@Override
	public void checkParam() throws CommonServiceException {
        if (StringUtils.isEmpty(dictType)) {
            throw new CommonServiceException(404, "类型编码不能为空!");
        }
        if (StringUtils.isEmpty(typeName)) {
            throw new CommonServiceException(404, "类型名称不能为空!");
        }
        if (StringUtils.isEmpty(isValid)) {
            throw new CommonServiceException(404, "是否有效不能为空!");
        }
	}
}
