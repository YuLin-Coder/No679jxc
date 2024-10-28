package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.SellDAO;
import com.entity.Sell;
import com.service.SellService;

@Service("sellService") //
public class SellServiceImpl implements SellService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private SellDAO sellDAO;
	@Override // 继承接口的新增商品销售表数据 返回值0(失败),1(成功)
	public int insertSell(Sell sell) {
		return this.sellDAO.insertSell(sell);
	}

	@Override // 继承接口的更新商品销售表数据 返回值0(失败),1(成功)
	public int updateSell(Sell sell) {
		return this.sellDAO.updateSell(sell);
	}

	@Override // 继承接口的按主键删除商品销售表数据 返回值0(失败),1(成功)
	public int deleteSell(String sellid) {
		return this.sellDAO.deleteSell(sellid);
	}

	@Override // 继承接口的批量删除商品销售表数据 返回值0(失败),大于0(成功)
	public int deleteSellByIds(String[] ids) {
		return this.sellDAO.deleteSellByIds(ids);
	}

	@Override // 继承接口的查询商品销售表全部数据
	public List<Sell> getAllSell() {
		return this.sellDAO.getAllSell();
	}

	@Override // 继承接口的按条件精确查询商品销售表数据
	public List<Sell> getSellByCond(Sell sell) {
		return this.sellDAO.getSellByCond(sell);
	}

	@Override // 继承接口的按条件模糊查询商品销售表数据
	public List<Sell> getSellByLike(Sell sell) {
		return this.sellDAO.getSellByLike(sell);
	}

	@Override // 继承接口的按主键查询商品销售表数据 返回Entity实例
	public Sell getSellById(String sellid) {
		return this.sellDAO.getSellById(sellid);
	}

}

