package br.com.softplan.desafio.controllers;

import br.com.softplan.desafio.models.Perfil;
import br.com.softplan.desafio.models.Processo;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.repository.ProcessoRepository;
import br.com.softplan.desafio.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProcessoControllerTest extends IntegrationTest {

    private static final String URL = "/processo";

    @Autowired
    private ProcessoController processoController;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MockMvc mockMvc;

    private Usuario triador;
    private Usuario finalizador;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(processoController).build();

        triador = new Usuario("Frodo Bolseiro", Perfil.TRI);
        finalizador = new Usuario("Bilbo Bolseiro", Perfil.FIN);

        usuarioRepository.save(new ArrayList<>(Arrays.asList(triador, finalizador)));


    }

    @Test
    public void getProcessos() throws Exception {

        Processo processo = new Processo();
        processo.setNumero("882133-PR");

        Processo processo2 = new Processo();
        processo2.setNumero("654885-SP");

        processoRepository.save(new ArrayList<>(Arrays.asList(processo, processo2)));

        List<Processo> allPendentes = processoRepository.findAllPendentes();

        mockMvc.perform(get(URL))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(allPendentes.size())));

    }

    @Test
    public void postProcesso() throws Exception {

        Processo processo = new Processo();
        processo.setNumero("565483-SC");
        String json = objectMapper.writeValueAsString(processo);

        mockMvc.perform(post(URL.concat("/" + triador.getCodigo()))
               .content(json)
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.codigo", notNullValue()))
               .andExpect(jsonPath("$.numero", is(processo.getNumero())))
               .andExpect(jsonPath("$.dataCadastro", notNullValue()));

    }

    @Test
    public void putProcesso() throws Exception {

        Processo processo = new Processo();
        processo.setNumero("9875412-SC");
        Processo saved = processoRepository.save(processo);

        saved.setParecer("Processo avaliado e seguirá em julgamento.");

        String json = objectMapper.writeValueAsString(saved);

        mockMvc.perform(put(URL.concat("/" + finalizador.getCodigo()))
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo", notNullValue()))
                .andExpect(jsonPath("$.numero", is(processo.getNumero())))
                .andExpect(jsonPath("$.dataFinalizacao", notNullValue()))
                .andExpect(jsonPath("$.usuarioFinalizacao.nome", is(finalizador.getNome())));

    }

}
