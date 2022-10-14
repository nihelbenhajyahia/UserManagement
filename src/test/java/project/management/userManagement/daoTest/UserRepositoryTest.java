package project.management.userManagement.daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import project.management.userManagement.dao.UserRepository;
import project.management.userManagement.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;



@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private EntityManager entityManager;
	
	@Test
	public void whenFindAll() throws Exception
	{

		Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");       
        User userentity = new User("nihel",birth,"french", "125478","female");
        entityManager.persist(userentity);
        entityManager.flush();
        List<User> userListFinded = userRepository.findAll();       
        assertThat(userListFinded.size()).isEqualTo(1);
        assertThat(userListFinded.get(0)).isEqualTo(userentity);
	}
	
	@Test
	public void whenFindById() throws Exception
	{
		
		Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");       
        User userentity = new User("nihel",birth,"french", "125478","female");
        userentity =userRepository.save(userentity);
        Optional<User> user = userRepository.findById(userentity.getId());       
        assertThat(user.get().getName()).isEqualTo(userentity.getName());
	}
	
	@Test
	public void whenSave() throws Exception
	{
		Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");       
		User userentity = new User("nihel",birth,"french", "125478","female");
		User userFinded = userRepository.save(userentity);        
        assertThat(userFinded).isEqualTo(userentity);
	}
	


}
