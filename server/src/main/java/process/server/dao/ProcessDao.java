package process.server.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import process.server.domain.Process;

public interface ProcessDao extends PagingAndSortingRepository<Process, Long> {
	
	@Query("SELECT p FROM Process p Order By p.name, p.code, p.seem")
	Page<Process> findAllWithPagination(Pageable pageable);
	
	@Query("SELECT p FROM Process p WHERE p.name like concat('%', :filter, '%') OR p.code like concat('%', :filter, '%') OR p.seem like concat('%', :filter, '%') Order By p.name, p.code, p.seem")
	Page<Process> findAllWithPaginationByFilter(@Param("filter") String filter, Pageable pageable);
	
}
