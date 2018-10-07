package process.server.apis;

import java.util.List;
import java.util.Map;

import process.server.domain.OAuth;
import process.server.domain.User;

public interface UserService {

	Map<String, Object> OAuth(OAuth oauth);
	
	Map<String, Object> save(User user);
		
	List<User> findAll(String roleCode);
	
	Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter);
	
	User findOne(Long id);

	Map<String, Object> delete(Long id);

}
