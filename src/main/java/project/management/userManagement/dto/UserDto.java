package project.management.userManagement.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import project.management.userManagement.validator.UserCountryConstraint;
import project.management.userManagement.validator.AdultConstraint;

/**
 * Class Dto for entity user.
 */
public class UserDto {

	@NotEmpty
    private String name;
	
	@AdultConstraint
	private Date birthdate;
	
	@UserCountryConstraint
	private String country;
	
	private String phone;
	
	private String gender;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserDto(String name, Date birthdate, String country, String phone,
			String gender) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.country = country;
		this.phone = phone;
		this.gender = gender;
	}
	
	
	
}
