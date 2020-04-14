package unpg.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import unpg.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
        void deleteByUserId(Integer userId);

        User findByEmail(String email);

        User findByUserId(Integer userId);
}
