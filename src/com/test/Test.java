package com.test;

import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.AdminDAO;
import com.entity.Admin;
import com.util.VeDate;

public class Test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext resource = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
		AdminDAO adminDAO = (AdminDAO) resource.getBean(AdminDAO.class);
		for (int i = 0; i < 45; i++) {
			Admin admin = new Admin();
			admin.setAdminid(UUID.randomUUID().toString().replace("-", ""));
			admin.setUsername("admin" + i);
			admin.setPassword("admin" + i);
			admin.setRealname("admin" + i);
			admin.setContact("-" + i + i * i);
			admin.setAddtime(VeDate.getStringDateShort());
			adminDAO.insertAdmin(admin);
		}
	}
}








































	// 本类作者QQ : 709664889  提供程序有偿修改服务 本类制作日期 ：2021-08-31
	// 代做毕业设计程序 JSP || Servlet || Struts2 || SSH || SSM || SpringBoot || ThinkPHP || ASP.NET(三层架构) 
	// 数据库 MySQL ||  MSSQL || Oracle
	// 程序开发 数据库建设 环境调试 
	// 单对单 单对多 另有海量成品   欢迎惠顾
