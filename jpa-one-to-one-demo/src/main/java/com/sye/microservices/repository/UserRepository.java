package com.sye.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sye.microservices.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}