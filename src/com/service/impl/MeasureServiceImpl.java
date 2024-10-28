package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.MeasureDAO;
import com.entity.Measure;
import com.service.MeasureService;

@Service("measureService") //
public class MeasureServiceImpl implements MeasureService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private MeasureDAO measureDAO;
	@Override // 继承接口的新增计量单位表数据 返回值0(失败),1(成功)
	public int insertMeasure(Measure measure) {
		return this.measureDAO.insertMeasure(measure);
	}

	@Override // 继承接口的更新计量单位表数据 返回值0(失败),1(成功)
	public int updateMeasure(Measure measure) {
		return this.measureDAO.updateMeasure(measure);
	}

	@Override // 继承接口的按主键删除计量单位表数据 返回值0(失败),1(成功)
	public int deleteMeasure(String measureid) {
		return this.measureDAO.deleteMeasure(measureid);
	}

	@Override // 继承接口的批量删除计量单位表数据 返回值0(失败),大于0(成功)
	public int deleteMeasureByIds(String[] ids) {
		return this.measureDAO.deleteMeasureByIds(ids);
	}

	@Override // 继承接口的查询计量单位表全部数据
	public List<Measure> getAllMeasure() {
		return this.measureDAO.getAllMeasure();
	}

	@Override // 继承接口的按条件精确查询计量单位表数据
	public List<Measure> getMeasureByCond(Measure measure) {
		return this.measureDAO.getMeasureByCond(measure);
	}

	@Override // 继承接口的按条件模糊查询计量单位表数据
	public List<Measure> getMeasureByLike(Measure measure) {
		return this.measureDAO.getMeasureByLike(measure);
	}

	@Override // 继承接口的按主键查询计量单位表数据 返回Entity实例
	public Measure getMeasureById(String measureid) {
		return this.measureDAO.getMeasureById(measureid);
	}

}

