package com.example.wine.Region;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region {
    private @Id
    @GeneratedValue Long id;
    @NotNull
    private String name;
    @NotNull
    private String country;

    public Region(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Region() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Region))
            return false;
        Region region = (Region) o;
        return Objects.equals(this.id, region.id) && Objects.equals(this.name, region.name)
                && Objects.equals(this.country, region.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.country);
    }

    @Override
    public String toString() {
        return "Region{" + "id=" + this.id + ", Region='" + this.name + '\'' + ", country='" + this.country + '\'' + '}';
    }
}
