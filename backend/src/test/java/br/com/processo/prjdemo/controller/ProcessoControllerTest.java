package br.com.processo.prjdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

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

import br.com.processo.prjdemo.model.Processo;
import br.com.processo.prjdemo.repository.ProcessoRepository;
import br.com.processo.prjdemo.service.UsuarioService;

/**
 * 
 * @author Guilherme Sena
 * Classe para exemplificar alguns testes
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProcessoControllerTest {
	    
	    @Autowired
	    private ProcessoController processoController;
	    
	    @Autowired
	    private ProcessoRepository processoRepository;
	    
	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @LocalServerPort
	    private int port;

	    @MockBean
	    private UsuarioService usuarioService;
	    
	    /**
	     * Testa o contexto
	     * @throws Exception
	     */
	    @Test
	    public void contexLoads() throws Exception {
	        assertThat(processoController).isNotNull();
	    }
	    
	    /**
	     * testa retorno contendo processo espera lista vazio uma vez que no momento inicial nao existe processo 
	     * @throws Exception
	     */
	    @Test
	    public void testagetTodosUsuario() throws Exception {
	        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/processo/todos-processos",
	                String.class)).isEmpty();
	    }
	    
	    /**
	     * testa processo inserido é encontrado na busca
	     */
	    @Test
	    public void testaProcessoPeloId() {
	        Processo processo = new Processo();
	        processo.setDescricao("Processo de compra");
	        processo.setDataCriacao(new Date());
	        processoRepository.save(processo);
	
	        //when
	        Processo testProcesso = processoRepository.findById(processo.getId()).get();
	        //then
	        assertThat(testProcesso.getDescricao()).isEqualTo(processo.getDescricao());
	    }
}