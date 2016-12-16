package org.prnhs.javaee.swim.web;

import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.prnhs.javaee.swim.services.PlanResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 15, 2016.
 */
@RestController
@RequestMapping("/results")
public class PlanResultController {

    @Autowired
    private PlanResultService resultService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanResultDto save(@RequestBody PlanResultDto practiceDto) {
        return resultService.save(practiceDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanResultDto getById(@PathVariable Integer id) {
        return resultService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlanResultDto> getAll() {
        return resultService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        resultService.delete(id);
    }
}