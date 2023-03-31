package com.una.carassistschedulebackend.persistence;

import java.util.List;

import com.una.carassistschedulebackend.entities.Schedule;
import com.una.carassistschedulebackend.models.AssistanceType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ScheduleRepository extends CrudRepository<Schedule, String> {
    List<Schedule> findByClientName(String clientName);

    List<Schedule> findByAssistanceType(AssistanceType assistanceType);

}
