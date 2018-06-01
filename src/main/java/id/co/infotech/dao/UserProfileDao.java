package id.co.infotech.dao;

import java.util.List;

import id.co.infotech.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
