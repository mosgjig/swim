package org.prnhs.javaee.swim.services;

import org.prnhs.javaee.swim.core.dao.PlanPracticeDao;
import org.prnhs.javaee.swim.core.entity.PlanPractice;
import org.prnhs.javaee.swim.dto.PlanPracticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@Service
public class PlanPracticeService {

    @Autowired
    private PlanPracticeDao dao;

    public PlanPracticeDto save(PlanPracticeDto planPracticeDto) {

        if (planPracticeDto == null) {
            throw new IllegalArgumentException("You have to provide the Plan Practice!");
        }

        PlanPractice planPractice = dao.findOne(planPracticeDto.getId());

        if (planPractice == null) {
            planPractice = PlanPracticeTranslator.toEntity(planPracticeDto);
        } else {
            planPractice.setLength(planPracticeDto.getLength());
            planPractice.setMultiple(planPracticeDto.getMultiple());
            planPractice.setExercise(planPracticeDto.getExercise());
            planPractice.setSplit(planPracticeDto.getSplit());
        }

        planPractice = dao.save(planPractice);

        return PlanPracticeTranslator.toDto(planPractice);
    }

    public PlanPracticeDto getById(Integer id) {

        PlanPracticeDto dto = null;

        PlanPractice planPractice = dao.findOne(id);

        if (planPractice != null) {
            dto = PlanPracticeTranslator.toDto(planPractice);
        }

        return dto;
    }

    public List<PlanPracticeDto> getAll() {
        Iterable<PlanPractice> planPractices = dao.findAll();

        List<PlanPracticeDto> dtos = new ArrayList<>();

        planPractices.forEach(planPractice -> dtos.add(PlanPracticeTranslator.toDto(planPractice)));

        /*Iterator<PlanPractice> iterator = planPractices.iterator();

        while (iterator.hasNext()) {
            PlanPractice planPractice = iterator.next();
            PlanPracticeDto dto = PlanPracticeTranslator.toDto(planPractice);
            dtos.add(dto);
        }*/

        return dtos;
    }

    public void delete(Integer id) {
        PlanPractice planPractice = dao.findOne(id);

        if (planPractice != null) {
            dao.delete(id);
        }
    }
}