package com.microservice_support.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice_support.model.Support;

public interface ISupportRepository extends JpaRepository<Support, Long>{

}
