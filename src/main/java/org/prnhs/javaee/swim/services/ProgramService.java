package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.ProgramDao;
import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramService.class);
    
    @Autowired
    private ProgramDao dao;
    
    public ProgramDto save(ProgramDto programDto){
        if(programDto.getObjective() == null){
            LOGGER.warn("Save method called in ProgramService but the program object was null");
            throw new IllegalArgumentException("hey, you were supposed to give me a program!");
        }
        
        Program program = ProgramTranslator.toEntity(programDto);        
        program = dao.save(program);
        
        ProgramDto savedProgram = ProgramTranslator.toDto(program);
        LOGGER.debug("Save method executed successfully in ProgramService, the program {} got saved", savedProgram);
        return savedProgram;
    }
    
    public ProgramDto getById(Integer id){
        ProgramDto dto = null;
        Program p = dao.findOne(id);
        LOGGER.debug("getById method called in ProgramService, the given id was: {}", id);
        
        if(p != null){
            LOGGER.debug("getById called: A program was found: {}", p);
            dto = ProgramTranslator.toDto(p);
        }
        return dto;
    }
    
    public List<ProgramDto> getAll(){
        Iterable<Program> programs = dao.findAll();
        List<ProgramDto> dtos = new ArrayList<>();
        LOGGER.debug("getAll method called in ProgramService");
        
        if(programs!=null){
            Iterator<Program> iterator = programs.iterator();
            int count=0;    
            while(iterator.hasNext()){
                ProgramDto dto = ProgramTranslator.toDto(iterator.next());
                dtos.add(dto);
                count++;
            }
            LOGGER.debug("getAll method called in ProgramService and there were found {} programs", count);
        }
            
        return dtos;        
    }
    
    public void delete(Integer id){
        Program p = dao.findOne(id);
        LOGGER.debug("delete method called in ProgramService with the given parameter(id): {}", id);
        
        if(p != null){
            dao.delete(p);
            LOGGER.debug("A program was found and it was deleted successfully, id: {}", id);
        }
    }
}
