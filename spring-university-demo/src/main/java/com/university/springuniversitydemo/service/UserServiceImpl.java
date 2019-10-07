package com.university.springuniversitydemo.service;


import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.university.springuniversitydemo.dao.RoleDao;
import com.university.springuniversitydemo.dao.StudentRepository;
import com.university.springuniversitydemo.dao.TeacherRepository;
import com.university.springuniversitydemo.dao.UserDao;
import com.university.springuniversitydemo.entity.Role;
import com.university.springuniversitydemo.entity.Student;
import com.university.springuniversitydemo.entity.Teacher;
import com.university.springuniversitydemo.entity.User;
import com.university.springuniversitydemo.user.CrmUser;

@Service
@EnableTransactionManagement 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		// give user default role of "employee"
		if(crmUser.getRole().equals("1")) {
			System.out.println("Set role = student");
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_STUDENT")));
		} else {
			System.out.println("FUCK" + crmUser.getRole());
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_TEACHER")));	
			}
		
		//assign user details to student object
		if(crmUser.getRole().equals("1")) {
			Student student = new Student();
			student.setFirstName(crmUser.getFirstName());
			student.setLastName(crmUser.getLastName());
			student.setEmail(crmUser.getEmail());
			student.setUser(user);
			studentRepository.save(student);
		} else {
			Teacher teacher = new Teacher();
			teacher.setFirstName(crmUser.getFirstName());
			teacher.setLastName(crmUser.getLastName());
			teacher.setEmail(crmUser.getEmail());
			teacher.setUser(user);
			teacherRepository.save(teacher);
		}
		
		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) 
			throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
			Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(
				role.getName())).collect(Collectors.toList());
	}
}
