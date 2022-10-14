package project.management.userManagement.service;

import java.util.List;

import project.management.userManagement.entity.User;

/**
 * interface to define method for service.
 */
public interface UserService {
	
	public User createUser(User user);
	public List<User> detailsUsers();
	public User detailsUserById(int id);
}
