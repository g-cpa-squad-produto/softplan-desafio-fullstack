package com.pmanagement.pmanagementbackend.domain.dto;

import java.io.Serializable;

import com.pmanagement.pmanagementbackend.domain.entity.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The DTO for {@link User}
 *
 * @author Cristian Souza
 *
 * @version 1.0.0
 * @since 1.0.0, Mar 2, 2020
 */
@ToString
@EqualsAndHashCode
public class UserDTO implements AbstractDTO<User> {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String token;

	@Override
	public User toDomain() {
        final User user = new User();

        user.setName(this.name);
        user.setUsername(this.username);
        user.setEmail(this.email);

		return user;
	}

	@Override
	public void toDto(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
	}
}