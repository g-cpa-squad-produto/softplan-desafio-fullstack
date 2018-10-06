package process.server.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import process.server.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	
	User findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u FROM User u Order By u.name, u.email, u.role.name ")
	Page<User> findAllWithPagination(Pageable pageable);
	
	@Query("SELECT u FROM User u WHERE u.name like concat('%', :filter, '%') OR u.email like concat('%', :filter, '%') OR u.role.name like concat('%', :filter, '%') Order By u.name, u.email, u.role.name")
	Page<User> findAllWithPaginationByFilter(@Param("filter") String filter, Pageable pageable);

}
