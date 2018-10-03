package process.server.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import process.server.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	
	User findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u FROM User u")
	Page<User> findAllWithPagination(Pageable pageable);
	
}
