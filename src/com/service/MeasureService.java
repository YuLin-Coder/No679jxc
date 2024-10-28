package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Measure;
@Service("measureService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface MeasureService {
	// 插入计量单位表数据 调用measureDAO里的insertMeasure配置
	public int insertMeasure(Measure measure);

	// 更新计量单位表数据 调用measureDAO里的updateMeasure配置
	public int updateMeasure(Measure measure);

	// 按主键删除计量单位表数据 调用measureDAO里的deleteMeasure配置
	public int deleteMeasure(String measureid);

	// 批量删除计量单位表数据 调用mapper包measure.xml里的deleteMeasureByIds配置 返回值0(失败),大于0(成功)
	public int deleteMeasureByIds(String[] ids);

	// 查询全部数据 调用measureDAO里的getAllMeasure配置
	public List<Measure> getAllMeasure();

	// 按照Measure类里面的字段名称精确查询 调用measureDAO里的getMeasureByCond配置
	public List<Measure> getMeasureByCond(Measure measure);

	// 按照Measure类里面的字段名称模糊查询 调用measureDAO里的getMeasureByLike配置
	public List<Measure> getMeasureByLike(Measure measure);

	// 按主键查询表返回单一的Measure实例 调用measureDAO里的getMeasureById配置
	public Measure getMeasureById(String measureid);

}

