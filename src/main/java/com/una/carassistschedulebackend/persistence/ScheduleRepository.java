package com.una.carassistschedulebackend.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.una.carassistschedulebackend.entidades.Schedule;
import com.una.carassistschedulebackend.models.ServiceType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface ScheduleRepository extends CrudRepository<Schedule, String> {
    List<Schedule> findByClientName(String clientName);

    List<Schedule> findByServiceType(ServiceType serviceType);

}
