package com.desafiosoftplan.backend.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafiosoftplan.backend.enums.Status;
import com.desafiosoftplan.backend.repository.SituacaoRepository;
@Service
public class StatusService {

	public Status getAprovado() {
		return Status.APROVADO;
	}

	public Status getReprovado() {
		return Status.REPROVADO;
	}
	
	public Status getAguardandoAvaliacao() {
		return Status.PENDENTE_AGUARDANDO_AVALIACAO;
	}
	
	public Status getAguardandoRevisao() {
		return Status.PENDENTE_AGUARDANDO_REVISAO;
	}
	
	public Status getAguardandoReadequacao() {
		return Status.PENDENTE_READEQUACAO;
	}	
	
	public List<Status> list() {
		List<Status>retorno = new ArrayList<>();
		for(Status s:Status.values()) {
			retorno.add(s);
		}
		return retorno;
	}
	
}
