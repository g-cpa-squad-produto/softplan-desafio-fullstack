package br.com.softplan.processmanagement;

import br.com.softplan.processmanagement.domain.UserSystem;
import br.com.softplan.processmanagement.repositories.UsersSystemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DataJpaTest
//@AutoConfigureWebTestClient
public class UsersControllerTest {

//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Autowired
//    private TestEntityManager manager;
//
//    @Autowired
//    private UsersSystemRepository usersSystemRepository;
//
//    @Test
//    public void listUsersTest() throws Exception {
//        List<UserSystem> all = usersSystemRepository.findAll();
//        this.webTestClient.get().uri("/api/users").exchange().expectStatus().isOk()
//                .expectBody(List.class).isEqualTo(all);
//    }

}
