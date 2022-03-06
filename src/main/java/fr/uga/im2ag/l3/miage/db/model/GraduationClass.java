package fr.uga.im2ag.l3.miage.db.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQuery(name="find-by-year-name", query="select gc from GraduationClass gc where gc.year = :year and gc.name = :name")
public class GraduationClass {

    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
    @Column(nullable=false)
    private String name;
    @Column(name="class_year", nullable=false)
    private Integer year;
    @OneToMany(mappedBy="belongTo")
    private List<Student> students;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GraduationClass setId(Long id) {
        this.id = id;
        return this;
    }

    public GraduationClass setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public GraduationClass setYear(Integer year) {
        this.year = year;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public GraduationClass setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public void addStudent(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
