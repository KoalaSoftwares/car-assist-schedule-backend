package com.una.carassistschedulebackend.rest;

import com.una.carassistschedulebackend.business.AssistanceService;
import com.una.carassistschedulebackend.entities.Assistance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "assistance")
@CrossOrigin(origins = "*", allowedHeaders = {"Authorization", "Content-Type"})
public class AssistanceController {
    private final AssistanceService assistanceService;

    public AssistanceController(AssistanceService assistanceService) {
        this.assistanceService = assistanceService;
    }

    @GetMapping(value = "")
    public List<Assistance> getAssistances() {
        return this.assistanceService.getAssistances();
    }

    @GetMapping(value = "{id}")
    public Assistance getAssistanceById(@PathVariable String id) throws Exception {
        if (!ObjectUtils.isEmpty(id)) {
            return this.assistanceService.getAssistanceById(id);
        }
        throw new Exception(String.format("Assistance with id %s does not exist", id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assistance createAssistance(@RequestBody @NotNull Assistance assistance) throws Exception {
        return this.assistanceService.saveAssistance(assistance);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Assistance updateAssistance(@PathVariable String id, @RequestBody @NotNull Assistance assistance) throws Exception {
        return this.assistanceService.saveAssistance(assistance);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public void deleteAssistance(@PathVariable String id) throws Exception {
        this.assistanceService.deleteAssistance(id);
    }
}
