package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.Profile;
import br.com.softplan.process.model.User;
import br.com.softplan.process.request.UserRequest;
import br.com.softplan.process.response.UserResponse;
import br.com.softplan.process.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserRequestConverter implements Converter<UserRequest, User> {

    private ProfileService profileService;

    @Autowired
    public UserRequestConverter(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public User encode(UserRequest request) {
        User user = new User();

        user.setId(request.getId());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setProfiles(new HashSet<>(profileService.findByIdIn(request.getProfiles())));

        return user;
    }

    @Override
    public UserRequest decode(User user) {
        return null;
    }
}
