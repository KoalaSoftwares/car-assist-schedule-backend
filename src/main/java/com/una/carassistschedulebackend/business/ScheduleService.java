package com.una.carassistschedulebackend.business;

import com.una.carassistschedulebackend.entities.Schedule;
import com.una.carassistschedulebackend.models.AssistanceType;
import com.una.carassistschedulebackend.persistence.ScheduleRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final ScheduleRepository scheduleRepo;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepo = scheduleRepository;
    }

    public List<Schedule> getSchedules() {
        if (logger.isInfoEnabled()) {
            logger.info("Searching all schedules");
        }
        Iterable<Schedule> list = this.scheduleRepo.findAll();
        if (list == null) {
            return new ArrayList<Schedule>();
        }
        return IteratorUtils.toList(list.iterator());
    }

    public Schedule getSchedulesById(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Searching schedule with id: {}", id);
        }
        Optional<Schedule> schedule = this.scheduleRepo.findById(id);
        if(!schedule.isPresent()) {
            throw new RuntimeException(String.format("Schedule with id %s not found", id));
        }
        return schedule.get();
    }

    public List<Schedule> getSchedulesByClientName(String clientName) {
        if (logger.isInfoEnabled()) {
            logger.info("Searching schedules by client name {}",clientName);
        }
        Iterable<Schedule> list = this.scheduleRepo.findByClientName(clientName);
        if (list == null) {
            return new ArrayList<Schedule>();
        }
        return IteratorUtils.toList(list.iterator());
    }

    public List<Schedule> getSchedulesByServiceType(AssistanceType assistanceType) {
        if (logger.isInfoEnabled()) {
            logger.info("Searching by schedules with service type {}", assistanceType);
        }
        Iterable<Schedule> list = this.scheduleRepo.findByAssistanceType(assistanceType);
        if (list == null) {
            return new ArrayList<Schedule>();
        }
        return IteratorUtils.toList(list.iterator());
    }

    public Schedule saveSchedule(Schedule schedule) {
        if (logger.isInfoEnabled()) {
            logger.info("Saving schedule with details: {}", schedule.toString());
        }
        return this.scheduleRepo.save(schedule);
    }

    public void deleteSchedule(String id) {
        if (logger.isInfoEnabled()) {
            logger.info("Deleting schedule with id: {}", id);
        }
        this.scheduleRepo.deleteById(id);
    }

    public boolean isScheduleExists(Schedule schedule) {
        Optional<Schedule> res = this.scheduleRepo.findById(schedule.getId());
        return res.isPresent() ? true : false;
    }
}
