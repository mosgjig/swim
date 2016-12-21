package org.prnhs.javaee.swim.web;

import java.util.List;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.prnhs.javaee.swim.services.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
public class ProgramController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramController.class);
    
    @Autowired
    private ProgramService programService;
    
    @RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ProgramDto save(@RequestBody ProgramDto dto){
        LOGGER.debug("Save method called in ProgramController. Saving program with this objective: {}", dto.getObjective());
        return programService.save(dto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgramDto getById(@PathVariable Integer id){
        LOGGER.debug("getById method called in ProgramController with the id value: {}", id);
        return programService.getById(id);
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProgramDto> getAll(){
        LOGGER.debug("getAll method called in ProgramController class");
        return programService.getAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id){
        LOGGER.debug("Delete method called in ProgramController with the id value: {}", id);
        programService.delete(id);
    }
}
