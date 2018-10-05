package process.server.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import process.server.domain.User;
import process.server.domain.UserProcess;

public interface UserProcessDao extends PagingAndSortingRepository<UserProcess, Long> {
	
	@Query("SELECT up.user FROM UserProcess up WHERE up.process.id = :processId ")
	List<User> findUsersByProcessId(@Param("processId") Long processId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM UserProcess up WHERE up.process.id = :processId ")
	void deleteByProcessId(@Param("processId") Long processId);
	
}
