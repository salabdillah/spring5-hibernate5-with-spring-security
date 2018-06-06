package id.co.infotech.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import id.co.infotech.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User findById(int id) {
		User user = getByKey(id);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public User findBySSO(String sso) {
		logger.info("SSO : {}", sso);
		User user = (User) find("ssoId", sso);
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@Override
	public void save(User user) {
		persist(user);
	}

	@Override
	public void deleteBySSO(String sso) {
		User user = (User) find("ssoId", sso);
		delete(user);
	}

	@Override
	public List<User> findAllUsers() {
		List<User> users = (List<User>) findAllOrderByAsc("firstName");
		// Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		// criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid
		// duplicates.
		// List<User> users = (List<User>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return users;
	}

}
