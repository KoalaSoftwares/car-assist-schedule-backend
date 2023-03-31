package com.una.carassistschedulebackend.persistence;

import java.util.List;

import com.una.carassistschedulebackend.entities.Assistance;
import com.una.carassistschedulebackend.models.AssistanceType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AssistanceRepository extends CrudRepository<Assistance, String> {
    List<Assistance> findByAssistanceType(AssistanceType assistanceType);
}
