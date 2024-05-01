package com.electromart.electromart.repository;

import com.electromart.electromart.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
