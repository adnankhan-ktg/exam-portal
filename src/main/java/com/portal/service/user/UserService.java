package com.portal.service.user;

import com.portal.models.user.User;

public interface UserService {
	public User addUser(User user);
	public void deleteUser(User user);
	public User updateUser(User user);
	

}
