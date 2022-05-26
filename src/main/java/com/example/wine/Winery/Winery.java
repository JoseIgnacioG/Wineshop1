package com.example.wine.Winery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Winery {

    private @Id @GeneratedValue Long id;
    private String name;

    public Winery(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Winery))
            return false;
        Winery winery = (Winery) o;
        return Objects.equals(this.id, winery.id) && Objects.equals(this.name, winery.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Winery{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
