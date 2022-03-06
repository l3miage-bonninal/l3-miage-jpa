package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.TeacherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TeacherTest extends Base {

    TeacherRepository teacherRepository;

    @BeforeEach
    void before() {
        teacherRepository = daoFactory.newTeacherRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveTeacher()  {
        entityManager.getTransaction().begin();
        final var subject = Fixtures.createSubject();
        final var gradClass = Fixtures.createClass();
        entityManager.persist(subject);
        entityManager.persist(gradClass);
        final var teacher = Fixtures.createTeacher(subject, gradClass);
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        
        entityManager.detach(teacher);
        
        final var sTeacher = teacherRepository.findById(teacher.getId());
        assertThat(sTeacher).isNotNull().isNotSameAs(teacher);
        assertThat(sTeacher.getLastName()).isEqualTo(teacher.getLastName());
        assertThat(sTeacher.getFirstName()).isEqualTo(teacher.getFirstName());
    }

    @Test
    void shouldFindHeadingGraduationClassByYearAndName() {
        entityManager.getTransaction().begin();
        final var subject = Fixtures.createSubject();
        final var gradClass = Fixtures.createClass();
        entityManager.persist(subject);
        entityManager.persist(gradClass);
        final var teacher = Fixtures.createTeacher(subject, gradClass);
        teacherRepository.save(teacher);
        entityManager.getTransaction().commit();
        
        entityManager.detach(teacher);
        
        final var sTeacher = teacherRepository.findHeadingGraduationClassByYearAndName(teacher.getHeading().getYear(), teacher.getHeading().getName());
        assertThat(sTeacher).isNotNull().isNotSameAs(teacher);
        assertThat(sTeacher.getLastName()).isEqualTo(teacher.getLastName());
        assertThat(sTeacher.getFirstName()).isEqualTo(teacher.getFirstName());
    }

}
