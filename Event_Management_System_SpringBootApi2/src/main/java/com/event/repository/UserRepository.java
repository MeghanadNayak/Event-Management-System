package com.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.event.beans.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User getUserByEmail(String email);

}
