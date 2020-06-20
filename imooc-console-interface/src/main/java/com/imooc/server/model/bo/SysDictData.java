package com.imooc.server.model.bo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
@Data
@Accessors(chain = true)
@TableName("SYS_DICT_DATA")
public class SysDictData implements Serializable {

	@TableId(value = "ID")
	private Integer id;			//主键ID
	private String dictType;	//字典类型
	private String dictCode;	//字典编码
	private String dictName;	//字典名
	private String remark;		//备注
	private Date createTime;	//创建时间
	private Date updateTime;	//更新时间
	private Integer no;			//顺序号
	private String isValid;		//是否有效
}
