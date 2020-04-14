package com.softplan.processmanagerapi.factory;

import com.softplan.processmanagerapi.models.User;
import com.softplan.processmanagerapi.payload.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public User getEntity(UserResponse userResponse) {
        return new User();
    }

    public UserResponse getUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName());
    }

}
