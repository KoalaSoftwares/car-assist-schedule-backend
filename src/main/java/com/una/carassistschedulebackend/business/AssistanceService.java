package com.una.carassistschedulebackend.business;

import com.una.carassistschedulebackend.entidades.Assistance;
import com.una.carassistschedulebackend.models.AssistanceType;
import com.una.carassistschedulebackend.persistence.AssistanceRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssistanceService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final AssistanceRepository assistanceRepo;

    public AssistanceService(AssistanceRepository assistanceRepository) {
        this.assistanceRepo = assistanceRepository;
    }

    public List<Assistance> getAssistances() {
        if (logger.isInfoEnabled()) {
            logger.info("Searching for all assistances");
        }
        Iterable<Assistance> list = this.assistanceRepo.findAll();
        if (list == null) {
            return new ArrayList<Assistance>();
        }
        return IteratorUtils.toList(list.iterator());
    }

    public Assistance getAssistanceById(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Searching for assistance with id: {}", id);
        }
        Optional<Assistance> assistance = this.assistanceRepo.findById(id);
        if (!assistance.isPresent()) {
            throw new RuntimeException(String.format("Assistance with id: %s not found", id));
        }
        return assistance.get();
    }

    public List<Assistance> getAssistanceByType(AssistanceType assistanceType) {
        if (logger.isInfoEnabled()) {
            logger.info("Searching assistances with assistance type of: {}", assistanceType);
        }
        List<Assistance> list = this.assistanceRepo.findByAssistanceType(assistanceType);
        if (list == null) {
            logger.info("Not found any assistances with type of: {}", assistanceType);
        }
        return IteratorUtils.toList(list.iterator());
    }

    public Assistance saveAssistance(Assistance assistance) {
        if (logger.isInfoEnabled()) {
            logger.info("Saving assistance with details: {}", assistance.toString());
        }
        return this.assistanceRepo.save(assistance);
    }

    public void deleteAssistance(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Deleting assistance with id: {}", id);
        }
        this.assistanceRepo.deleteById(id);
    }

    public boolean isAssistanceExists(Assistance assistance) {
        Optional<Assistance> assist = this.assistanceRepo.findById(assistance.getId());
        return assist.isPresent() ? true : false;
    }
}
