package com.una.carassistschedulebackend.rest;

import com.una.carassistschedulebackend.business.AssistanceService;
import com.una.carassistschedulebackend.business.ScheduleService;
import com.una.carassistschedulebackend.entities.Assistance;
import com.una.carassistschedulebackend.entities.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(value="")
    public List<Schedule> getSchedules() {
        return this.scheduleService.getSchedules();
    }

    @GetMapping(value="{id}")
    public Schedule getAssistanceById(@PathVariable String id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return this.scheduleService.getSchedulesById(id);
        }
        throw new Exception(String.format("Schedule with id %s does not exist", id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Schedule createSchedule(@RequestBody @NotNull Schedule schedule) throws Exception {
        return this.scheduleService.saveSchedule(schedule);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Schedule updateSchedule(@PathVariable String id, @RequestBody @NotNull Schedule schedule) throws Exception {
        return this.scheduleService.saveSchedule(schedule);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public void deleteSchedule(@PathVariable String id) throws Exception {
        this.scheduleService.deleteSchedule(id);
    }
}
