package process.server.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import process.server.domain.Process;

public interface ProcessDao extends PagingAndSortingRepository<Process, Long> {
	
	@Query("SELECT DISTINCT p "
			+ " FROM Process p, UserProcess up "
			+ " WHERE up.process.id = p.id "
			+ "		AND (:userId IS NULL OR up.user.id = :userId) "
			+ " Order By p.name, p.code, p.seem")
	Page<Process> findAllWithPaginationByUserId(Pageable pageable, @Param("userId") Long userId);
	
	@Query("SELECT DISTINCT p "
			+ " FROM Process p, UserProcess up "
			+ " WHERE (p.name like concat('%', :filter, '%') OR p.code like concat('%', :filter, '%') OR p.seem like concat('%', :filter, '%')) "
			+ "		AND up.process.id = p.id "
			+ "		AND (:userId IS NULL OR up.user.id = :userId) "
			+ " Order By p.name, p.code, p.seem")
	Page<Process> findAllWithPaginationByFilterAndUserId(Pageable pageable, @Param("filter") String filter, @Param("userId") Long userId);

	Process findByCode(String code);
	
}
