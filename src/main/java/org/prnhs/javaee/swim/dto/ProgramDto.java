package org.prnhs.javaee.swim.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import org.springframework.hateoas.ResourceSupport;

@ApiModel(value="ProgramDto", description="Model for representing a Program")
public class ProgramDto extends ResourceSupport implements Serializable{
    
    @ApiModelProperty(value = "Program's key/id", readOnly = true)
    private Integer keyId;
    @ApiModelProperty(value = "Program's objective", required = true)
    private String objective;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
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
        hash = 29 * hash + this.keyId;
        hash = 29 * hash + Objects.hashCode(this.objective);
        return hash;
    }

    @Override
    public String toString() {
        return "ProgramDto{" + "id=" + keyId + ", objective=" + objective + '}';
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
        if (this.keyId != other.keyId) {
            return false;
        }
        if (!Objects.equals(this.objective, other.objective)) {
            return false;
        }
        return true;
    }    
    
}
