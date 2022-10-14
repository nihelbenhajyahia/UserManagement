package project.management.userManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.management.userManagement.aop.ApiLog;
import project.management.userManagement.dto.UserDto;
import project.management.userManagement.entity.User;
import project.management.userManagement.service.impl.UserServiceImpl;


/**
 * Class to define method for Api Rest.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	/**
     * Method createUser :create a user when the api /user/create is called.
     *
     * @param userDto contains user attribute
     * @return user
     */
	@ApiLog(uri = "/user/create")
	@PostMapping(value = "/create")
	public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto) {
		User user = new User(userDto.getName(),userDto.getBirthdate(),userDto.getCountry(),userDto.getPhone(),userDto.getGender());
		user = userServiceImpl.createUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/**
     * Method getUsers :list all user when the api /user/allte is called.
     *
     * @return list user
     */
	@ApiLog(uri = "/user/all")
	@GetMapping(value = "/all")
	public ResponseEntity<List<User>> getUsers() {
		List<User> userList = userServiceImpl.detailsUsers();
		return new ResponseEntity<>(userList, HttpStatus.OK);
	}
	
	/**
     * Method getUserById :return user who have id passed on param when the api /user/allte is called.
     *
     * @param id : the id of user in database
     * @return user 
     */
	@ApiLog(uri = "/user/{id}")
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userServiceImpl.detailsUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
