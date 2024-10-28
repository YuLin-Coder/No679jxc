package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.BuyDAO;
import com.entity.Buy;
import com.service.BuyService;

@Service("buyService") //
public class BuyServiceImpl implements BuyService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private BuyDAO buyDAO;
	@Override // 继承接口的新增商品采购表数据 返回值0(失败),1(成功)
	public int insertBuy(Buy buy) {
		return this.buyDAO.insertBuy(buy);
	}

	@Override // 继承接口的更新商品采购表数据 返回值0(失败),1(成功)
	public int updateBuy(Buy buy) {
		return this.buyDAO.updateBuy(buy);
	}

	@Override // 继承接口的按主键删除商品采购表数据 返回值0(失败),1(成功)
	public int deleteBuy(String buyid) {
		return this.buyDAO.deleteBuy(buyid);
	}

	@Override // 继承接口的批量删除商品采购表数据 返回值0(失败),大于0(成功)
	public int deleteBuyByIds(String[] ids) {
		return this.buyDAO.deleteBuyByIds(ids);
	}

	@Override // 继承接口的查询商品采购表全部数据
	public List<Buy> getAllBuy() {
		return this.buyDAO.getAllBuy();
	}

	@Override // 继承接口的按条件精确查询商品采购表数据
	public List<Buy> getBuyByCond(Buy buy) {
		return this.buyDAO.getBuyByCond(buy);
	}

	@Override // 继承接口的按条件模糊查询商品采购表数据
	public List<Buy> getBuyByLike(Buy buy) {
		return this.buyDAO.getBuyByLike(buy);
	}

	@Override // 继承接口的按主键查询商品采购表数据 返回Entity实例
	public Buy getBuyById(String buyid) {
		return this.buyDAO.getBuyById(buyid);
	}

}

