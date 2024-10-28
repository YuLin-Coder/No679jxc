package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.entity.Buy;
import com.entity.Goods;
import com.github.pagehelper.Page;
import com.service.BuyService;
import com.service.GoodsService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/buy", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class BuyController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BuyService buyService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private GoodsService goodsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createBuy.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createBuy() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("bno", "B" + VeDate.getStringDatex());
		return map;
	}

	// 新增商品采购
	@PostMapping(value = "insertBuy.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertBuy(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Buy buy = new Buy();
		buy.setBno(obj.getString("bno")); // 为采购单号赋值
		buy.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		buy.setNum(obj.getString("num")); // 为采购数量赋值
		buy.setPrice(obj.getString("price")); // 为采购单价赋值
		buy.setTotal("" + VeDate
				.getDouble(Double.parseDouble(obj.getString("num")) * Double.parseDouble(obj.getString("price")))); // 为总计赋值
		buy.setAddtime(VeDate.getStringDateShort()); // 为采购日期赋值
		buy.setManager(obj.getString("manager")); // 为经手人赋值
		buy.setMemo(obj.getString("memo")); // 为备注赋值
		Goods goods = this.goodsService.getGoodsById(obj.getString("goodsid"));
		goods.setNum("" + (Double.parseDouble(goods.getNum()) + Double.parseDouble(obj.getString("num"))));
		this.goodsService.updateGoods(goods);
		System.out.println(buy.getTotal());
		int num = this.buyService.insertBuy(buy);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "保存成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "保存失败");
		}
		return map;
	}

	// 按主键删除一个商品采购
	@GetMapping(value = "deleteBuy.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBuy(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.buyService.deleteBuy(id);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "删除成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "删除失败");
		}
		return map;
	}

	// 按主键批量删除商品采购
	@PostMapping(value = "deleteBuyByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBuyByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String buyid : ids) {
			num += this.buyService.deleteBuy(buyid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "删除成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "删除失败");
		}
		return map;
	}

	// 修改商品采购
	@PostMapping(value = "updateBuy.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateBuy(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Buy buy = this.buyService.getBuyById(obj.getString("buyid")); // 获取object中buyid字段
		buy.setBno(obj.getString("bno")); // 为采购单号赋值
		buy.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		buy.setNum(obj.getString("num")); // 为采购数量赋值
		buy.setPrice(obj.getString("price")); // 为采购单价赋值
		buy.setManager(obj.getString("manager")); // 为经手人赋值
		buy.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.buyService.updateBuy(buy);
		if (num > 0) {
			map.put("success", true);
			map.put("code", num);
			map.put("message", "修改成功");
		} else {
			map.put("success", false);
			map.put("code", num);
			map.put("message", "修改失败");
		}
		return map;
	}

	// 查询全部商品采购数据 在下拉菜单中显示
	@GetMapping(value = "getAllBuy.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Buy> getAllBuy() {
		return this.buyService.getAllBuy();
	}

	// 通过AJAX在表格中显示商品采购数据
	@GetMapping(value = "getBuyByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBuyByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Buy> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Buy> list = this.buyService.getAllBuy();
		// 返回的map中定义数据格式
		map.put("count", pager.getTotal());
		map.put("total", list.size());
		map.put("data", list);
		map.put("code", 0);
		map.put("msg", "");
		map.put("page", page);
		map.put("limit", limit);
		return map;
	}

	// 按主键查询商品采购数据
	@GetMapping(value = "getBuyById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Buy getBuyById(String id) {
		Buy buy = this.buyService.getBuyById(id);
		return buy;
	}

}
