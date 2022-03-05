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
        // TODO
    }

    @Test
    void shouldFailUpgradeGrade() {
        // TODO, ici tester que la mise à jour n'a pas eu lieu.
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
        entityManager.persist(grade1);
        entityManager.persist(grade2);
        entityManager.persist(grade3);
        entityManager.getTransaction().commit();
        
        Collection<Grade> bestGrades = gradeRepository.findHighestGrades(13);
        assertThat(bestGrades.size()).isEqualTo(1);
        assertThat(bestGrades.contains(grade2)).isTrue();
    }

    @Test
    void shouldFindHighestGradesBySubject() {
        // TODO
    }

}
