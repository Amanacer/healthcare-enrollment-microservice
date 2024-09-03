package com.challenge.enrollment.enrolleeservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.challenge.enrollment.enrolleeservice.entity.Enrollee;
import com.challenge.enrollment.enrolleeservice.repository.EnrolleeRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EnrolleRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EnrolleeRepository enrolleeRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Enrollee makar = new Enrollee("makar");
        entityManager.persistAndFlush(makar);

        // when
        Enrollee found = enrolleeRepository.findByName(makar.getName());

        // then
        assertThat(found.getName()).isEqualTo(makar.getName(), true);
    }

    @Test
    public void whenFindById_thenReturnEnrollee() {
        // given
        Enrollee makar = new Enrollee("makar");
        entityManager.persistAndFlush(makar);

        // when
        Enrollee found = enrolleeRepository.findById(makar.getEnrollee_Id()).orElse(null);
	 
	    // then
	    assertThat(found.getEnrollee_Id()).isEqualTo(makar.getEnrollee_Id());
    }
}
