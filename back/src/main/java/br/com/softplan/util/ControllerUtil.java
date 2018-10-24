package br.com.softplan.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.softplan.security.JwtUser;
import br.com.softplan.security.enums.PerfilEnum;
import br.com.softplan.security.utils.JwtTokenUtil;

public class ControllerUtil {

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	public Boolean isAdministrador() {
		String role = jwtTokenUtil.getRole(request);

		return role.equals(PerfilEnum.ROLE_ADMINISTRADOR.toString());
	}

	public Boolean isTriador() {
		String role = jwtTokenUtil.getRole(request);

		return role.equals(PerfilEnum.ROLE_USUARIO_TRIADOR.toString());
	}

	public Boolean isFinalizador() {
		String role = jwtTokenUtil.getRole(request);

		return role.equals(PerfilEnum.ROLE_USUARIO_FINALIZADOR.toString());
	}

}
