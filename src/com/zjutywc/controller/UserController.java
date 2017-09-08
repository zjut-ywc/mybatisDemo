package com.zjutywc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zjutywc.inter.IUserOperation;
import com.zjutywc.model.Article;

@Controller
@RequestMapping("/article")
public class UserController {
	@Autowired
	IUserOperation userMapper;
	
	@RequestMapping("/list")
	public ModelAndView listall(HttpServletRequest request,HttpServletResponse response){
		  List<Article> articles=userMapper.getUserArticles(1); 
		  System.out.println(articles.size());
		  ModelAndView mav=new ModelAndView("list");
		  mav.addObject("articles",articles);
		  return mav;
	}
}
