package org.prnhs.javaee.swim.service;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.prnhs.javaee.swim.core.dao.ProgramDao;
import org.prnhs.javaee.swim.core.entity.Program;
import org.prnhs.javaee.swim.dto.ProgramDto;
import org.prnhs.javaee.swim.services.ProgramService;


public class ProgramServiceTest {
    
    private static final String OBJECTIVE = "the objective";
    private static final int ID = 1;
    
    @InjectMocks
    private ProgramService uut;
    
    @Mock
    private ProgramDao dao;
    
    private Program program;
    private List<Program> programs;
    private ProgramDto dto;
    private List<ProgramDto> dtos;
    
    @Before
    public void onStartUp(){
        MockitoAnnotations.initMocks(this);
        
        program = new Program();
        program.setObjective(OBJECTIVE);
        
        dto = new ProgramDto();
        dto.setId(ID);
        dto.setObjective(OBJECTIVE);
    }
    
    @Test
    public void test_saveWithFound(){
        when(dao.findOne(Matchers.anyInt())).thenReturn(program);
        when(dao.save(program)).thenReturn(program);
        
        ProgramDto savedDto = uut.save(dto);
        Mockito.verify(dao).save(program);
    }
}
