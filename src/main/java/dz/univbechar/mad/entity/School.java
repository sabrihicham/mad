package dz.univbechar.mad.entity;

import dz.univbechar.mad.constants.Enums;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private Enums.SchoolType type;

    @OneToMany
    private List<Faculty> faculties;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Enums.SchoolType getType() {
        return type;
    }

    public void setType(Enums.SchoolType type) {
        this.type = type;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return id == school.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
