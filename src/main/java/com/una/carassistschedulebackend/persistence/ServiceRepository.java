package com.una.carassistschedulebackend.persistence;

import java.util.List;
import java.util.UUID;

import com.una.carassistschedulebackend.entidades.Schedule;
import com.una.carassistschedulebackend.entidades.Service;
import com.una.carassistschedulebackend.models.ServiceType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ServiceRepository extends CrudRepository<Service, UUID> {
    List<Service> findById(String id);

    List<Service> findByServiceType(ServiceType serviceType);
}
