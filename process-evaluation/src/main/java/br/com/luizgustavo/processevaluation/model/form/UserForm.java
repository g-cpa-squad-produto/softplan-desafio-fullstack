package br.com.luizgustavo.processevaluation.model.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sun.istack.NotNull;

import br.com.luizgustavo.processevaluation.model.User;
import br.com.luizgustavo.processevaluation.model.enums.RoleUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {

	@NotNull @NotBlank @Length(max = 50)
	private String name;
	@NotNull @NotBlank @Length(max = 20)
	private String login;
	@NotNull @NotBlank
	private String password;
	@NotNull @NotBlank
	private String role;
	
	public User toEntity(PasswordEncoder encoder) {
		User user = new User();
		user.setName(this.name);
		user.setLogin(this.login);
		user.setPassword(encoder.encode(this.password));
		user.setRole(RoleUser.toEnum(this.role));
		return user;
	}
}
