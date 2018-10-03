package process.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import process.server.apis.RoleService;
import process.server.domain.Role;

@RestController
@RequestMapping(value="api/roles")
public class RoleServiceRest {
	
	@Autowired
	private RoleService roleService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "", method = RequestMethod.GET)
    private List<Role> findAll() {
		return roleService.findAll();
    }
	
}
