package com.una.carassistschedulebackend.persistence;

import java.util.List;
import java.util.UUID;

import com.una.carassistschedulebackend.entidades.Assistance;
import com.una.carassistschedulebackend.models.AssistanceType;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AssistanceRepository extends CrudRepository<Assistance, UUID> {
    List<Assistance> findById(String id);

    List<Assistance> findByAssistanceType(AssistanceType assistanceType);
}
