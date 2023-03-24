package com.una.persistence;

import java.util.List;
import java.util.UUID;

import com.una.entidades.Service;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan()
public interface ServiceRepository extends CrudRepository<Service, UUID> {
    List<Service> findById(String id);
}
