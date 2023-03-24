package com.una.persistence;

import java.util.List;
import java.util.UUID;

import com.una.entidades.Schedule;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan()
public interface ScheduleRepository extends CrudRepository<Schedule, UUID> {
    List<Schedule> findById(String id);
    List<Schedule> findByClientName(String clientName);
}
