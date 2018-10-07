package process.server.apis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import process.server.apis.ProcessService;
import process.server.dao.ProcessDao;
import process.server.dao.UserProcessDao;
import process.server.domain.Process;
import process.server.domain.User;
import process.server.domain.UserProcess;
import process.server.domain.dto.ProcessDTO;

@Service
public class ProcessServiceImpl implements ProcessService {

	@Autowired
	private ProcessDao processDao;
	
	@Autowired
	private UserProcessDao userProcessDao;
	
	@Override
	public Map<String, Object> save(ProcessDTO processDTO) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		String message = null;
		Integer statusCode = null;
		
		Process existedProcess = processDao.findByCode(processDTO.getProcess().getCode());
		if ((processDTO.getProcess().getId() == null && existedProcess != null) 
				|| (processDTO.getProcess().getId() != null && existedProcess != null && !processDTO.getProcess().getId().equals(existedProcess.getId())) ) {
			
			message = "Já existe um processo com o mesmo código. Não é possível salvar o processo";
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
		} else {
			
			Process processSaved = processDao.save(processDTO.getProcess());
			
			//-- Delete all user process before save the newest
			deleteUserProcess(processSaved.getId());
			
			List<User> users = processDTO.getUsers();
			users.forEach(user -> {
				userProcessDao.save(new UserProcess(processSaved, user));
			});
			
			processDTO.setProcess(processSaved);
			
			data.put("processDTO", processDTO);
			message = "Processo salvo com sucesso";
			statusCode = HttpServletResponse.SC_OK;
			
		}
		
		data.put("message", message);
		data.put("status", statusCode);
		
		return data;
	}
	
	@Transactional
	@Modifying
	private void deleteUserProcess(Long processId) {
		userProcessDao.deleteByProcessId(processId);
	}

	@Override
	public Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter, Long userId) {
		
		Long to = (page * perPage);
		Long lastPage = to - perPage;
		Long from = lastPage == 0 ? 0 : lastPage + 1; 
		
		Map<String, Object> usersWithPagination = new HashMap<String, Object>();
		usersWithPagination.put("current_page", page);
		usersWithPagination.put("from", from);
		usersWithPagination.put("last_page", lastPage);
		usersWithPagination.put("per_page", perPage);
		
		Pageable pageable = new PageRequest(from.intValue(), to.intValue());
		Page<Process> data = null;
		
		if (StringUtils.isBlank(filter)) {
			data = processDao.findAllWithPaginationByUserId(pageable, userId);
		} else {
			data = processDao.findAllWithPaginationByFilterAndUserId(pageable, filter, userId);
		}

		if (data != null) {
			
			usersWithPagination.put("data", data.getContent());
			usersWithPagination.put("total", data.getTotalElements());
			usersWithPagination.put("to", data.getNumberOfElements());
			
		}
		
		return usersWithPagination;
	}

	@Override
	public ProcessDTO findOne(Long id) {
		
		ProcessDTO processDTO = new ProcessDTO();
		processDTO.setProcess(processDao.findOne(id));
		processDTO.setUsers(userProcessDao.findUsersByProcessId(id));
		
		return processDTO;
	}
	
	@Override
	public List<Process> findAll() {
		return (List<Process>) processDao.findAll();
	}

}
