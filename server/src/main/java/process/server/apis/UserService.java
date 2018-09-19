package process.server.apis;

import process.server.domain.OAuth;
import process.server.domain.User;

public interface UserService {

	User OAuth(OAuth oauth);

}
