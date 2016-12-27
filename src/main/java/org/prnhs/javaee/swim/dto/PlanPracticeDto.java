package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Time;

/**
 * Created by Durim Kryeziu on Dec 14, 2016.
 */
@ApiModel(value = "PlanPracticeDto", description = "Model for representing a Plan Practice")
public class PlanPracticeDto {

    @ApiModelProperty("Plan Practice's id")
    private Integer id;
    @ApiModelProperty("Plan Practice's length")
    private Integer length;
    @ApiModelProperty("Plan Practice's multiple")
    private Integer multiple;
    @ApiModelProperty("Plan Practice's exercise")
    private String exercise;
    @ApiModelProperty("Plan Practice's split")
    private Time split;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public Time getSplit() {
        return split;
    }

    public void setSplit(Time split) {
        this.split = split;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanPracticeDto that = (PlanPracticeDto) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (length != null ? !length.equals(that.length) : that.length != null) return false;
        if (multiple != null ? !multiple.equals(that.multiple) : that.multiple != null) return false;
        if (exercise != null ? !exercise.equals(that.exercise) : that.exercise != null) return false;
        return split != null ? split.equals(that.split) : that.split == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (length != null ? length.hashCode() : 0);
        result = 31 * result + (multiple != null ? multiple.hashCode() : 0);
        result = 31 * result + (exercise != null ? exercise.hashCode() : 0);
        result = 31 * result + (split != null ? split.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlanPracticeDto { " +
                "id = " + id +
                ", length = " + length +
                ", multiple = " + multiple +
                ", exercise = '" + exercise + '\'' +
                ", split = " + split +
                " }";
    }
}