package process.server.apis_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import process.server.apis.UserService;
import process.server.dao.UserDao;
import process.server.domain.OAuth;
import process.server.domain.User;

@Service
public class UserImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Boolean OAuth(OAuth oAuth) {
		
		Boolean isAuth = Boolean.TRUE;
		
		User user = userDao.findByEmailAndPassword(oAuth.getEmail(), oAuth.getPassword()); 
		if (user == null) {
			isAuth = Boolean.FALSE;
		}
		
		return isAuth;
		
	}

}
