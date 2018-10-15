package br.com.softplan.process.repository;

import br.com.softplan.process.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(collectionResourceRel = "roles", path = "role")
public interface RoleRepository extends JpaRepository<Role, Long> {

}