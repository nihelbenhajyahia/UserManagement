package project.management.userManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import project.management.userManagement.entity.User;

/**
 * Repository for the Mapping with Database.
 */
public interface UserRepository extends JpaRepository<User,Integer>{

}
