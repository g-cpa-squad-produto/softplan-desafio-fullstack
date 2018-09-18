package process.server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import process.server.domain.UserProcess;

public interface UserProcessDao extends PagingAndSortingRepository<UserProcess, Long> {
	
}
