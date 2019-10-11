package br.com.softplan.processmanagement;

import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.domain.UserType;
import br.com.softplan.processmanagement.repositories.UserSystemProcessRepository;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserSystemRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private UsersSystemRepository userSystemRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Test
    public void whenFindByEmail_thenReturnUserSystem(){
        UserSystem userSystem = new UserSystem("Administrador","admin@email.com",bcryptEncoder.encode("123456"), UserType.ADMIN);
        userSystemRepository.saveAndFlush(userSystem);
        Optional<UserSystem> byEmail = userSystemRepository.findByEmail(userSystem.getEmail());
        assertThat(byEmail.get().getEmail()).isEqualTo(userSystem.getEmail());
    }

}
