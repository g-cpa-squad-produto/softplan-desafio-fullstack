package br.com.luizgustavo.processevaluation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.luizgustavo.processevaluation.model.User;
import br.com.luizgustavo.processevaluation.model.dto.UserDto;
import br.com.luizgustavo.processevaluation.model.enums.RoleUser;
import br.com.luizgustavo.processevaluation.model.form.UserForm;
import br.com.luizgustavo.processevaluation.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRep;
	@Autowired
	private PasswordEncoder encoder;
	
	public UserDto insert(UserForm form) {
		User user = form.toEntity(encoder);
		user = this.userRep.save(user);
		return new UserDto(user);
	}
	
	public UserDto update(Long idUser, UserForm form) {
		User user = this.userRep.findById(idUser).orElseThrow(() -> new EmptyResultDataAccessException(1));
		user.setRole(RoleUser.toEnum(form.getRole()));
		if (form.getPassword() != null) {
			user.setPassword(encoder.encode(form.getPassword()));
		}		
		BeanUtils.copyProperties(form, user, "idUser", "password", "role");
		user = this.userRep.save(user);
		return new UserDto(user);
	}
	
	public Page<UserDto> findAll(Pageable pageable) {
		return this.userRep.findAll(pageable).map(u -> new UserDto(u));		
	}
	
	public UserDto findById(Long idUser) {
		User user = this.userRep.findById(idUser).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return new UserDto(user);
	}
	
	public void delete(Long idUser) {
		this.userRep.deleteById(idUser);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRep.findByLogin(username);
	}
	
	public List<UserDto> findClosers() {
		return this.userRep.findByRole(RoleUser.ROLE_CLOSER).stream().map(u -> new UserDto(u)).collect(Collectors.toList());
	}
}
