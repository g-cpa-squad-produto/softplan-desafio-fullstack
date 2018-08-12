package br.com.fwom.api.repositories;

import br.com.fwom.api.models.LawsuitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawsuitUserRepository extends JpaRepository<LawsuitUser, Long> {
    List<LawsuitUser> findAllByUserId(Long userId);
    List<LawsuitUser> findAllByLawsuitId(Long lawsuitId);
    List<LawsuitUser> findAllByLawsuitIdAndUserId(Long lawsuitId, Long userId);
}
