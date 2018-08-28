package br.com.danilopaixao.ws.process;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danilopaixao.ws.user.User;
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
				.code(processRequest.getCode())
				.summary(processRequest.getSummary())
				.description(processRequest.getDescription())
				.userCreatedBy(User.builder().id(processRequest.getIdUserCreatedBy()).build())
				.build();
		this.repository.save(process);
		return ProcessResponse
					.builder()
					.id(process.getId())
					.code(process.getCode())
					.summary(process.getSummary())
					.description(process.getDescription())
					.idCreatedBy(process.getUserCreatedBy().getId())
					.build();
		
	}
	
	@Override
	public ProcessResponse save(Long id, ProcessRequest processRequest) {
		log.info("save process", processRequest);
		Process process = this.repository.findOne(id);
		process.setSummary(processRequest.getSummary());
		process.setCode(processRequest.getCode());
		process.setDescription(processRequest.getDescription());
		process.setUserCreatedBy(User.builder().id(processRequest.getIdUserCreatedBy()).build());
		process.setUserFinishedBy(User.builder().id(processRequest.getIdUserFinishedBy()).build());
		this.repository.save(process);
		return ProcessResponse
					.builder()
					.id(process.getId())
					.code(process.getCode())
					.summary(process.getSummary())
					.description(process.getDescription())
					.idCreatedBy(process.getUserCreatedBy().getId())
					.idFinishedBy(process.getUserFinishedBy()!=null?process.getUserFinishedBy().getId():null)
					.build();
		
	}
	
	@Override
	public ProcessResponse getById(Long id) {
		Process process = this.repository.findOne(id);
		return ProcessResponse.builder()
				.id(process.getId())
				.code(process.getCode())
				.summary(process.getSummary())
				.description(process.getDescription())
				.idCreatedBy(process.getUserCreatedBy().getId())
				.loginCreatedBy(process.getUserCreatedBy().getLogin())
				.idFinishedBy(process.getUserFinishedBy()!=null?process.getUserFinishedBy().getId():null)
				.loginFinishedBy(process.getUserFinishedBy()!=null?process.getUserFinishedBy().getLogin():null)
				.build();
	}
	
	@Override
	public List<ProcessResponse> getByAllProcesss() {
		return this.repository
				.findAll()
				.stream()
				.map(p -> ProcessResponse.builder()
								.id(p.getId())
								.code(p.getCode())
								.summary(p.getSummary())
								.description(p.getDescription())
								.idCreatedBy(p.getUserCreatedBy().getId())
								.loginCreatedBy(p.getUserCreatedBy().getLogin())
								.idFinishedBy(p.getUserFinishedBy()!=null?p.getUserFinishedBy().getId():null)
								.loginFinishedBy(p.getUserFinishedBy()!=null?p.getUserFinishedBy().getLogin():null)
								.build()
				).collect(Collectors.toList());		
	}
	
}
