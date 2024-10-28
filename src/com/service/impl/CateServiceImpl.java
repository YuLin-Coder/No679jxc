package com.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.CateDAO;
import com.entity.Cate;
import com.service.CateService;

@Service("cateService") //
public class CateServiceImpl implements CateService {
	@Autowired // 它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作
	private CateDAO cateDAO;
	@Override // 继承接口的新增商品类型表数据 返回值0(失败),1(成功)
	public int insertCate(Cate cate) {
		return this.cateDAO.insertCate(cate);
	}

	@Override // 继承接口的更新商品类型表数据 返回值0(失败),1(成功)
	public int updateCate(Cate cate) {
		return this.cateDAO.updateCate(cate);
	}

	@Override // 继承接口的按主键删除商品类型表数据 返回值0(失败),1(成功)
	public int deleteCate(String cateid) {
		return this.cateDAO.deleteCate(cateid);
	}

	@Override // 继承接口的批量删除商品类型表数据 返回值0(失败),大于0(成功)
	public int deleteCateByIds(String[] ids) {
		return this.cateDAO.deleteCateByIds(ids);
	}

	@Override // 继承接口的查询商品类型表全部数据
	public List<Cate> getAllCate() {
		return this.cateDAO.getAllCate();
	}

	@Override // 前台显示分类
	public List<Cate> getCateFront() {
		return this.cateDAO.getCateFront();
	}
	@Override // 继承接口的按条件精确查询商品类型表数据
	public List<Cate> getCateByCond(Cate cate) {
		return this.cateDAO.getCateByCond(cate);
	}

	@Override // 继承接口的按条件模糊查询商品类型表数据
	public List<Cate> getCateByLike(Cate cate) {
		return this.cateDAO.getCateByLike(cate);
	}

	@Override // 继承接口的按主键查询商品类型表数据 返回Entity实例
	public Cate getCateById(String cateid) {
		return this.cateDAO.getCateById(cateid);
	}

}

