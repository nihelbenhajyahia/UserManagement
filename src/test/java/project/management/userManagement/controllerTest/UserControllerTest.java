package project.management.userManagement.controllerTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.management.userManagement.controller.UserController;
import project.management.userManagement.dto.UserDto;
import project.management.userManagement.entity.User;
import project.management.userManagement.service.impl.UserServiceImpl;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired MockMvc mvc;
    @MockBean UserServiceImpl userServiceImpl;

    @Test
    public void createUserTest() throws Exception {
    	
       Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13"); 
       UserDto user = new UserDto("nihel",birth,"french", "125478","female");
        mvc.perform(post("/user/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(user)))
            .andExpect(status().isOk());
        
        UserDto userInvalidCountry = new UserDto("nihel",birth,"tunisia", "125478","female");
        mvc.perform(post("/user/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(userInvalidCountry)))
            .andExpect(status().isBadRequest());
        
        Date invalidbirth = new SimpleDateFormat("yyyy-MM-dd").parse("2021-09-13"); 
        UserDto userInvalidBirthdate = new UserDto("nihel",invalidbirth,"french", "125478","female");
         mvc.perform(post("/user/create")
             .contentType(MediaType.APPLICATION_JSON)
             .content(toJson(userInvalidBirthdate)))
             .andExpect(status().isBadRequest());
        
        
    }
    
    @Test
    public void getUsersTest() throws Exception {
    	
    	Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");  
        User userentity = new User("nihel",birth,"french", "125478","female");
        List<User> userList = new ArrayList<User>();
        userList.add(userentity);
        given(userServiceImpl.detailsUsers()).willReturn(userList);    
        mvc.perform(get("/user/all")
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$", hasSize(1)))
             .andExpect(jsonPath("$[0].name", is(userentity.getName())));
    	
    }
    
    @Test
    public void getUserByIdTest() throws Exception {
    	
    	Date birth = new SimpleDateFormat("yyyy-MM-dd").parse("1994-09-13");  
        User userentity = new User("nihel",birth,"french", "125478","female");
        given(userServiceImpl.detailsUserById(1)).willReturn(userentity);
        int id=1;
        String url = "/user/";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).path("/"+id);
        mvc.perform(get(builder.build().toUri())
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.name", is(userentity.getName())));
    	
    }
    public static String toJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
