package com.miratanlehmkuhl.backend.enums;

import com.miratanlehmkuhl.backend.model.User;
import com.miratanlehmkuhl.backend.model.UserAuthority;

public enum Role {

	ADMIN, SCREENING, FINISHER;
	
	public UserAuthority asAuthorityFor(User user) {
		UserAuthority authority = new UserAuthority();
		authority.setAuthority("ROLE_" + toString());
		authority.setUser(user);
		return authority;
	}

	public static Role valueOf(UserAuthority authority) {
		switch (authority.getAuthority()) {
			case "ROLE_FINISHER":
				return FINISHER;
			case "ROLE_SCREENING":
				return SCREENING;
			case "ROLE_ADMIN":
				return ADMIN;
		}
		throw new IllegalArgumentException("No role defined for authority: " + authority.getAuthority());
	}


}
