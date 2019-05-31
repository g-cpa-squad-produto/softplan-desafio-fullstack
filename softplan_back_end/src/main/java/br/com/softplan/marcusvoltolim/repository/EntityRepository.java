package br.com.softplan.marcusvoltolim.repository;

import br.com.softplan.marcusvoltolim.model.support.EntityLongId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T extends EntityLongId> extends JpaRepository<T, Long> {

}
