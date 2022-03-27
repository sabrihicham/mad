package dz.univbechar.mad.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table()
public class Student extends User {

    @ManyToMany
    @JsonIgnoreProperties("{Parent}")
    private Set<Parent> parents;

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }
}
