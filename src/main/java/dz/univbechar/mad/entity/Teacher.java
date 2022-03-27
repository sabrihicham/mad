package dz.univbechar.mad.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table()
public class Teacher extends User {

    @OneToMany
    private Set<Module> modules;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Department> departments;

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}
