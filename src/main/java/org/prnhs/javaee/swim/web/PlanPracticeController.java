package org.prnhs.javaee.swim.web;

import org.prnhs.javaee.swim.dto.PlanPracticeDto;
import org.prnhs.javaee.swim.services.PlanPracticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanPracticeController.class);

    @Autowired
    private PlanPracticeService practiceService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanPracticeDto save(@RequestBody PlanPracticeDto practiceDto) {

        LOGGER.info("POST /practices get called.");
        LOGGER.debug("Saving: {}", practiceDto);

        return practiceService.save(practiceDto);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlanPracticeDto getById(@PathVariable Integer id) {

        LOGGER.info("GET /practices/{} get called.", id);

        PlanPracticeDto practiceDto = practiceService.getById(id);

        LOGGER.debug("Getting: {}", practiceDto);

        return practiceDto;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PlanPracticeDto> getAll() {

        LOGGER.info("GET /practices get called.");

        List<PlanPracticeDto> all = practiceService.getAll();

        LOGGER.debug("{} 'Plan Practices' got found.", all.size());
        LOGGER.debug("Getting: {}", all);

        return all;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

        LOGGER.info("DELETE /practices/{} get called.", id);

        practiceService.delete(id);
    }
}