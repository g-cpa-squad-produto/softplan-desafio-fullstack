package br.com.softplan.process.converter;

import br.com.softplan.process.Converter;
import br.com.softplan.process.model.User;
import br.com.softplan.process.request.UserInsertRequest;
import br.com.softplan.process.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserInsertRequestConverter implements Converter<UserInsertRequest, User> {

    private ProfileService profileService;

    @Autowired
    public UserInsertRequestConverter(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public User encode(UserInsertRequest request) {
        User user = new User();

        user.setId(request.getId());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setProfiles(new HashSet<>(profileService.findByIdIn(request.getProfiles())));

        return user;
    }

    @Override
    public UserInsertRequest decode(User user) {
        return null;
    }
}
