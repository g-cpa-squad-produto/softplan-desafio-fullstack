package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.User;
import br.com.softplan.process.request.UserUpdateRequest;
import br.com.softplan.process.service.ProfileService;
import br.com.softplan.process.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class UserUpdateRequestConverter implements Converter<UserUpdateRequest, User> {

    private ProfileService profileService;
    private UserService userService;

    @Autowired
    public UserUpdateRequestConverter(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @Override
    public User encode(UserUpdateRequest request) {
        User user = getUser(request.getId());

        user.setId(request.getId());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setProfiles(new HashSet<>(profileService.findByIdIn(request.getProfiles())));

        return user;
    }

    @Override
    public UserUpdateRequest decode(User user) {
        return null;
    }

    private User getUser(Long id) {
        return Optional.ofNullable(userService.findById(id))
                .orElse(new User());
    }
}
