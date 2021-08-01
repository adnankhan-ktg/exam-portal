package com.portal.service.user.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.portal.models.user.User;
import com.portal.models.user.UserDashBoard;
import com.portal.repository.user.UserDashBoardRepository;
import com.portal.repository.user.UserRepository;
import com.portal.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

@Autowired
private UserRepository userRepository;
@Autowired
private PasswordEncoder bc;
@Autowired
private UserDashBoardRepository userDashBoardRepository;

	@Override
	public User addUser(User user) {
		user.setOtp(null);

		
		User u = null;
		try {
			user.setPassword(this.bc.encode(user.getPassword()));
			u = this.userRepository.save(user);
			return u;
		}
		catch (Exception e) {
			e.printStackTrace();
			return u;
		}
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User updateUser(User user) {
		User user1 = null;
		try
		{
		  user1 = this.userRepository.save(user);
		  return user1;
		}catch (Exception e) {
		e.printStackTrace();
		return user1;
		}
	}

	@Override
	public UserDashBoard saveDashboard(UserDashBoard userDashBoard) {
		 userDashBoard.setId(UUID.randomUUID().toString());
		UserDashBoard userDashBoard1 = null;
		try {
		             userDashBoard1=  this.userDashBoardRepository.save(userDashBoard);
		             return userDashBoard1;
		}catch (Exception e) {
			e.printStackTrace();
			return userDashBoard1;
		
		}
	}

	@Override
	public List<UserDashBoard> getDashBoards() {
		List<UserDashBoard> list = null;
		try {
		         list = this.userDashBoardRepository.findAll();
		         return list;
		}catch (Exception e) {
		e.printStackTrace();
		return list;
		}
	}
	
	

}
