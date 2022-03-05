package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.StudentRepository;
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
        // TODO
        entityManager.getTransaction().begin();
        final var graduationClass = Fixtures.createClass();
        entityManager.persist(graduationClass);
        final var student1 = Fixtures.createStudent(graduationClass);
        final var student2 = Fixtures.createStudent(graduationClass);
        studentRepository.save(student1);
        studentRepository.save(student2);
        // instanciate grades for each student, save all, select from avg
    }

}
