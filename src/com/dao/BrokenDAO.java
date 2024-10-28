package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Broken;

@Repository("brokenDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到BrokenServiceImpl中
public interface BrokenDAO {

	/**
	* BrokenDAO 接口 可以按名称直接调用broken.xml配置文件的SQL语句
	*/

	// 插入商品损失表数据 调用mapper包broken.xml里的insertBroken配置 返回值0(失败),1(成功)
	public int insertBroken(Broken broken);

	// 更新商品损失表数据 调用mapper包broken.xml里的updateBroken配置 返回值0(失败),1(成功)
	public int updateBroken(Broken broken);

	// 按主键删除商品损失表数据 调用mapper包broken.xml里的deleteBroken配置 返回值0(失败),1(成功)
	public int deleteBroken(String brokenid);

	// 批量删除商品损失表数据 调用mapper包broken.xml里的deleteBrokenByIds配置 返回值0(失败),大于0(成功)
	public int deleteBrokenByIds(String[] ids);

	// 查询商品损失表全部数据 调用mapper包broken.xml里的getAllBroken配置 返回List<Broken>类型的数据
	public List<Broken> getAllBroken();

	// 按照Broken类里面的值精确查询 调用mapper包broken.xml里的getBrokenByCond配置 返回List<Broken>类型的数据
	public List<Broken> getBrokenByCond(Broken broken);

	// 按照Broken类里面的值模糊查询 调用mapper包broken.xml里的getBrokenByLike配置 返回List<Broken>类型的数据
	public List<Broken> getBrokenByLike(Broken broken);

	// 按主键查询商品损失表返回单一的Broken实例 调用mapper包broken.xml里的getBrokenById配置
	public Broken getBrokenById(String brokenid);

}


