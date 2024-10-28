package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Goods {
	private String goodsid = "G" + VeDate.getStringId(); // 生成主键编号
	private String gno; // 商品号
	private String goodsname; // 商品名称
	private String cateid; // 商品类型
	private String measureid; // 计量单位
	private String num; // 库存数量
	private String addtime; // 创建日期
	private String productor; // 生产厂商
	private String memo; // 备注
	private String catename; // 映射数据
	private String measurename; // 映射数据
	private Cate cate;// 多对一映射类
	private Measure measure;// 多对一映射类

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getGno() {
		return this.gno;
	}

	public void setGno(String gno) {
		this.gno = gno;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getCateid() {
		return this.cateid;
	}

	public void setCateid(String cateid) {
		this.cateid = cateid;
	}

	public String getMeasureid() {
		return this.measureid;
	}

	public void setMeasureid(String measureid) {
		this.measureid = measureid;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAddtime() {
		return this.addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getProductor() {
		return this.productor;
	}

	public void setProductor(String productor) {
		this.productor = productor;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Cate getCate() {
		return cate;
	}

	public void setCate(Cate cate) {
		this.cate = cate;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public String getMeasurename() {
		return this.measurename;
	}

	public void setMeasurename(String measurename) {
		this.measurename = measurename;
	}

	// 重载方法 生成JSON类型字符串
	@Override
	public String toString() {
		return this.toJsonString();
	}

	// 直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("goodsid", this.goodsid); // 主键编号
		jsonString.put("gno", this.gno); // 商品号
		jsonString.put("goodsname", this.goodsname); // 商品名称
		jsonString.put("cateid", this.cateid); // 商品类型
		jsonString.put("measureid", this.measureid); // 计量单位
		jsonString.put("num", this.num); // 库存数量
		jsonString.put("addtime", this.addtime); // 创建日期
		jsonString.put("productor", this.productor); // 生产厂商
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Cate", this.cate); // 多对一映射类
		jsonString.put("Measure", this.measure); // 多对一映射类
		jsonString.put("catename", this.catename); // 映射数据
		jsonString.put("measurename", this.measurename); // 映射数据
		return jsonString.toString();
	}

}
