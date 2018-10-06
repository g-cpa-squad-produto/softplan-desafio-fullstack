package process.server.apis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Override
	public User save(User user) {
		return userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter) {
		
		Long to = (page * perPage);
		Long lastPage = to - perPage;
		Long from = lastPage == 0 ? 0 : lastPage + 1; 
		
		Map<String, Object> usersWithPagination = new HashMap<String, Object>();
		usersWithPagination.put("current_page", page);
		usersWithPagination.put("from", from);
		usersWithPagination.put("last_page", lastPage);
		usersWithPagination.put("per_page", perPage);
		
		Pageable pageable = new PageRequest(from.intValue(), to.intValue());
		Page<User> data = null;
		
		if (StringUtils.isBlank(filter)) {
			data = userDao.findAllWithPagination(pageable);
		} else {
			data = userDao.findAllWithPaginationByFilter(filter, pageable);
		}

		if (data != null) {
			
			usersWithPagination.put("data", data.getContent());
			usersWithPagination.put("total", data.getTotalElements());
			usersWithPagination.put("to", data.getNumberOfElements());
			
		}
		
		return usersWithPagination;
	}

	@Override
	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

}
