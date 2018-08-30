package br.com.danilopaixao.ws.legal.advice;

import java.util.List;

public interface LegalAdviceService {

	LegalAdviceResponse save(LegalAdviceRequest process);
	LegalAdviceResponse save(Long id, LegalAdviceRequest process);
	LegalAdviceResponse getById(Long id);
	List<LegalAdviceResponse> getByAllLegalAdvices();

}
