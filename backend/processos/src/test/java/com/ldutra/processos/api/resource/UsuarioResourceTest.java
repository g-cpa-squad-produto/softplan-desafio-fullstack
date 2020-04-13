package com.ldutra.processos.api.resource;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ldutra.processos.api.dto.UsuarioDTO;
import com.ldutra.processos.exception.ErroAutenticacao;
import com.ldutra.processos.exception.RegraNegocioException;
import com.ldutra.processos.model.entity.Usuario;
import com.ldutra.processos.service.ProcessoService;
import com.ldutra.processos.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest( controllers = UsuarioResource.class )
@AutoConfigureMockMvc
public class UsuarioResourceTest {
	
	static final String API = "/api/usuarios";
	static final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	UsuarioService service;
	
	@MockBean
	ProcessoService lancamentoService;
	
	@Test
	public void deveAutenticarUmUsuario() throws Exception {
		//cenario
		String email = "usuario@email.com";
		String senha = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).senha(senha).build();
		Usuario usuario = Usuario.builder().id(1l).email(email).senha(senha).build();
		Mockito.when( service.autenticar(email, senha) ).thenReturn(usuario);
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post( API.concat("/autenticar") )
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		
		
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isOk()  )
			.andExpect( MockMvcResultMatchers.jsonPath("id").value(usuario.getId())  )
			.andExpect( MockMvcResultMatchers.jsonPath("nome").value(usuario.getNome())  )
			.andExpect( MockMvcResultMatchers.jsonPath("email").value(usuario.getEmail())  )
			
		;
		
	}
	
	@Test
	public void deveRetornarBadRequestAoObterErroDeAutenticacao() throws Exception {
		//cenario
		String email = "usuario@email.com";
		String senha = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email(email).senha(senha).build();
		Mockito.when( service.autenticar(email, senha) ).thenThrow(ErroAutenticacao.class);
		
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post( API.concat("/autenticar") )
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		
		
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isBadRequest()  );
			
		;
		
	}
	
	@Test
	public void deveCriarUmNovoUsuario() throws Exception {
		//cenario
		String email = "usuario@email.com";
		String senha = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email("usuario@email.com").senha("123").build();
		Usuario usuario = Usuario.builder().id(1l).email(email).senha(senha).build();
		
		Mockito.when( service.salvarUsuario(Mockito.any(Usuario.class)) ).thenReturn(usuario);
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post( API  )
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		
		
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isCreated()  )
			.andExpect( MockMvcResultMatchers.jsonPath("id").value(usuario.getId())  )
			.andExpect( MockMvcResultMatchers.jsonPath("nome").value(usuario.getNome())  )
			.andExpect( MockMvcResultMatchers.jsonPath("email").value(usuario.getEmail())  )
			
		;
		
	}
	
	@Test
	public void deveRetornarBadRequestAoTentarCriarUmUsuarioInvalido() throws Exception {
		//cenario
		String email = "usuario@email.com";
		String senha = "123";
		
		UsuarioDTO dto = UsuarioDTO.builder().email("usuario@email.com").senha("123").build();
		
		Mockito.when( service.salvarUsuario(Mockito.any(Usuario.class)) ).thenThrow(RegraNegocioException.class);
		String json = new ObjectMapper().writeValueAsString(dto);
		
		//execucao e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.post( API  )
													.accept( JSON )
													.contentType( JSON )
													.content(json);
		
		
		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isBadRequest()  );
			
		;
		
	}
	

	


}
