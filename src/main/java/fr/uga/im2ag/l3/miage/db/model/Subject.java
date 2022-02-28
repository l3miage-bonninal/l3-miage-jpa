package fr.uga.im2ag.l3.miage.db.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

// TODO ajouter une named query pour une des requêtes à faire dans le repository
@Entity
@NamedQuery(name="teachers-by-subject", query="select t from Teacher t join t.teaching s where s.id = :id ")
public class Subject {

    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String name;
    private Integer points;
    private Float hours;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public Subject setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPoints() {
        return points;
    }

    public Subject setPoints(Integer points) {
        this.points = points;
        return this;
    }

    public Float getHours() {
        return hours;
    }

    public Subject setHours(Float hours) {
        this.hours = hours;
        return this;
    }

    public Date getStart() {
        return startDate;
    }

    public Subject setStart(Date start) {
        this.startDate = start;
        return this;
    }

    public Date getEnd() {
        return endDate;
    }

    public Subject setEnd(Date end) {
        this.endDate = end;
        return this;
    }
}
