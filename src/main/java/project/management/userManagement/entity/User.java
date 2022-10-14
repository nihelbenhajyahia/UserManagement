package project.management.userManagement.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Class Pojo for entity user that will transform on table userEntity in Database.
 */
@Entity
@Table(name="userEntity")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private Date birthdate;
	
	@Column(nullable=false)
	private String country;
	
	@Column
	private String phone;
	
	@Column
	private String gender;
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(String name, Date birthdate, String country, String phone, String gender) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.country = country;
		this.phone = phone;
		this.gender = gender;
	}



	public User(int id, String name, Date birthdate, String country, String phone, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.country = country;
		this.phone = phone;
		this.gender = gender;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	
}
