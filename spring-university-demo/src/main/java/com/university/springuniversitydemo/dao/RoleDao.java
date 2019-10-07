package com.university.springuniversitydemo.dao;

import com.university.springuniversitydemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
