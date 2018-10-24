package br.com.softplan.models;

import lombok.Builder;
import lombok.Data;

public @Builder @Data class UserDTO {
	
 private String login;
 private ProfileTypes profile;
 private String token;

}
