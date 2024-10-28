package com.dao;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.entity.Goods;

@Repository("goodsDAO") // Repository标签定义数据库连接的访问 Spring中直接扫描加载
@Mapper // 不需要在spring配置中设置扫描地址 spring将动态的生成Bean后注入到GoodsServiceImpl中
public interface GoodsDAO {

	/**
	* GoodsDAO 接口 可以按名称直接调用goods.xml配置文件的SQL语句
	*/

	// 插入商品表数据 调用mapper包goods.xml里的insertGoods配置 返回值0(失败),1(成功)
	public int insertGoods(Goods goods);

	// 更新商品表数据 调用mapper包goods.xml里的updateGoods配置 返回值0(失败),1(成功)
	public int updateGoods(Goods goods);

	// 按主键删除商品表数据 调用mapper包goods.xml里的deleteGoods配置 返回值0(失败),1(成功)
	public int deleteGoods(String goodsid);

	// 批量删除商品表数据 调用mapper包goods.xml里的deleteGoodsByIds配置 返回值0(失败),大于0(成功)
	public int deleteGoodsByIds(String[] ids);

	// 查询商品表全部数据 调用mapper包goods.xml里的getAllGoods配置 返回List<Goods>类型的数据
	public List<Goods> getAllGoods();

	// 查询最新上架商品
	public List<Goods> getGoodsByNews();

	// 查询热门商品
	public List<Goods> getGoodsByHot();

	// 查询按分类查询N个商品首页显示
	public List<Goods> getGoodsByCate(String cateid);

	// 按照Goods类里面的值精确查询 调用mapper包goods.xml里的getGoodsByCond配置 返回List<Goods>类型的数据
	public List<Goods> getGoodsByCond(Goods goods);

	// 按照Goods类里面的值模糊查询 调用mapper包goods.xml里的getGoodsByLike配置 返回List<Goods>类型的数据
	public List<Goods> getGoodsByLike(Goods goods);

	// 按主键查询商品表返回单一的Goods实例 调用mapper包goods.xml里的getGoodsById配置
	public Goods getGoodsById(String goodsid);

}


