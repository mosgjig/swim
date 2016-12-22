package org.prnhs.javaee.swim.web;

import org.prnhs.javaee.swim.dto.PlanPracticeDto;
import org.prnhs.javaee.swim.services.PlanPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 15, 2016.
 */
@RestController
@RequestMapping("/practices")
public class PlanPracticeController {

    @Autowired
    private PlanPracticeService practiceService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanPracticeDto save(@RequestBody PlanPracticeDto practiceDto) {
        return practiceService.save(practiceDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanPracticeDto getById(@PathVariable Integer id) {
        return practiceService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlanPracticeDto> getAll() {
        return practiceService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        practiceService.delete(id);
    }
}