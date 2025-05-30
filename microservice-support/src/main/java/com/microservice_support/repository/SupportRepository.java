package com.microservice_support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice_support.model.Support;

public interface SupportRepository extends JpaRepository<Support, Long>{

}
