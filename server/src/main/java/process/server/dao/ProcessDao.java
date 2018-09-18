package process.server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import process.server.domain.Process;

public interface ProcessDao extends PagingAndSortingRepository<Process, Long> {
	
}
