package com.miratanlehmkuhl.backend.dto;

import java.util.List;

import com.miratanlehmkuhl.backend.model.User;

public class ListUser {

	private List<User> users;
	private Long total;

	public ListUser() {
	}

	public ListUser(List<User> users, Long total) {
		this.users = users;
		this.total = total;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
