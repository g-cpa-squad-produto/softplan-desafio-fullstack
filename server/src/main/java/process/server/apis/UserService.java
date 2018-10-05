package process.server.apis;

import java.util.List;
import java.util.Map;

import process.server.domain.OAuth;
import process.server.domain.User;

public interface UserService {

	User OAuth(OAuth oauth);
	
	User save(User user);
		
	List<User> findAll();
	
	Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter);
	
	User findOne(Long id);
}
