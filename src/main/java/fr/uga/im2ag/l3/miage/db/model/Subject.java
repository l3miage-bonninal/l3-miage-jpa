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

@Entity
@NamedQuery(name="teachers-by-subject", query="select t from Teacher t join t.teaching s where s.id = :id ")
public class Subject {

    @Id
    @GenericGenerator(name = "kaugen1", strategy = "increment")
    @GeneratedValue(generator = "kaugen1")
    private Long id;
    
    @Column(unique=true)
    private String name;
    
    private Integer points;
    
    @Column(nullable=false)
    private Float hours;
    @Column(nullable=false)
    private Date startDate;
    @Column(nullable=false)
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
