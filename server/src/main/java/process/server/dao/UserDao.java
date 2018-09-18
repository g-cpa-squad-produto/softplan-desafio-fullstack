package process.server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import process.server.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	
	User findByEmailAndPassword(String email, String password);
	
}
