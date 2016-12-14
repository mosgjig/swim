package org.prnhs.javaee.swim.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.prnhs.javaee.swim.core.dao.ProgramDao;
import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    
    @Autowired
    private ProgramDao dao;
    
    public ProgramDto save(ProgramDto programDto){
        if(programDto == null){
            throw new IllegalArgumentException("hey, you were supposed to give me a program!");
        }
        Program program = dao.findOne(programDto.getId());
        
        if(program == null){
            program = ProgramTranslator.toEntity(programDto);
        }
        else{
            program.setObjective(programDto.getObjective());
        }
        
        program = dao.save(program);
        
        ProgramDto savedProgram = ProgramTranslator.toDto(program);
        
        return savedProgram;
    }
    
    public ProgramDto getById(int id){
        ProgramDto dto = null;
        Program p = dao.findOne(id);
        
        if(p != null){
            dto = ProgramTranslator.toDto(p);
        }
        return dto;
    }
    
    public List<ProgramDto> getAll(){
        Iterable<Program> programs = dao.findAll();
        Iterator<Program> iterator = programs.iterator();
        List<ProgramDto> dtos = new ArrayList<>();
        
        while(iterator.hasNext()){
            ProgramDto dto = ProgramTranslator.toDto(iterator.next());
            dtos.add(dto);
        }
            
        return dtos;        
    }
    
    public void delete(int id){
        Program p = dao.findOne(id);
        if(p != null){
            dao.delete(p);
        }
    }
}
