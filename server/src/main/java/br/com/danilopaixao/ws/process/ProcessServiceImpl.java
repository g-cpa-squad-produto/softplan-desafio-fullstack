package br.com.danilopaixao.ws.process;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danilopaixao.ws.legal.advice.LegalAdvice;
import br.com.danilopaixao.ws.legal.advice.LegalAdviceResponse;
import br.com.danilopaixao.ws.user.User;
import br.com.danilopaixao.ws.user.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class ProcessServiceImpl implements ProcessService {

	@Autowired
    private ProcessRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public ProcessResponse save(ProcessRequest processRequest) {
		log.info("save process => " + processRequest);
		Process process = Process.builder()
				.code(processRequest.getCode())
				.summary(processRequest.getSummary())
				.description(processRequest.getDescription())
				.userCreatedBy(User.builder().id(processRequest.getIdUserCreatedBy()).build())
				.legalAdvices(processRequest.getLegalAdvices()
								.stream()
								.map(
									l->LegalAdvice
											.builder()
											.description(l.getDescription())
											.userCreatedBy(User.builder().id(l.getIdCreatedBy()).build())
											.userResponsableFor(User.builder().id(l.getIdResponsableFor()).build())
											.build()
								).collect(Collectors.toList())
				).build();
		process = this.repository.save(process);
		final Process idProcess = process;
		List<LegalAdvice> legalAdvices = processRequest.getLegalAdvices()
				.stream()
				.map(
					l->LegalAdvice
							.builder()
							.process(idProcess)
							.description(l.getDescription())
							.userCreatedBy(User.builder().id(l.getIdCreatedBy()).build())
							.userResponsableFor(User.builder().id(l.getIdResponsableFor()).build())
							.build()
				).collect(Collectors.toList());
		process.setLegalAdvices(legalAdvices);
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
	
	private User getUser(Long id) {
		return userService.getUserById(id);
	}
	
	@Override
	public ProcessResponse save(Long id, ProcessRequest processRequest) {
		log.info("save process", processRequest);
		Process process = this.repository.findOne(id);
		process.setSummary(processRequest.getSummary());
		process.setCode(processRequest.getCode());
		process.setDescription(processRequest.getDescription());
		if(processRequest.getIdUserCreatedBy()!= null) {
			User u = userService.getUserById(processRequest.getIdUserCreatedBy());
			process.setUserCreatedBy(u);
		}
		if(processRequest.getIdUserFinishedBy()!= null) {
			User u = userService.getUserById(processRequest.getIdUserFinishedBy());
			process.setUserFinishedBy(u);
		}
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
				.legalAdvices(process.getLegalAdvices()
						.stream()
						.map(
							l->LegalAdviceResponse
									.builder()
									.id(l.getId())
									.description(l.getDescription())
									.idCreatedBy(l.getUserCreatedBy().getId())
									.loginCreatedBy(l.getUserCreatedBy().getLogin())
									.idFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getId():null)
									.loginFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getLogin():null)
									.idResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getId():null)
									.loginResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getLogin():null)
									.nameResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getName():null)
									.build()
						).collect(Collectors.toList())
				).build();
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
								.legalAdvices(p.getLegalAdvices()
										.stream()
										.map(
											l->LegalAdviceResponse
													.builder()
													.id(l.getId())
													.description(l.getDescription())
													.idCreatedBy(l.getUserCreatedBy().getId())
													.loginCreatedBy(l.getUserCreatedBy().getLogin())
													.idFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getId():null)
													.loginFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getLogin():null)
													.idResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getId():null)
													.loginResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getLogin():null)
													.nameResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getName():null)
													.build()
										).collect(Collectors.toList())
								).build()
				).collect(Collectors.toList());		
	}

	@Override
	public Process getProcessById(Long id) {
		return repository.getOne(id);
	}
	
}
