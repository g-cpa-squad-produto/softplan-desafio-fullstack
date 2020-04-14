package unpg.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import unpg.Model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Integer> {
        void deleteById(Integer id);
}
