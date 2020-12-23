package teste.softplan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teste.softplan.domain.UserAdmin;


public interface UserAdminRepo extends JpaRepository<UserAdmin, Long> {

}
