package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.GraduationClass;
import fr.uga.im2ag.l3.miage.db.model.Student;
import fr.uga.im2ag.l3.miage.db.model.Teacher;
import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.SubjectRepository;
import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SubjectTest extends Base {

    SubjectRepository subjectRepository;
    TeacherRepository teacherRepository;
    GraduationClassRepository gcRepository;
    StudentRepository studentRepository;

    @BeforeEach
    void before() {
        subjectRepository = daoFactory.newSubjectRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveSubject() {

        final var subject = Fixtures.createSubject();
        //subject.setId(Long.valueOf(2));

        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        entityManager.getTransaction().commit();
        entityManager.detach(subject);

        var pSubject = subjectRepository.findById(subject.getId());
        assertThat(pSubject).isNotNull().isNotSameAs(subject);
        assertThat(pSubject.getName()).isEqualTo(subject.getName());

    }

    @Test
    void shouldFindTeachersForSubject() {
        /*final var subject = Fixtures.createSubject();
        entityManager.getTransaction().begin();
        subjectRepository.save(subject);
        final var graduationClass = Fixtures.createClass();
        gcRepository.save(graduationClass);
        final var student = Fixtures.createStudent(graduationClass);
        final var students = new Student[1];
        students[0] = student;
        final var teacher = Fixtures.createTeacher(subject, graduationClass, students);
        
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        
        Collection<Teacher> foundTeachers = subjectRepository.findTeachers(subject.getId());
        assertThat(foundTeachers.size()).isEqualTo(1);
        assertThat(foundTeachers.contains(teacher)).isTrue();//*/
    }

}
