package br.com.danilopaixao.ws.process;

import java.util.List;

public interface ProcessService {

	ProcessResponse save(ProcessRequest user);
	ProcessResponse save(Long id, ProcessRequest user);
	ProcessResponse getById(Long id);
	List<ProcessResponse> getByAllProcesss();

}
