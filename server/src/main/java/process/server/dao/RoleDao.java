package process.server.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import process.server.domain.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {
	
}
