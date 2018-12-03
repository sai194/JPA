package com.sye.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sye.microservices.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
