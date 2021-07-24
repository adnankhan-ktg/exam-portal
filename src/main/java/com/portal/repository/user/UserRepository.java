package com.portal.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.portal.models.user.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>
{
	
	public User findByUsername(String str);
	public User findByUsernameAndPassword(String username, String password);

}
