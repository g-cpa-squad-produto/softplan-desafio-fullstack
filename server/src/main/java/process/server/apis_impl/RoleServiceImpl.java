package process.server.apis_impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import process.server.apis.RoleService;
import process.server.dao.RoleDao;
import process.server.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		return (List<Role>) roleDao.findAll();
	}
	
}
