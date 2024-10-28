package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Measure;

@Repository("measureDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到MeasureServiceImpl中
public interface MeasureDAO {

	/**
	* MeasureDAO 接口 可以按名称直接调用measure.xml配置文件的SQL语句
	*/

	// 插入计量单位表数据 调用mapper包measure.xml里的insertMeasure配置 返回值0(失败),1(成功)
	public int insertMeasure(Measure measure);

	// 更新计量单位表数据 调用mapper包measure.xml里的updateMeasure配置 返回值0(失败),1(成功)
	public int updateMeasure(Measure measure);

	// 按主键删除计量单位表数据 调用mapper包measure.xml里的deleteMeasure配置 返回值0(失败),1(成功)
	public int deleteMeasure(String measureid);

	// 批量删除计量单位表数据 调用mapper包measure.xml里的deleteMeasureByIds配置 返回值0(失败),大于0(成功)
	public int deleteMeasureByIds(String[] ids);

	// 查询计量单位表全部数据 调用mapper包measure.xml里的getAllMeasure配置 返回List<Measure>类型的数据
	public List<Measure> getAllMeasure();

	// 按照Measure类里面的值精确查询 调用mapper包measure.xml里的getMeasureByCond配置 返回List<Measure>类型的数据
	public List<Measure> getMeasureByCond(Measure measure);

	// 按照Measure类里面的值模糊查询 调用mapper包measure.xml里的getMeasureByLike配置 返回List<Measure>类型的数据
	public List<Measure> getMeasureByLike(Measure measure);

	// 按主键查询计量单位表返回单一的Measure实例 调用mapper包measure.xml里的getMeasureById配置
	public Measure getMeasureById(String measureid);

}


