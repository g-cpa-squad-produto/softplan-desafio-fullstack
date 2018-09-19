package process.server.apis_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import process.server.apis.UserService;
import process.server.dao.UserDao;
import process.server.domain.OAuth;
import process.server.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User OAuth(OAuth oAuth) {
		return userDao.findByEmailAndPassword(oAuth.getEmail(), oAuth.getPassword());
	}

}
