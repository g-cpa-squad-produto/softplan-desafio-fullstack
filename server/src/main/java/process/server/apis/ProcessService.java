package process.server.apis;

import java.util.List;
import java.util.Map;

import process.server.domain.Process;
import process.server.domain.dto.ProcessDTO;

public interface ProcessService {

	ProcessDTO save(ProcessDTO processDTO);
	
	Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter);
	
	ProcessDTO findOne(Long id);

	List<Process> findAll();
	
}
