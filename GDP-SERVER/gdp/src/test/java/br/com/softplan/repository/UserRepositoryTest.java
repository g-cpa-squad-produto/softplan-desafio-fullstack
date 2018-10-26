package br.com.softplan.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import br.com.softplan.ApplicationTest;
import br.com.softplan.models.User;


@DataJpaTest
public class UserRepositoryTest extends ApplicationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IUserRepository userRepository;
	
	private User user;
	
	@Before
	public void setup() {
		// given
		this.user =  new User();
		this.user.setName("Test User");
		this.user.setLogin("test");
		this.user.setPassword("teste");
	}

	@Test
	public void whenFindByLogin_thenReturnUser() {
						
		entityManager.persist(this.user);
		entityManager.flush();

		// when
		User found = userRepository.findByLogin(user.getLogin());

		// then
		assertThat(found.getName()).isEqualTo(user.getName());
	}
	
	@Test
	public void whenFindId_thenDeleteUser() {
				
		entityManager.persist(this.user);
		entityManager.flush();

		// when
		User found = userRepository.findByLogin(this.user.getLogin());

		// then
		assertThat(found.getName()).isEqualTo(user.getName());
		
		entityManager.remove(found);
		entityManager.flush();

	}
}
