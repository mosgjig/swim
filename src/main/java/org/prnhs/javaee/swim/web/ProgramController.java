package org.prnhs.javaee.swim.web;

import java.util.List;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.prnhs.javaee.swim.services.ProgramService;
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
    
    @Autowired
    ProgramService programService;
    
    @RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ProgramDto save(@RequestBody ProgramDto dto){
        return programService.save(dto);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProgramDto getById(@PathVariable Integer id){
        return programService.getById(id);
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProgramDto> getAll(){
        return programService.getAll();
    }
    
    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable Integer id){
        programService.delete(id);
    }
}
