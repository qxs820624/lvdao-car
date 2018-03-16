package com.lvdao.entity;

import java.io.Serializable;
/**
 * 数据字典表 t_dict
 * date: 2016年9月6日 下午7:04:55 
 * @author wangyu
 */
public class DictEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1946168182467501357L;
	
    private String  dictId;//数据字典ID
    private String  dictName;//字典名称
    private String  dictValue;//数据字典值
    private String dictType;//字典类型 0 组 1字典
    private String  dictGroupId;//数据字典所属组ID
    private String  dictGroupName;//数据字典所属组的名称
    private Integer  dictOrder;//在同一个group的排列顺序
    private String  dictDesc;//数据字典描述
    
	public synchronized String getDictType() {
		return dictType;
	}
	public synchronized void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getDictGroupId() {
		return dictGroupId;
	}
	public void setDictGroupId(String dictGroupId) {
		this.dictGroupId = dictGroupId;
	}
	public String getDictGroupName() {
		return dictGroupName;
	}
	public void setDictGroupName(String dictGroupName) {
		this.dictGroupName = dictGroupName;
	}
	public Integer getDictOrder() {
		return dictOrder;
	}
	public void setDictOrder(Integer dictOrder) {
		this.dictOrder = dictOrder;
	}
	public String getDictDesc() {
		return dictDesc;
	}
	public void setDictDesc(String dictDesc) {
		this.dictDesc = dictDesc;
	}

}