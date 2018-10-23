package com.softplan.thiagobernardo.parecer.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.softplan.thiagobernardo.login.controller.LoginApiController;
import com.softplan.thiagobernardo.parecer.entity.Parecer;
import com.softplan.thiagobernardo.parecer.service.ParecerService;
import com.softplan.thiagobernardo.usuario.entity.UsuarioDTO;
import com.softplan.thiagobernardo.usuario.service.UsuarioService;
import com.softplan.thiagobernardo.util.TipoUsuario;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ParecerApiControllerTest {
	
	private MockMvc mvc;
	
	@Mock
	private ParecerService parecerService;
	
	@Mock
	private UsuarioService usuarioService;
	
	@InjectMocks
	private ParecerApiController parecerApiController;
	
	
	public static final String TOKEN = "Bearer_gas7ya7s7ags67auasdha7asdas5asfd5afda"; 
	
	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(parecerApiController).build();
	}
	
	@Test
	public void salvarParecerProcessoFinalizadorTest() throws Exception {
		
		when(usuarioService.trazerPorToken((String) any())).thenReturn(getUsuario(TipoUsuario.FINALIZADOR));
		when(parecerService.salvarParecerProcesso( (Parecer) any() )).thenReturn(new Parecer());
		
		mvc.perform(MockMvcRequestBuilders.post("/pareceres/processo")
				.header(LoginApiController.NOME_TOKEN_HEADER, TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"descricao\":\"Teste\",\"titulo\":\"Teste\",\"usuario\":{\"id\":1},\"processo\":{\"id\":1}}"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
		.andDo(MockMvcResultHandlers.print());
		
		verify(usuarioService, atLeastOnce()).trazerPorToken((String) any());
		verify(parecerService, atLeastOnce()).salvarParecerProcesso((Parecer) any());
	}
	
	@Test
	public void salvarParecerProcessoTriadorTest() throws Exception {
		
		when(usuarioService.trazerPorToken((String) any())).thenReturn(getUsuario(TipoUsuario.TRIADOR));
		when(parecerService.salvarParecerProcesso( (Parecer) any() )).thenReturn(new Parecer());
		
		mvc.perform(MockMvcRequestBuilders.post("/pareceres/processo")
				.header(LoginApiController.NOME_TOKEN_HEADER, TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"descricao\":\"Teste\",\"titulo\":\"Teste\",\"usuario\":{\"id\":1},\"processo\":{\"id\":1}}"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError())
		.andDo(MockMvcResultHandlers.print());
		
		verify(usuarioService, atLeastOnce()).trazerPorToken((String) any());
		verify(parecerService, never()).salvarParecerProcesso((Parecer) any());
	}
	
	@Test
	public void salvarParecerProcessoAdminTest() throws Exception {
		
		when(usuarioService.trazerPorToken((String) any())).thenReturn(getUsuario(TipoUsuario.ADMIN));
		when(parecerService.salvarParecerProcesso( (Parecer) any() )).thenReturn(new Parecer());
		
		mvc.perform(MockMvcRequestBuilders.post("/pareceres/processo")
				.header(LoginApiController.NOME_TOKEN_HEADER, TOKEN)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"descricao\":\"Teste\",\"titulo\":\"Teste\",\"usuario\":{\"id\":1},\"processo\":{\"id\":1}}"))
		.andExpect(MockMvcResultMatchers.status().is4xxClientError())
		.andDo(MockMvcResultHandlers.print());
		
		verify(usuarioService, atLeastOnce()).trazerPorToken((String) any());
		verify(parecerService, never()).salvarParecerProcesso((Parecer) any());
	}
	
	private UsuarioDTO getUsuario(TipoUsuario tipoUsuario) {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId( Long.parseLong("10"));
		usuario.setNome("nome");
		usuario.setLogin("login");
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setToken(TOKEN);
		return usuario;
	}

}
