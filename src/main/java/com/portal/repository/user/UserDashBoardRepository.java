package com.portal.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.portal.models.user.UserDashBoard;

public interface UserDashBoardRepository extends MongoRepository<UserDashBoard, String> {

}
