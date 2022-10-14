package project.management.userManagement.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import project.management.userManagement.dao.UserRepository;
import project.management.userManagement.entity.User;
import project.management.userManagement.service.impl.UserServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	
	@Mock UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	Date birth;
	User userentity;
	
	@Before
	public void init() throws Exception {
		
		MockitoAnnotations.openMocks(this);
		birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");
	    userentity = new User("nihel",birth,"french", "125478","female");
	}
	
	@Test
	public void detailsUsersTest() throws Exception
	{
       List<User> list = new ArrayList<>(); 
       list.add(userentity);
       given(userRepository.findAll()).willReturn(list);       
       List<User> listfinded = userServiceImpl.detailsUsers();
       assertThat(listfinded.size()).isEqualTo(1);
	}
	@Test
	public void detailsUserById() {
		Optional<User> user = Optional.of(new User("nihel",birth,"french", "125478","female"));
		given(userRepository.findById(1)).willReturn(user);      
	       User userfinded = userServiceImpl.detailsUserById(1);
	       assertThat(userfinded).isNotNull();
	       assertThat(userfinded.getName()).isEqualTo(user.get().getName());
	}
	
	@Test
	public void createUserTest() throws Exception
	{     
       given(userRepository.save(userentity)).willReturn(userentity);      
       userServiceImpl.createUser(userentity);
	}

}
