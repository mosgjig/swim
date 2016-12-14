package org.prnhs.javaee.swim.dto;

import java.util.Objects;

public class ProgramDto {
    
    private int id;
    private String objective;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.objective);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProgramDto other = (ProgramDto) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.objective, other.objective)) {
            return false;
        }
        return true;
    }
    
    
}
