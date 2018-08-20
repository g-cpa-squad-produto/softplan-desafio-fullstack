package com.processos.luiz.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.processos.luiz.ParecerProcessoDTO;
import com.processos.luiz.exception.ProcessoException;
import com.processos.luiz.models.ParecerProcesso;
import com.processos.luiz.models.Processo;
import com.processos.luiz.models.Usuario;
import com.processos.luiz.repository.ParecerProcessoRepository;
import com.processos.luiz.repository.ProcessoRepository;
import com.processos.luiz.repository.UsuarioRepository;

@Service
@Transactional
public class ProcessoService {
	private final Logger LOG = LoggerFactory.getLogger(ProcessoService.class);
	@Autowired
	private ProcessoRepository processoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ParecerProcessoRepository parecerProcessoRepository;
	
	public Processo salvarProcesso(Processo processo) throws ProcessoException{
		processo.setDataCadastro(new Date());
		processoRepository.save(processo);
		return processo;
	}
	public Processo cadastrarParecer (ParecerProcessoDTO parecerProcessoDTO)throws ProcessoException{
		Processo  processo = processoRepository.getOne(parecerProcessoDTO.getCodigoProcesso());
		Usuario usuario = usuarioRepository.findByLogin(parecerProcessoDTO.getLogin());
		ParecerProcesso parecerProcesso = new ParecerProcesso();
		parecerProcesso.setDataCadastro(new Date());
		parecerProcesso.setParecer(parecerProcessoDTO.getParecer());
		parecerProcesso.setUsuario(usuario.getCodigo());
		parecerProcesso.setProcesso(processo.getCodigo());
		parecerProcessoRepository.save(parecerProcesso);
		return null;
	}
	public List<Processo> listaProcessosUsuarios(String login) throws ProcessoException{
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario==null){
			LOG.info("Usuário não encontrado com login");
			throw new ProcessoException();
		}
		return processoRepository.listarProcessosFinalizador(usuario.getCodigo());
	}
}
