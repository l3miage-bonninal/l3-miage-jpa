package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.model.Grade;
import fr.uga.im2ag.l3.miage.db.repository.api.GradeRepository;
import java.util.Collection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GradeTest extends Base {

    GradeRepository gradeRepository;

    @BeforeEach
    void before() {
        gradeRepository = daoFactory.newGradeRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveGrade() {
        entityManager.getTransaction().begin();
        final var subject = Fixtures.createSubject();
        entityManager.persist(subject);
        final var grade = Fixtures.createGrade(subject);
        gradeRepository.save(grade);
        entityManager.getTransaction().commit();
        entityManager.detach(grade);
        
        var savedGrade = gradeRepository.findById(grade.getId());
        assertThat(savedGrade).isNotNull().isNotSameAs(grade);
        assertThat(grade.getValue()).isEqualTo(savedGrade.getValue());
        assertThat(grade.getSubject().getName()).isEqualTo(savedGrade.getSubject().getName());
        assertThat(grade.getWeight()).isEqualTo(savedGrade.getWeight());
        
    }

    @Test
    void shouldFailUpgradeGrade() {
        entityManager.getTransaction().begin();
        final var subject = Fixtures.createSubject();
        entityManager.persist(subject);
        final var grade1 = Fixtures.createGrade(subject);
        grade1.setValue(10.0f);
        gradeRepository.save(grade1);
        entityManager.getTransaction().commit();
        
        grade1.setValue(11.0f);
        entityManager.detach(grade1);
        
        var sGrade = gradeRepository.findById(grade1.getId());
        assertThat(sGrade).isNotNull().isNotSameAs(grade1);
        assertThat(sGrade.getValue()).isEqualTo(10.0f);
        
        
        
    }

    @Test
    void shouldFindHighestGrades() {
        entityManager.getTransaction().begin();
        final var subject = Fixtures.createSubject();
        entityManager.persist(subject);
        final var grade1 = Fixtures.createGrade(subject);
        final var grade2 = Fixtures.createGrade(subject);
        final var grade3 = Fixtures.createGrade(subject);
        grade1.setValue(10.0f);
        grade2.setValue(20.0f);
        grade3.setValue(12.0f);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        entityManager.getTransaction().commit();
        
        Collection<Grade> bestGrades = gradeRepository.findHighestGrades(13);
        assertThat(bestGrades.size()).isEqualTo(1);
        assertThat(bestGrades.contains(grade2)).isTrue();
    }

    @Test
    void shouldFindHighestGradesBySubject() {
        // TODO
        entityManager.getTransaction().begin();
        final var subject1 = Fixtures.createSubject();
        entityManager.persist(subject1);
        final var grade1 = Fixtures.createGrade(subject1);
        final var grade2 = Fixtures.createGrade(subject1);
        final var grade3 = Fixtures.createGrade(subject1);
        grade1.setValue(10.0f);
        grade2.setValue(20.0f);
        grade3.setValue(12.0f);
        gradeRepository.save(grade1);
        gradeRepository.save(grade2);
        gradeRepository.save(grade3);
        final var subject2 = Fixtures.createSubject();
        entityManager.persist(subject2);
        final var grade4 = Fixtures.createGrade(subject2);
        final var grade5 = Fixtures.createGrade(subject2);
        final var grade6 = Fixtures.createGrade(subject2);
        grade4.setValue(11.0f);
        grade5.setValue(19.0f);
        grade6.setValue(13.0f);
        gradeRepository.save(grade4);
        gradeRepository.save(grade5);
        gradeRepository.save(grade6);
        
        entityManager.getTransaction().commit();
        
        Collection<Grade> bestGrades = gradeRepository.findHighestGradesBySubject(13, subject2);
        
        assertThat(bestGrades).isNotNull();
        assertThat(bestGrades.size()).isEqualTo(2);
    }

}
