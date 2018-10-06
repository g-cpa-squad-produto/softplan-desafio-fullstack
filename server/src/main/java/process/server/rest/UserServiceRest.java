package process.server.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import process.server.apis.UserService;
import process.server.domain.OAuth;
import process.server.domain.User;

@RestController
@RequestMapping(value="api/users")
public class UserServiceRest {

	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/oauth", method = RequestMethod.POST)
    private User oauth(@RequestBody  OAuth oAuth) {
		return userService.OAuth(oAuth);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "", method = RequestMethod.POST)
    private User save(@RequestBody  User user) {
		return userService.save(user);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "", method = RequestMethod.GET)
    private List<User> findAll() {
		return userService.findAll();
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
    private Map<String, Object> findAllWithPagination(@RequestParam(value = "sort", required = false) String sort, 
    		@RequestParam("page") Long page, 
    		@RequestParam("per_page") Long perPage,
    		@RequestParam(value = "filter", required = false) String filter) {
		return userService.findAllWithPagination(sort, page, perPage, filter);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private User findOne(@PathVariable("id") Long id) {
		return userService.findOne(id);
    }
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private Map<String, Object> delete(@PathVariable("id") Long id) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			
			userService.delete(id);
			result.put("message", "Delete with sucess!");
			result.put("status", HttpServletResponse.SC_OK);
			
		} catch (Exception e) {
			
			String message = "Error to delete user with id " + id.toString() + ". " + e;
			System.out.println(message);
			
			result.put("message", message);
			result.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return result;
    }
	
}
