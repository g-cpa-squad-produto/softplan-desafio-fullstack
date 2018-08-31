package br.com.danilopaixao.ws.legal.advice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.danilopaixao.ws.process.Process;
import br.com.danilopaixao.ws.process.ProcessService;
import br.com.danilopaixao.ws.user.User;
import br.com.danilopaixao.ws.user.UserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
class LegalAdviceServiceImpl implements LegalAdviceService {

	@Autowired
    private LegalAdviceRepository repository;
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public LegalAdviceResponse save(LegalAdviceRequest legalAdviceRequest) {
		log.info("save legalAdvice => " + legalAdviceRequest);
		final Process process = processService.getProcessById(Long.valueOf(legalAdviceRequest.getProcessId()));
		LegalAdvice legalAdvice = LegalAdvice.builder()
				.process(process)
				.description(legalAdviceRequest.getDescription())
				.userCreatedBy(User.builder().id(legalAdviceRequest.getIdCreatedBy()).build())
				.userResponsableFor(User.builder().id(legalAdviceRequest.getIdResponsableFor()).build())
				.build();
		legalAdvice = this.repository.save(legalAdvice);	
		return LegalAdviceResponse
				.builder()
				.id(legalAdvice.getId())
				.processId(legalAdvice.getProcess().getId())
				.description(legalAdvice.getDescription())
				.idCreatedBy(legalAdvice.getUserCreatedBy().getId())
				.idResponsableFor(legalAdvice.getUserResponsableFor().getId())
				.idFinishedBy(legalAdvice.getUserFinishedBy()!=null?legalAdvice.getUserFinishedBy().getId():null)
				.build();
	}
	
	private User getUser(Long id) {
		return userService.getUserById(id);
	}
	
	@Override
	public LegalAdviceResponse save(Long id, LegalAdviceRequest legalAdviceRequest) {
		log.info("save legalAdvice", legalAdviceRequest);
		final Process process = processService.getProcessById(Long.valueOf(legalAdviceRequest.getProcessId()));
		
		LegalAdvice legalAdvice = this.repository.findOne(id);
		legalAdvice.setProcess(process);
		legalAdvice.setDescription(legalAdviceRequest.getDescription());
		
		if(legalAdviceRequest.getIdResponsableFor()!= null) {
			User u = userService.getUserById(legalAdviceRequest.getIdResponsableFor());
			legalAdvice.setUserResponsableFor(u);
		}
		if(legalAdviceRequest.getIdCreatedBy()!= null) {
			User u = userService.getUserById(legalAdviceRequest.getIdCreatedBy());
			legalAdvice.setUserCreatedBy(u);
		}
		if(legalAdviceRequest.getIdFinishedBy()!= null) {
			User u = userService.getUserById(legalAdviceRequest.getIdFinishedBy());
			legalAdvice.setUserFinishedBy(u);
		}
		this.repository.save(legalAdvice);
		return LegalAdviceResponse
					.builder()
					.id(legalAdvice.getId())
					.processId(legalAdvice.getProcess().getId())
					.description(legalAdvice.getDescription())
					.idCreatedBy(legalAdvice.getUserCreatedBy().getId())
					.idFinishedBy(legalAdvice.getUserFinishedBy()!=null?legalAdvice.getUserFinishedBy().getId():null)
					.build();
		
	}
	
	@Override
	public LegalAdviceResponse getById(Long id) {
		LegalAdvice legalAdvice = this.repository.findOne(id);
		return LegalAdviceResponse.builder()
				.id(legalAdvice.getId())
				.processId(legalAdvice.getProcess().getId())
				.processCode(legalAdvice.getProcess().getCode())
				.description(legalAdvice.getDescription())
				.idCreatedBy(legalAdvice.getUserCreatedBy().getId())
				.loginCreatedBy(legalAdvice.getUserCreatedBy().getLogin())
				.idFinishedBy(legalAdvice.getUserFinishedBy()!=null?legalAdvice.getUserFinishedBy().getId():null)
				.loginFinishedBy(legalAdvice.getUserFinishedBy()!=null?legalAdvice.getUserFinishedBy().getLogin():null)
				.idResponsableFor(legalAdvice.getUserResponsableFor()!=null?legalAdvice.getUserResponsableFor().getId():null)
				.loginResponsableFor(legalAdvice.getUserResponsableFor()!=null?legalAdvice.getUserResponsableFor().getLogin():null)
				.nameResponsableFor(legalAdvice.getUserResponsableFor()!=null?legalAdvice.getUserResponsableFor().getName():null)
				.build();
	}
	
	@Override
	public List<LegalAdviceResponse> getByAllLegalAdvices() {
		return this.repository
				.findAll()
				.stream()
				.map(l -> LegalAdviceResponse.builder()
								.id(l.getId())
								.processId(l.getProcess().getId())
								.processCode(l.getProcess().getCode())
								.description(l.getDescription())
								.idCreatedBy(l.getUserCreatedBy().getId())
								.loginCreatedBy(l.getUserCreatedBy().getLogin())
								.idFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getId():null)
								.loginFinishedBy(l.getUserFinishedBy()!=null?l.getUserFinishedBy().getLogin():null)
								.idResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getId():null)
								.loginResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getLogin():null)
								.nameResponsableFor(l.getUserResponsableFor()!=null?l.getUserResponsableFor().getName():null)
								.build()
				).collect(Collectors.toList());		
	}
	
}
