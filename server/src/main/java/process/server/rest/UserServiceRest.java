package process.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import process.server.apis.UserService;
import process.server.domain.OAuth;

@RestController
@RequestMapping(value="api/users")
public class UserServiceRest {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/oauth", method = RequestMethod.GET)
    private Boolean oauth(@RequestBody  OAuth oAuth) {
		return userService.OAuth(oAuth);
    }
	
}
