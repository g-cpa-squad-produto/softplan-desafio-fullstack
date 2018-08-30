package br.com.danilopaixao.ws.process;

import java.util.List;

public interface ProcessService {

	ProcessResponse save(ProcessRequest process);
	ProcessResponse save(Long id, ProcessRequest process);
	ProcessResponse getById(Long id);
	List<ProcessResponse> getByAllProcesss();
	Process getProcessById(Long valueOf);

}
