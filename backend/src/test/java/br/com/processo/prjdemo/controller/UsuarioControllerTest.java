package br.com.processo.prjdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.processo.prjdemo.service.UsuarioService;

/**
 * 
 * @author Guilherme Sena
 * Classe para exemplificar alguns testes
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {
	    
	    @Autowired
	    private UsuarioController usuarioController;
	    
	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @LocalServerPort
	    private int port;

	    @MockBean
	    private UsuarioService usuarioService;

	    @Autowired
	    private MockMvc mockMvc;
	    
	    /**
	     * Testa o contexto
	     * @throws Exception
	     */
	    @Test
	    public void contexLoads() throws Exception {
	        assertThat(usuarioController).isNotNull();
	    }
	    
	    /**
	     * 
	     * @throws Exception
	     */
	    @Test
	    public void testagetTodosUsuario() throws Exception {
	        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/usuario/todos-usuario",
	                String.class)).isNotEmpty();
	    }

	    /**
	     * testa metodo get usuario pelo id
	     * @throws Exception
	     */
	    @Test
	    public void shouldReturnDefaultMessage() throws Exception {
	        this.mockMvc.perform(get("/usuario/todos-usuarios-permissao/ADMINISTRADOR")).andDo(print()).andExpect(status().isOk());
	    }
}