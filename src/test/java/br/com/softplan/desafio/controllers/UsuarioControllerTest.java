package br.com.softplan.desafio.controllers;

import br.com.softplan.desafio.models.Perfil;
import br.com.softplan.desafio.models.Usuario;
import br.com.softplan.desafio.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UsuarioControllerTest extends IntegrationTest {

    private static final String URL = "/usuario";

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private MockMvc mockMvc;

    private Usuario usuarioADM;
    private Usuario usuarioTRI;
    private Usuario usuarioFIN;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();

        usuarioADM = new Usuario("Kathy Sierra", Perfil.ADM);
        usuarioTRI = new Usuario("Bert Bates", Perfil.TRI);
        usuarioFIN = new Usuario("Robert C. Martin", Perfil.FIN);
    }

    @Test
    public void getListUsuarios() throws Exception {

        usuarioRepository.save(new ArrayList<>(Arrays.asList(usuarioADM, usuarioFIN, usuarioTRI)));
        List<Usuario> allUsuarios = usuarioRepository.findAll();

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(allUsuarios.size())));
    }

    @Test
    public void postUsuario() throws Exception {

        String json = objectMapper.writeValueAsString(usuarioADM);

        mockMvc.perform(post(URL)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo", notNullValue()))
                .andExpect(jsonPath("$.nome", is(usuarioADM.getNome())))
                .andExpect(jsonPath("$.perfil", is(usuarioADM.getPerfil().name())));
    }

    @Test
    public void putUsuario() throws Exception {

        usuarioRepository.save(usuarioADM);

        usuarioADM.setNome("Paulo Silveira");
        usuarioADM.setPerfil(Perfil.FIN);

        String json = objectMapper.writeValueAsString(usuarioADM);

        mockMvc.perform(put(URL)
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo", notNullValue()))
                .andExpect(jsonPath("$.nome", is(usuarioADM.getNome())))
                .andExpect(jsonPath("$.perfil", is(usuarioADM.getPerfil().name())));
    }

    @Test
    public void deleteUsuario() throws Exception {

        usuarioRepository.save(usuarioADM);

        String json = objectMapper.writeValueAsString(usuarioADM);

        mockMvc.perform(delete(URL.concat("/" + usuarioADM.getCodigo()))
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Usuario deleted = usuarioRepository.findOne(usuarioADM.getCodigo());

        assertNotNull(deleted.getDataExclusao());

    }
}
