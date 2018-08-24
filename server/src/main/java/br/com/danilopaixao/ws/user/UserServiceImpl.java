package br.com.danilopaixao.ws.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class UserServiceImpl implements UserService {

	@Value("${br.com.danilopaixao.ws.variavel}")
	private String processDefinitionKey;

	@Value("${br.com.danilopaixao.ws.variavel}")
	private String subProcesses;
	
	@Override
	public String save(String user) {
		log.info("save user");
		// TODO Auto-generated method stub
		return user;
		
	}

	@Override
	public String getById(String id) {
		// TODO Auto-generated method stub
		return id;
	}
	
}
