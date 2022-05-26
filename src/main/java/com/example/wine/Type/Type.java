package com.example.wine.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Type {

    private @Id @GeneratedValue Long id;
    private String name;

    public Type(String name) {
        this.name = name;
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
        if (!(o instanceof Type))
            return false;
        Type type = (Type) o;
        return Objects.equals(this.id, type.id) && Objects.equals(this.name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
