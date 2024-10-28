package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.GoodsDAO;
import com.entity.Goods;
import com.service.GoodsService;

@Service("goodsService") //
public class GoodsServiceImpl implements GoodsService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private GoodsDAO goodsDAO;
	@Override // 继承接口的新增商品表数据 返回值0(失败),1(成功)
	public int insertGoods(Goods goods) {
		return this.goodsDAO.insertGoods(goods);
	}

	@Override // 继承接口的更新商品表数据 返回值0(失败),1(成功)
	public int updateGoods(Goods goods) {
		return this.goodsDAO.updateGoods(goods);
	}

	@Override // 继承接口的按主键删除商品表数据 返回值0(失败),1(成功)
	public int deleteGoods(String goodsid) {
		return this.goodsDAO.deleteGoods(goodsid);
	}

	@Override // 继承接口的批量删除商品表数据 返回值0(失败),大于0(成功)
	public int deleteGoodsByIds(String[] ids) {
		return this.goodsDAO.deleteGoodsByIds(ids);
	}

	@Override // 继承接口的查询商品表全部数据
	public List<Goods> getAllGoods() {
		return this.goodsDAO.getAllGoods();
	}

	@Override// 查询最新上架商品
	public List<Goods> getGoodsByNews() {
		return this.goodsDAO.getGoodsByNews();
	}

	@Override// 查询热门商品
	public List<Goods> getGoodsByHot() {
		return this.goodsDAO.getGoodsByHot();
	}
	@Override// 查询按分类查询N个商品首页显示
	public List<Goods> getGoodsByCate(String cateid) {
		return this.goodsDAO.getGoodsByCate(cateid);
	}
	@Override // 继承接口的按条件精确查询商品表数据
	public List<Goods> getGoodsByCond(Goods goods) {
		return this.goodsDAO.getGoodsByCond(goods);
	}

	@Override // 继承接口的按条件模糊查询商品表数据
	public List<Goods> getGoodsByLike(Goods goods) {
		return this.goodsDAO.getGoodsByLike(goods);
	}

	@Override // 继承接口的按主键查询商品表数据 返回Entity实例
	public Goods getGoodsById(String goodsid) {
		return this.goodsDAO.getGoodsById(goodsid);
	}

}

