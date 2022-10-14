package project.management.userManagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.management.userManagement.dao.UserRepository;
import project.management.userManagement.entity.User;
import project.management.userManagement.service.UserService;

/**
 * class to implement service method.
 */
@Service
public class UserServiceImpl implements UserService{


	private UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}
	
	/**
     * Method createUser :create a user.
     *
     * @param user contains user attribute
     * @return user
     */
	@Override
	public void createUser(User user) {
	   userRepository.save(user);
	}

	/**
     * Method detailsUsers :list all user.
     *
     * @return list of user
     */
	@Override
	public List<User> detailsUsers() {
		List<User> listUser = userRepository.findAll();
		return listUser;
	}
	
	/**
     * Method detailsUserById :return user who have id passed on param
     *
     * @param id : the id of user in database
     * @return user 
     */
	@Override
	public User detailsUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		return user.isPresent() ? user.get() : null;
	}

}
