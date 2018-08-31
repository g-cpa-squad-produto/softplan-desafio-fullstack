package com.luanrubensf.challenge.representation;

import com.luanrubensf.challenge.model.User;
import org.springframework.stereotype.Component;

public class UserDto implements IEntityDto<Long> {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String matchPassword;
    private String role;

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchPassword() {
        return matchPassword;
    }

    public String getRole() {
        return role;
    }

    @Component
    public static class Representation extends AbstractRepresentation<User, UserDto, User.Builder> {

        public Representation() {
            super(User.Builder::create, User.Builder::from);
        }

        @Override
        public UserDto toRepresentation(User entity) {
            return Builder.create()
                    .id(entity.getId())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .role(entity.getRole().getName())
                    .build();
        }

        @Override
        public User fromRepresentation(UserDto dto, User.Builder builder) {
            return builder
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .matchPassword(dto.getMatchPassword())
                    .name(dto.getName())
                    .build();
        }
    }

    public static class Builder {
        private UserDto entity;

        private Builder(UserDto entity) {
            this.entity = entity;
        }

        public static Builder create() {
            return new Builder(new UserDto());
        }

        public static Builder from(UserDto entity) {
            return new Builder(entity);
        }

        public Builder id(Long id) {
            entity.id = id;
            return this;
        }

        public Builder name(String name) {
            entity.name = name;
            return this;
        }

        public Builder email(String email) {
            entity.email = email;
            return this;
        }

        public Builder password(String password) {
            entity.password = password;
            return this;
        }

        public Builder matchPassword(String matchPassword) {
            entity.matchPassword = matchPassword;
            return this;
        }

        public Builder role(String role) {
            entity.role = role;
            return this;
        }

        public UserDto build() {
            return entity;
        }
    }
}
