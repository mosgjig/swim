package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;

public class ProgramTranslator {
    
    public static ProgramDto toDto(Program p){
        if(p == null){
            throw new IllegalArgumentException("hey, you were supposed to give me a program!");
        }
        
        ProgramDto pDto = new ProgramDto();
        pDto.setId(p.getId());
        pDto.setObjective(p.getObjective());
        
        return pDto;
    }
    
    public static Program toEntity(ProgramDto dto){
        if(dto == null){
            throw new IllegalArgumentException("hey, you were supposed to give me a program!");
        }
        Program p = new Program();
        p.setObjective(dto.getObjective());
        
        return p;
    }
}
