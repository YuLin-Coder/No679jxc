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
import com.entity.Goods;
import com.entity.Sell;
import com.github.pagehelper.Page;
import com.service.GoodsService;
import com.service.SellService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/sell", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class SellController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private SellService sellService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private GoodsService goodsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createSell.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createSell() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sno", "S" + VeDate.getStringDatex());
		return map;
	}

	// 新增商品销售
	@PostMapping(value = "insertSell.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertSell(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Sell sell = new Sell();
		sell.setSno(obj.getString("sno")); // 为销售单号赋值
		sell.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		sell.setNum(obj.getString("num")); // 为销售数量赋值
		sell.setPrice(obj.getString("price")); // 为销售单价赋值
		sell.setTotal("" + VeDate.getDouble(Double.parseDouble(obj.getString("num")) * Double.parseDouble(obj.getString("price")))); // 为销售总计赋值
		sell.setAddtime(obj.getString("addtime")); // 为销售日期赋值
		sell.setManager(obj.getString("manager")); // 为经手人赋值
		sell.setMemo(obj.getString("memo")); // 为备注赋值
		Goods goods = this.goodsService.getGoodsById(obj.getString("goodsid"));
		if (Double.parseDouble(goods.getNum()) < Double.parseDouble(obj.getString("num"))) {
			map.put("success", false);
			map.put("code", 0);
			map.put("message", "库存不足");
			return map;
		}
		goods.setNum("" + (Double.parseDouble(goods.getNum()) - Double.parseDouble(sell.getNum())));
		this.goodsService.updateGoods(goods);
		int num = this.sellService.insertSell(sell);
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

	// 按主键删除一个商品销售
	@GetMapping(value = "deleteSell.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteSell(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.sellService.deleteSell(id);
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

	// 按主键批量删除商品销售
	@PostMapping(value = "deleteSellByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteSellByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String sellid : ids) {
			num += this.sellService.deleteSell(sellid);
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

	// 修改商品销售
	@PostMapping(value = "updateSell.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateSell(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Sell sell = this.sellService.getSellById(obj.getString("sellid")); // 获取object中sellid字段
		sell.setSno(obj.getString("sno")); // 为销售单号赋值
		sell.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		sell.setNum(obj.getString("num")); // 为销售数量赋值
		sell.setPrice(obj.getString("price")); // 为销售单价赋值
		sell.setAddtime(obj.getString("addtime")); // 为销售日期赋值
		sell.setManager(obj.getString("manager")); // 为经手人赋值
		sell.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.sellService.updateSell(sell);
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

	// 查询全部商品销售数据 在下拉菜单中显示
	@GetMapping(value = "getAllSell.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Sell> getAllSell() {
		return this.sellService.getAllSell();
	}

	// 通过AJAX在表格中显示商品销售数据
	@GetMapping(value = "getSellByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getSellByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Sell> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Sell> list = this.sellService.getAllSell();
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

	// 按主键查询商品销售数据
	@GetMapping(value = "getSellById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Sell getSellById(String id) {
		Sell sell = this.sellService.getSellById(id);
		return sell;
	}

}
