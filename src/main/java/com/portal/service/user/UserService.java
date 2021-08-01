package com.portal.service.user;

import java.util.List;

import com.portal.models.user.User;
import com.portal.models.user.UserDashBoard;

public interface UserService {
	public User addUser(User user);
	public void deleteUser(User user);
	public User updateUser(User user);
	public UserDashBoard saveDashboard(UserDashBoard userDashBoard);
	public List<UserDashBoard> getDashBoards();
}
