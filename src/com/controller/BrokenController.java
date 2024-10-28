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
import com.entity.Broken;
import com.entity.Goods;
import com.github.pagehelper.Page;
import com.service.BrokenService;
import com.service.GoodsService;
import com.util.VeDate;

@RestController // 定义为控制器 返回JSON类型数据
@RequestMapping(value = "/broken", produces = "application/json; charset=utf-8") // 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class BrokenController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private BrokenService brokenService;
	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private GoodsService goodsService;

	// 预处理 获取基础参数
	@GetMapping(value = "createBroken.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createBroken() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", "B" + VeDate.getStringDatex());
		return map;
	}

	// 新增商品损失
	@PostMapping(value = "insertBroken.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertBroken(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Broken broken = new Broken();
		broken.setBno(obj.getString("bno")); // 为损失单号赋值
		broken.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		broken.setReason(obj.getString("reason")); // 为损失原因赋值
		broken.setNum(obj.getString("num")); // 为损失数量赋值
		broken.setAddtime(obj.getString("addtime")); // 为损失日期赋值
		broken.setManager(obj.getString("manager")); // 为经手人赋值
		broken.setOpers(obj.getString("opers")); // 为损失处理赋值
		broken.setMemo(obj.getString("memo")); // 为备注赋值
		Goods goods = this.goodsService.getGoodsById(obj.getString("goodsid"));
		if (Double.parseDouble(goods.getNum()) < Double.parseDouble(broken.getNum())) {
			map.put("success", false);
			map.put("code", 0);
			map.put("message", "库存不足");
			return map;
		}
		goods.setNum("" + (Double.parseDouble(goods.getNum()) - Double.parseDouble(broken.getNum())));
		this.goodsService.updateGoods(goods);
		int num = this.brokenService.insertBroken(broken);
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

	// 按主键删除一个商品损失
	@GetMapping(value = "deleteBroken.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBroken(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.brokenService.deleteBroken(id);
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

	// 按主键批量删除商品损失
	@PostMapping(value = "deleteBrokenByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteBrokenByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String brokenid : ids) {
			num += this.brokenService.deleteBroken(brokenid);
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

	// 修改商品损失
	@PostMapping(value = "updateBroken.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateBroken(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Broken broken = this.brokenService.getBrokenById(obj.getString("brokenid")); // 获取object中brokenid字段
		broken.setBno(obj.getString("bno")); // 为损失单号赋值
		broken.setGoodsid(obj.getString("goodsid")); // 为商品赋值
		broken.setReason(obj.getString("reason")); // 为损失原因赋值
		broken.setNum(obj.getString("num")); // 为损失数量赋值
		broken.setAddtime(obj.getString("addtime")); // 为损失日期赋值
		broken.setManager(obj.getString("manager")); // 为经手人赋值
		broken.setOpers(obj.getString("opers")); // 为损失处理赋值
		broken.setMemo(obj.getString("memo")); // 为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.brokenService.updateBroken(broken);
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

	// 查询全部商品损失数据 在下拉菜单中显示
	@GetMapping(value = "getAllBroken.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Broken> getAllBroken() {
		return this.brokenService.getAllBroken();
	}

	// 通过AJAX在表格中显示商品损失数据
	@GetMapping(value = "getBrokenByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getBrokenByPage(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Broken> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Broken> list = this.brokenService.getAllBroken();
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

	// 按主键查询商品损失数据
	@GetMapping(value = "getBrokenById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Broken getBrokenById(String id) {
		Broken broken = this.brokenService.getBrokenById(id);
		return broken;
	}

}
