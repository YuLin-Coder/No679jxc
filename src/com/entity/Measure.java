package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Measure {
	private String measureid = "M" + VeDate.getStringId(); // 生成主键编号
	private String measurename; // 单位名称
	private String addtime; // 创建日期
	private String memo; // 备注

	public String getMeasureid() {
		return measureid;
	}

	public void setMeasureid(String measureid) {
		this.measureid = measureid;
	}

	public String getMeasurename() {
		return this.measurename;
	}

	public void setMeasurename(String measurename) {
		this.measurename = measurename;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	// 重载方法 生成JSON类型字符串
	@Override
	public String toString() {
		return this.toJsonString();
	}

	// 直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("measureid", this.measureid); // 主键编号
		jsonString.put("measurename", this.measurename); // 单位名称
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("memo", this.memo); // 备注
		return jsonString.toString();
	}

}
