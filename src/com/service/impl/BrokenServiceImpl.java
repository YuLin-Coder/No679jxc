package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.BrokenDAO;
import com.entity.Broken;
import com.service.BrokenService;

@Service("brokenService") //
public class BrokenServiceImpl implements BrokenService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private BrokenDAO brokenDAO;
	@Override // 继承接口的新增商品损失表数据 返回值0(失败),1(成功)
	public int insertBroken(Broken broken) {
		return this.brokenDAO.insertBroken(broken);
	}

	@Override // 继承接口的更新商品损失表数据 返回值0(失败),1(成功)
	public int updateBroken(Broken broken) {
		return this.brokenDAO.updateBroken(broken);
	}

	@Override // 继承接口的按主键删除商品损失表数据 返回值0(失败),1(成功)
	public int deleteBroken(String brokenid) {
		return this.brokenDAO.deleteBroken(brokenid);
	}

	@Override // 继承接口的批量删除商品损失表数据 返回值0(失败),大于0(成功)
	public int deleteBrokenByIds(String[] ids) {
		return this.brokenDAO.deleteBrokenByIds(ids);
	}

	@Override // 继承接口的查询商品损失表全部数据
	public List<Broken> getAllBroken() {
		return this.brokenDAO.getAllBroken();
	}

	@Override // 继承接口的按条件精确查询商品损失表数据
	public List<Broken> getBrokenByCond(Broken broken) {
		return this.brokenDAO.getBrokenByCond(broken);
	}

	@Override // 继承接口的按条件模糊查询商品损失表数据
	public List<Broken> getBrokenByLike(Broken broken) {
		return this.brokenDAO.getBrokenByLike(broken);
	}

	@Override // 继承接口的按主键查询商品损失表数据 返回Entity实例
	public Broken getBrokenById(String brokenid) {
		return this.brokenDAO.getBrokenById(brokenid);
	}

}

