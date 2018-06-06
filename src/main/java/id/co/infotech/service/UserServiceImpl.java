package id.co.infotech.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.infotech.dao.UserDao;
import id.co.infotech.model.User;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public User findById(int id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	public User findBySSO(String sso) {
		User user = null;
		try{
			user = dao.findBySSO(sso);
		} catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		}
		return user;
	}

	@Transactional
	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		dao.save(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	@Transactional
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity!=null){
			entity.setSsoId(user.getSsoId());
			if(!user.getPassword().equals(entity.getPassword())){
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	@Transactional
	public void deleteUserBySSO(String sso) {
		try{
			dao.deleteBySSO(sso);
		} catch (NoResultException nre){
			//Ignore this because as per your logic this is ok!
		}
	}

	@Transactional(readOnly = true)
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	@Transactional(readOnly = true)
	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySSO(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
}
