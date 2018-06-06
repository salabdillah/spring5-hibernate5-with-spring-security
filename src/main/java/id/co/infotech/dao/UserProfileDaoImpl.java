package id.co.infotech.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import id.co.infotech.model.UserProfile;



@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile> implements UserProfileDao{

	@Override
	public List<UserProfile> findAll() {
		return (List<UserProfile>) findAllOrderByAsc("type");
	}

	@Override
	public UserProfile findByType(String type) {
		return (UserProfile) find("type", type);
	}

	@Override
	public UserProfile findById(int id) {
		return getByKey(id);
	}
	
}
