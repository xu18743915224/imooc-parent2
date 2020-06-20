package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("SYS_DICT_TYPE")
public class SysDictType implements Serializable {

	@TableId(value = "ID")
	private Integer id;			//主键ID
	private String dictType;	//字典类型
	private String typeName;	//字典类型名name
	private String isValid;		//是否有效
	private Date createTime;	//创建时间
	private Date updateTime;	//更新时间
}
