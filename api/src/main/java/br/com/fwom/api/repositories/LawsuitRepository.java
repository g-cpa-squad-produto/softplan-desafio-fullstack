package br.com.fwom.api.repositories;

import br.com.fwom.api.models.Lawsuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawsuitRepository extends JpaRepository<Lawsuit, Long> {
    Optional<Lawsuit> findByNumber(String number);
    @Query(nativeQuery = true)
    List<Lawsuit> findOpenedByUserEmail(@Param("email") String email);
}
