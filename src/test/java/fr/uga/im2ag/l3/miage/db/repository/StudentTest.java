package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StudentTest extends Base {

    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        studentRepository = daoFactory.newStudentRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveStudent() {
        entityManager.getTransaction().begin();
        final var graduationClass = Fixtures.createClass();
        entityManager.persist(graduationClass);
        final var student = Fixtures.createStudent(graduationClass);
        studentRepository.save(student);
        entityManager.getTransaction().commit();
        entityManager.detach(student);
        
        var sStudent = studentRepository.findById(student.getId());
        assertThat(sStudent).isNotNull().isNotSameAs(student);
        assertThat(sStudent.getBelongTo().getId()).isEqualTo(student.getBelongTo().getId());
        assertThat(sStudent.getBirth().getTime()).isEqualTo(student.getBirth().getTime());
        assertThat(sStudent.getFirstName()).isEqualTo(student.getFirstName());
        assertThat(sStudent.getLastName()).isEqualTo(student.getLastName());
        assertThat(sStudent.getGender()).isEqualTo(student.getGender());
        
    }

    @Test
    void shouldFindStudentHavingGradeAverageAbove() {
        entityManager.getTransaction().begin();
        final var graduationClass = Fixtures.createClass();
        final var subject1 = Fixtures.createSubject().setPoints(2);
        final var subject2 = Fixtures.createSubject().setPoints(1);
        entityManager.persist(subject1);
        entityManager.persist(subject2);
        entityManager.persist(graduationClass);
        final var student1 = Fixtures.createStudent(graduationClass);
        final var student2 = Fixtures.createStudent(graduationClass);

        List<Grade> grades1 = new ArrayList();
        final var grade1 = Fixtures.createGrade(subject1).setValue(9.0f).setWeight(0.5f);
        grades1.add(grade1);
        final var grade2 = Fixtures.createGrade(subject1).setValue(12.0f).setWeight(1.0f);
        grades1.add(grade2);
        final var grade3 = Fixtures.createGrade(subject2).setValue(13.0f).setWeight(1.0f);
        grades1.add(grade3);
        
        List<Grade> grades2 = new ArrayList();
        final var grade4 = Fixtures.createGrade(subject1).setValue(10.0f).setWeight(0.5f);
        grades2.add(grade4);
        final var grade5 = Fixtures.createGrade(subject1).setValue(19.0f).setWeight(1.0f); 
        grades2.add(grade5);
        final var grade6 = Fixtures.createGrade(subject2).setValue(7.0f).setWeight(1.0f); 
        grades2.add(grade6);
        entityManager.persist(grade1);
        entityManager.persist(grade2);
        entityManager.persist(grade3);
        entityManager.persist(grade4);
        entityManager.persist(grade5);
        entityManager.persist(grade6);
        
        student1.setGrades(grades1);
        student2.setGrades(grades2);
        studentRepository.save(student1);
        studentRepository.save(student2);
        entityManager.getTransaction().commit();
        
        var studentsAb = studentRepository.findStudentHavingGradeAverageAbove(12.0f);
        assertThat(studentsAb.size()).isEqualTo(1);
        assertThat(studentsAb.get(0).getId()).isEqualTo(subject2.getId());
        
    }

}
