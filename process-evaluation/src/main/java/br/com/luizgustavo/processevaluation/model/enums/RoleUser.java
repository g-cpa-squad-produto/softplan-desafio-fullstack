package br.com.luizgustavo.processevaluation.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum RoleUser {

	ROLE_ADMIN("ROLE_ADMIN"), ROLE_SCREENING("ROLE_SCREENING"), ROLE_CLOSER("ROLE_CLOSER");
	
	@Getter @Setter
	private String role;
	
	public static RoleUser toEnum(String role) {
		if ("".equals(role)) {
			return null;
		}

		for (RoleUser roleUser : RoleUser.values()) {
			if (role.equals(roleUser.getRole())) {
				return roleUser;
			}
		}

		throw new IllegalArgumentException("Role inválida.");
	}
}
