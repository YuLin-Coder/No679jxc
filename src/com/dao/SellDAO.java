package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Sell;

@Repository("sellDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到SellServiceImpl中
public interface SellDAO {

	/**
	* SellDAO 接口 可以按名称直接调用sell.xml配置文件的SQL语句
	*/

	// 插入商品销售表数据 调用mapper包sell.xml里的insertSell配置 返回值0(失败),1(成功)
	public int insertSell(Sell sell);

	// 更新商品销售表数据 调用mapper包sell.xml里的updateSell配置 返回值0(失败),1(成功)
	public int updateSell(Sell sell);

	// 按主键删除商品销售表数据 调用mapper包sell.xml里的deleteSell配置 返回值0(失败),1(成功)
	public int deleteSell(String sellid);

	// 批量删除商品销售表数据 调用mapper包sell.xml里的deleteSellByIds配置 返回值0(失败),大于0(成功)
	public int deleteSellByIds(String[] ids);

	// 查询商品销售表全部数据 调用mapper包sell.xml里的getAllSell配置 返回List<Sell>类型的数据
	public List<Sell> getAllSell();

	// 按照Sell类里面的值精确查询 调用mapper包sell.xml里的getSellByCond配置 返回List<Sell>类型的数据
	public List<Sell> getSellByCond(Sell sell);

	// 按照Sell类里面的值模糊查询 调用mapper包sell.xml里的getSellByLike配置 返回List<Sell>类型的数据
	public List<Sell> getSellByLike(Sell sell);

	// 按主键查询商品销售表返回单一的Sell实例 调用mapper包sell.xml里的getSellById配置
	public Sell getSellById(String sellid);

}


