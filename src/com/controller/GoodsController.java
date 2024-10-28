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
import com.github.pagehelper.Page;
import com.service.GoodsService;
import com.util.VeDate;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/goods", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class GoodsController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private GoodsService goodsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createGoods.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		map.put("gno", "G" + VeDate.getStringDatex());
		return map;
	}

	// 新增商品
	@PostMapping(value = "insertGoods.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertGoods(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Goods goods = new Goods();
		goods.setGno(obj.getString("gno")); //  为商品号赋值
		goods.setGoodsname(obj.getString("goodsname")); //  为商品名称赋值
		goods.setCateid(obj.getString("cateid")); //  为商品类型赋值
		goods.setMeasureid(obj.getString("measureid")); //  为计量单位赋值
		goods.setNum("0"); //  为库存数量赋值
		goods.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值 
		goods.setProductor(obj.getString("productor")); //  为生产厂商赋值
		goods.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.goodsService.insertGoods(goods);
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

	// 按主键删除一个商品
	@GetMapping(value = "deleteGoods.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteGoods(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.goodsService.deleteGoods(id);
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

	// 按主键批量删除商品
	@PostMapping(value = "deleteGoodsByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteGoodsByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String goodsid : ids) {
			num += this.goodsService.deleteGoods(goodsid);
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

	// 修改商品
	@PostMapping(value = "updateGoods.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateGoods(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Goods goods = this.goodsService.getGoodsById(obj.getString("goodsid")); // 获取object中goodsid字段
		goods.setGno(obj.getString("gno")); //  为商品号赋值
		goods.setGoodsname(obj.getString("goodsname")); //  为商品名称赋值
		goods.setCateid(obj.getString("cateid")); //  为商品类型赋值
		goods.setMeasureid(obj.getString("measureid")); //  为计量单位赋值
		goods.setProductor(obj.getString("productor")); //  为生产厂商赋值
		goods.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.goodsService.updateGoods(goods);
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

	// 查询全部商品数据 在下拉菜单中显示
	@GetMapping(value = "getAllGoods.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Goods> getAllGoods() {
		return this.goodsService.getAllGoods();
	}

	// 通过AJAX在表格中显示商品数据
	@GetMapping(value = "getGoodsByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getGoodsByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Goods> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Goods> list = this.goodsService.getAllGoods();
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

	// 按主键查询商品数据
	@GetMapping(value = "getGoodsById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Goods getGoodsById(String id) {
		Goods goods = this.goodsService.getGoodsById(id);
		return goods;
	}

}


