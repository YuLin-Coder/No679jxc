package com.entity;

import com.util.VeDate;
import com.alibaba.fastjson.JSONObject;

public class Buy {
	private String buyid = "B" + VeDate.getStringId(); // 生成主键编号
	private String bno; // 采购单号
	private String goodsid; // 商品
	private String num; // 采购数量
	private String price; // 采购单价
	private String total; // 总计
	private String addtime; // 采购日期
	private String manager; // 经手人
	private String memo; // 备注
	private String goodsname; // 映射数据
	private Goods goods;// 多对一映射类

	public String getBuyid() {
		return buyid;
	}

	public void setBuyid(String buyid) {
		this.buyid = buyid;
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

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = total;
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
		jsonString.put("buyid", this.buyid); // 主键编号
		jsonString.put("bno", this.bno); // 采购单号
		jsonString.put("goodsid", this.goodsid); // 商品
		jsonString.put("num", this.num); // 采购数量
		jsonString.put("price", this.price); // 采购单价
		jsonString.put("total", this.total); // 总计
		jsonString.put("addtime", this.addtime); // 采购日期
		jsonString.put("manager", this.manager); // 经手人
		jsonString.put("memo", this.memo); // 备注
		jsonString.put("Goods", this.goods); // 多对一映射类
		jsonString.put("goodsname", this.goodsname); // 映射数据
		return jsonString.toString();
	}

}
