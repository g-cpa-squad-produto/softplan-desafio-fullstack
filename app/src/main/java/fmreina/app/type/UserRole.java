package fmreina.app.type;

import java.io.Serializable;

import lombok.Getter;

@Getter
public enum UserRole implements Serializable {

	ADM(1L, "Administrador"), 
	TRIADOR(2L, "Triador"), 
	FINALIZADOR(3L, "Finalizador");

	private Long id;
	private String role;

	private UserRole(Long id, String name) {
		this.id = id;
		this.role = name;
	}
}
