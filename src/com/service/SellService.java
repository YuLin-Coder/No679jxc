package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Sell;
@Service("sellService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface SellService {
	// 插入商品销售表数据 调用sellDAO里的insertSell配置
	public int insertSell(Sell sell);

	// 更新商品销售表数据 调用sellDAO里的updateSell配置
	public int updateSell(Sell sell);

	// 按主键删除商品销售表数据 调用sellDAO里的deleteSell配置
	public int deleteSell(String sellid);

	// 批量删除商品销售表数据 调用mapper包sell.xml里的deleteSellByIds配置 返回值0(失败),大于0(成功)
	public int deleteSellByIds(String[] ids);

	// 查询全部数据 调用sellDAO里的getAllSell配置
	public List<Sell> getAllSell();

	// 按照Sell类里面的字段名称精确查询 调用sellDAO里的getSellByCond配置
	public List<Sell> getSellByCond(Sell sell);

	// 按照Sell类里面的字段名称模糊查询 调用sellDAO里的getSellByLike配置
	public List<Sell> getSellByLike(Sell sell);

	// 按主键查询表返回单一的Sell实例 调用sellDAO里的getSellById配置
	public Sell getSellById(String sellid);

}

