package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Broken {
	private String brokenid = "B" + VeDate.getStringId(); // 生成主键编号
	private String bno; // 损失单号
	private String goodsid; // 商品
	private String reason; // 损失原因
	private String num; // 损失数量
	private String addtime; // 损失日期
	private String manager; // 经手人
	private String opers; // 损失处理
	private String memo; // 备注
	private String goodsname; // 映射数据
	private Goods goods;// 多对一映射类

	public String getBrokenid() {
		return brokenid;
	}

	public void setBrokenid(String brokenid) {
		this.brokenid = brokenid;
	}

	public String getBno() {
		return this.bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getGoodsid() {
		return this.goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOpers() {
		return this.opers;
	}

	public void setOpers(String opers) {
		this.opers = opers;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	// 重载方法 生成JSON类型字符串
	@Override
	public String toString() {
		return this.toJsonString();
	}

	// 直接转换成JSON字符串
	private String toJsonString() {
		JSONObject jsonString = new JSONObject();
		jsonString.put("brokenid", this.brokenid); // 主键编号
		jsonString.put("bno", this.bno); // 损失单号
		jsonString.put("goodsid", this.goodsid); // 商品
		jsonString.put("reason", this.reason); // 损失原因
		jsonString.put("num", this.num); // 损失数量
		jsonString.put("addtime", this.addtime); // 损失日期
		jsonString.put("manager", this.manager); // 经手人
		jsonString.put("opers", this.opers); // 损失处理
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Goods", this.goods); // 多对一映射类
		jsonString.put("goodsname", this.goodsname); // 映射数据
		return jsonString.toString();
	}

}
