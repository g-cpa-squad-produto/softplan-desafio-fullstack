package process.server.apis_impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import process.server.apis.UserService;
import process.server.dao.UserDao;
import process.server.domain.OAuth;
import process.server.domain.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, Object> OAuth(OAuth oAuth) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		String message = "";
		Integer statusCode = null;
		
		User user = userDao.findByEmailAndPassword(oAuth.getEmail(), oAuth.getPassword());
		if (user != null) {
			
			message = "Login efetuado com sucesso";
			statusCode = HttpServletResponse.SC_OK;
			
		} else {
			
			message = "E-mail e/ou senha estão incorretos";
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
		}
		
		data.put("message", message);
		data.put("status", statusCode);
		data.put("user", user);
		
		return data;
	}

	@Transactional
	@Modifying
	@Override
	public Map<String, Object> save(User user) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		String message = "";
		Integer statusCode = null;
		User savedUser = null;
		
		User userSameEmail = userDao.findByEmail(user.getEmail());
		if ((user.getId() == null && userSameEmail != null) 
				|| (user.getId() != null && userSameEmail != null && !user.getId().equals(userSameEmail.getId()))) {
			
			message = "Já existe um usuário com esse e-mail. Por favor, utilize outro e-mail.";
			statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			
		} else {
			
			message = "Usuário salvo com sucesso.";
			statusCode = HttpServletResponse.SC_OK;
			savedUser = userDao.save(user);
			
		}
		
		data.put("message", message);
		data.put("status", statusCode);
		data.put("user", savedUser);
		
		return data;
	}

	@Override
	public List<User> findAll(String roleCode) {
		return userDao.findByRoleCode(roleCode);
	}

	@Override
	public Map<String, Object> findAllWithPagination(String sort, Long page, Long perPage, String filter) {
		
		Long to = (page * perPage);
		Long lastPage = to - perPage;
		Long from = lastPage == 0 ? 0 : lastPage + 1; 
		
		Map<String, Object> usersWithPagination = new HashMap<String, Object>();
		usersWithPagination.put("current_page", page);
		usersWithPagination.put("from", from);
		usersWithPagination.put("last_page", lastPage);
		usersWithPagination.put("per_page", perPage);
		
		Pageable pageable = new PageRequest(from.intValue(), to.intValue());
		Page<User> data = null;
		
		if (StringUtils.isBlank(filter)) {
			data = userDao.findAllWithPagination(pageable);
		} else {
			data = userDao.findAllWithPaginationByFilter(filter, pageable);
		}

		if (data != null) {
			
			usersWithPagination.put("data", data.getContent());
			usersWithPagination.put("total", data.getTotalElements());
			usersWithPagination.put("to", data.getNumberOfElements());
			
		}
		
		return usersWithPagination;
	}

	@Override
	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public Map<String, Object> delete(Long id) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			userDao.delete(id);
			result.put("message", "Usuário deletado com sucesso");
			result.put("status", HttpServletResponse.SC_OK);
			
		} catch (DataIntegrityViolationException e) {
			
			String message = "Usuário possui processos vinculados. Não foi possível deletar o usuário.";
			result.put("message", message);
			result.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}

}
