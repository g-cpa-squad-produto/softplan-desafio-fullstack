package br.com.luizgustavo.processevaluation.model.dto;

import br.com.luizgustavo.processevaluation.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long idUser;
	private String name;
	private String login;
	private String role;
	
	public UserDto(User user) {
		this.idUser = user.getIdUser();
		this.name = user.getName();
		this.login = user.getLogin();
		this.role = user.getRole().getRole();
	}
}
