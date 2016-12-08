
package org.prnhs.javaee.swim.core.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="addresses")
public class Address {
    @Id
    @GeneratedValue
    private Integer id;
    private String line1;
    private String line2;
    private String city;
    private String country_code;
    private String postal_code;

    public Integer getId() {
        return id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryCode() {
        return country_code;
    }

    public void setCountryCode(String countryCode) {
        this.country_code = countryCode;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postalCode) {
        this.postal_code = postalCode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.line1);
        hash = 41 * hash + Objects.hashCode(this.line2);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.country_code);
        hash = 41 * hash + Objects.hashCode(this.postal_code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.line1, other.line1)) {
            return false;
        }
        if (!Objects.equals(this.line2, other.line2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country_code, other.country_code)) {
            return false;
        }
        if (!Objects.equals(this.postal_code, other.postal_code)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
        
}


