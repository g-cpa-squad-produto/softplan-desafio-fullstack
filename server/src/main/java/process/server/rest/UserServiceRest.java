package process.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import process.server.apis.UserService;
import process.server.domain.OAuth;
import process.server.domain.User;

@RestController
@RequestMapping(value="api/users")
public class UserServiceRest {

	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/oauth", method = RequestMethod.POST)
    private User oauth(@RequestBody  OAuth oAuth) {
		return userService.OAuth(oAuth);
    }
	
}
