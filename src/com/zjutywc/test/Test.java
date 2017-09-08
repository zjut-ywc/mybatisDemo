package com.zjutywc.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zjutywc.inter.IUserOperation;
import com.zjutywc.model.Article;
import com.zjutywc.model.User;

public class Test {
	private static SqlSessionFactory sqlSessionFactory;
//	private static Reader reader;
	static{
		try {
			File file = new File(System.getProperty("user.dir")+"/WebContent/WEB-INF/Configuration.xml");
			InputStream is = new FileInputStream(file);
//			reader = Resources.getResourceAsReader();
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSession(){
		return sqlSessionFactory;
	}
//	根据id查询单个用户方法一
//	public static void main(String[] args) {
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//			User user = (User)session.selectOne("com.zjutywc.models.UserMapper.selectUserByID",1);
//			System.out.println(user.getUserName());
//			System.out.println(user.getUserAge());
//			System.out.println(user.getUserAddress());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}
	
//	通过接口根据id查询单个用户方法二
//	public static void main(String[] args) {
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//			IUserOperation userOperation = session.getMapper(IUserOperation.class);
//			User user = userOperation.selectUserByID(1);
//			System.out.println(user.getUserName());
//			System.out.println(user.getUserAge());
//			System.out.println(user.getUserAddress());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
//	}
	
	public void getUserList(String userName){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			List<User> users = userOperation.selectUsers(userName);
			for(User user:users){
				System.out.println(user.getId()+":"+user.getUserName()+":"+user.getUserAge()+":"+user.getUserAddress());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//	根据名字查询用户列表
//	public static void main(String[] args) {
//		Test testUser = new Test();
//		testUser.getUserList("%");
//	}
	
	public void addUser(User user){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.addUser(user);
			session.commit();
			System.out.println("当前增加的用户Id为:"+user.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//	添加用户	
//	public static void main(String[] args) {
//		Test testUser = new Test();
//		User user = new User();
//		user.setUserName("飞鸟");
//		user.setUserAge("80");
//		user.setUserAddress("人民广场");
//		testUser.addUser(user);
//	}
	
	public void updateUser(){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			User user = userOperation.selectUserByID(2);
			user.setUserAddress("原来是魔都的浦东创新园区");
			userOperation.updateUser(user);
			session.commit();
			System.out.println("更新用户信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//	更新用户信息
//	public static void main(String[] args) {
//		Test testUser = new Test();
//		testUser.updateUser();
//	}
	
	public void deleteUser(int id){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			userOperation.deleteUser(id);
			session.commit();
			System.out.println("删除用户信息成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//	删除用户
//	public static void main(String[] args) {
//		Test testUser = new Test();
//		testUser.deleteUser(2);
//	}
	
	public void getUserArticles(int userid){
		SqlSession session = sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation = session.getMapper(IUserOperation.class);
			List<Article> articles = userOperation.getUserArticles(userid);
			for(Article article:articles){
				System.out.println(article.getTitle()+":"+article.getContent()+":"+article.getUser().getUserName()+":"+article.getUser().getUserAge()+":"+article.getUser().getUserAddress());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static void main(String[] args) {
		Test testUser = new Test();
		testUser.getUserArticles(1);
	}
}
