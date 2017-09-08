package com.zjutywc.inter;

import java.util.List;

import com.zjutywc.model.User;

public interface IUserOperation {
	public User selectUserByID(int id);
	public List selectUsers(String userName);
	public void addUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
	public List getUserArticles(int id);
}
