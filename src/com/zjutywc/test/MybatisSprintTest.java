package com.zjutywc.test;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.zjutywc.inter.IUserOperation;
import com.zjutywc.model.Article;
import com.zjutywc.model.User;

public class MybatisSprintTest {
	private static ApplicationContext ctx;
	private static String Path;
	static{
		Path = System.getProperty("user.dir")+"/WebContent/WEB-INF/applicationContext.xml";
		ctx = new FileSystemXmlApplicationContext(Path);
	}
	
	public static void main(String[] args) {
		IUserOperation userOperation = (IUserOperation) ctx.getBean("userMapper");
		System.out.println("�õ��û�id=1���û���Ϣ");
		User user = userOperation.selectUserByID(1);
		System.out.println(user.getUserName()+":"+user.getUserAge()+":"+user.getUserAddress());
		System.out.println("�õ��û�IDΪ1�����������б�");
		List<Article> articles = userOperation.getUserArticles(1);
		for(Article article:articles){
			System.out.println(article.getTitle()+":"+article.getContent()+":"+article.getUser().getUserName()+":"+article.getUser().getUserAge()+":"+article.getUser().getUserAddress());
		}
	}
}	
