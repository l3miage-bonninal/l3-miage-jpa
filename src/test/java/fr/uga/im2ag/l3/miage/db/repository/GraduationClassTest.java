package fr.uga.im2ag.l3.miage.db.repository;

import fr.uga.im2ag.l3.miage.db.repository.api.GraduationClassRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class GraduationClassTest extends Base {

    GraduationClassRepository classRepository;

    @BeforeEach
    void before() {
        classRepository = daoFactory.newGraduationClassRepository(entityManager);
    }

    @AfterEach
    void after() {
        if (entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback();
        }
    }

    @Test
    void shouldSaveClass() {
        entityManager.getTransaction().begin();
        final var gradClass = Fixtures.createClass();
        classRepository.save(gradClass);
        entityManager.getTransaction().commit();
        entityManager.detach(gradClass);
        
        final var sClass = classRepository.findById(gradClass.getId());
        assertThat(sClass).isNotNull().isNotSameAs(gradClass);
        assertThat(sClass.getName()).isEqualTo(gradClass.getName());
        assertThat(sClass.getYear()).isEqualTo(gradClass.getYear());
    }


    @Test
    void shouldFindByYearAndName() {
        entityManager.getTransaction().begin();
        final var gradClass = Fixtures.createClass();
        classRepository.save(gradClass);
        entityManager.getTransaction().commit();
        entityManager.detach(gradClass);
        
        final var sClass = classRepository.findByYearAndName(gradClass.getYear(), gradClass.getName());
        assertThat(sClass).isNotNull().isNotSameAs(gradClass);
        assertThat(sClass.getId()).isEqualTo(gradClass.getId());
    }

}
