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
import com.entity.Measure;
import com.github.pagehelper.Page;
import com.service.MeasureService;
import com.util.VeDate;

@RestController //定义为控制器 返回JSON类型数据
@RequestMapping(value = "/measure", produces = "application/json; charset=utf-8")// 设置请求路径
@CrossOrigin // 允许跨域访问其资源
public class MeasureController extends BaseController {

	@Autowired // @Autowired的作用是自动注入依赖的ServiceBean
	private MeasureService measureService;

	// 预处理 获取基础参数
	@GetMapping(value = "createMeasure.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> createMeasure() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("today", VeDate.getStringDateShort());
		return map;
	}

	// 新增计量单位
	@PostMapping(value = "insertMeasure.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> insertMeasure(@RequestBody String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Measure measure = new Measure();
		measure.setMeasurename(obj.getString("measurename")); //  为单位名称赋值
		measure.setAddtime(VeDate.getStringDateShort()); // 为创建日期赋值 
		measure.setMemo(obj.getString("memo")); //  为备注赋值
		int num = this.measureService.insertMeasure(measure);
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

	// 按主键删除一个计量单位
	@GetMapping(value = "deleteMeasure.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteMeasure(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.measureService.deleteMeasure(id);
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

	// 按主键批量删除计量单位
	@PostMapping(value = "deleteMeasureByIds.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> deleteMeasureByIds(@RequestBody String[] ids) {
		int num = 0;
		for (String measureid : ids) {
			num += this.measureService.deleteMeasure(measureid);
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

	// 修改计量单位
	@PostMapping(value = "updateMeasure.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> updateMeasure(@RequestBody String jsonStr) {
		JSONObject obj = JSONObject.parseObject(jsonStr); // 将JSON字符串转换成object
		Measure measure = this.measureService.getMeasureById(obj.getString("measureid")); // 获取object中measureid字段
		measure.setMeasurename(obj.getString("measurename")); //  为单位名称赋值
		measure.setMemo(obj.getString("memo")); //  为备注赋值

		Map<String, Object> map = new HashMap<String, Object>();
		int num = this.measureService.updateMeasure(measure);
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

	// 查询全部计量单位数据 在下拉菜单中显示
	@GetMapping(value = "getAllMeasure.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public List<Measure> getAllMeasure() {
		return this.measureService.getAllMeasure();
	}

	// 通过AJAX在表格中显示计量单位数据
	@GetMapping(value = "getMeasureByPage.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Map<String, Object> getMeasureByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
		// 定义一个Map对象 用来返回数据
		Map<String, Object> map = new HashMap<String, Object>();
		Page<Measure> pager = com.github.pagehelper.PageHelper.startPage(page, limit);// 定义当前页和分页条数
		List<Measure> list = this.measureService.getAllMeasure();
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

	// 按主键查询计量单位数据
	@GetMapping(value = "getMeasureById.action")
	@ResponseBody // 将java对象转为json格式的数据返回
	public Measure getMeasureById(String id) {
		Measure measure = this.measureService.getMeasureById(id);
		return measure;
	}

}


