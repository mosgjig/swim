package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.PlanResultDao;
import org.prnhs.javaee.swim.core.entity.PlanResult;
import org.prnhs.javaee.swim.dto.PlanResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@Service
public class PlanResultService {

    @Autowired
    private PlanResultDao dao;

    public PlanResultDto save(PlanResultDto planResultDto) {

        if (planResultDto == null) {
            throw new IllegalArgumentException("You have to provide the Plan Result!");
        }

        PlanResult planResult = dao.findOne(planResultDto.getId());

        if (planResult == null) {
            planResult = PlanResultTranslator.toEntity(planResultDto);
        } else {
            planResult.setStrokes(planResultDto.getStrokes());
            planResult.setLength(planResultDto.getLength());
            planResult.setSwimTime(planResultDto.getSwimTime());
        }

        planResult = dao.save(planResult);

        return PlanResultTranslator.toDto(planResult);
    }

    public PlanResultDto getById(Integer id) {
        PlanResultDto dto = null;
        PlanResult planResult = dao.findOne(id);
        if (planResult != null) {
            dto = PlanResultTranslator.toDto(planResult);
        }

        return dto;
    }

    public List<PlanResultDto> getAll() {
        Iterable<PlanResult> planResults = dao.findAll();
        Iterator<PlanResult> iterator = planResults.iterator();
        List<PlanResultDto> dtos = new ArrayList<>();

        while (iterator.hasNext()) {
            PlanResult planResult = iterator.next();
            PlanResultDto dto = PlanResultTranslator.toDto(planResult);
            dtos.add(dto);
        }

        return dtos;
    }

    public void delete(Integer id) {
        PlanResult planResult = dao.findOne(id);
        if (planResult != null) {
            dao.delete(planResult);
        }
    }
}