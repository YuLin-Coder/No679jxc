package com.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.entity.Broken;
@Service("brokenService") // 自动注册到Spring容器，不需要再在xml文件定义bean
public interface BrokenService {
	// 插入商品损失表数据 调用brokenDAO里的insertBroken配置
	public int insertBroken(Broken broken);

	// 更新商品损失表数据 调用brokenDAO里的updateBroken配置
	public int updateBroken(Broken broken);

	// 按主键删除商品损失表数据 调用brokenDAO里的deleteBroken配置
	public int deleteBroken(String brokenid);

	// 批量删除商品损失表数据 调用mapper包broken.xml里的deleteBrokenByIds配置 返回值0(失败),大于0(成功)
	public int deleteBrokenByIds(String[] ids);

	// 查询全部数据 调用brokenDAO里的getAllBroken配置
	public List<Broken> getAllBroken();

	// 按照Broken类里面的字段名称精确查询 调用brokenDAO里的getBrokenByCond配置
	public List<Broken> getBrokenByCond(Broken broken);

	// 按照Broken类里面的字段名称模糊查询 调用brokenDAO里的getBrokenByLike配置
	public List<Broken> getBrokenByLike(Broken broken);

	// 按主键查询表返回单一的Broken实例 调用brokenDAO里的getBrokenById配置
	public Broken getBrokenById(String brokenid);

}

