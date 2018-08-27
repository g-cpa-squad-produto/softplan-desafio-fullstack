package br.com.danilopaixao.ws.process;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class ProcessServiceImpl implements ProcessService {

	@Autowired
    private ProcessRepository repository;
	
	@Override
	public ProcessResponse save(ProcessRequest processRequest) {
		log.info("save process => " + processRequest);
		Process process = Process.builder()
				.summary(processRequest.getSummary())
				.description(processRequest.getDescription())
				.build();
		this.repository.save(process);
		return ProcessResponse
					.builder()
					.summary(process.getSummary())
					.description(process.getDescription())
					.build();
		
	}
	
	@Override
	public ProcessResponse save(Long id, ProcessRequest processRequest) {
		log.info("save process", processRequest);
		Process process = this.repository.findOne(id);
		process.setSummary(processRequest.getSummary());
		process.setDescription(processRequest.getDescription());
		this.repository.save(process);
		return ProcessResponse
					.builder()
					.id(process.getId())
					.summary(process.getSummary())
					.description(process.getDescription())
					.build();
		
	}
	
	@Override
	public ProcessResponse getById(Long id) {
		Process process = this.repository.findOne(id);
		return ProcessResponse.builder()
				.id(process.getId())
				.summary(process.getSummary())
				.description(process.getDescription())
				.build();
	}
	
	@Override
	public List<ProcessResponse> getByAllProcesss() {
		return this.repository
				.findAll()
				.stream()
				.map(p -> ProcessResponse.builder()
								.id(p.getId())
								.summary(p.getSummary())
								.description(p.getDescription())
								.build()
				).collect(Collectors.toList());		
	}
	
}
