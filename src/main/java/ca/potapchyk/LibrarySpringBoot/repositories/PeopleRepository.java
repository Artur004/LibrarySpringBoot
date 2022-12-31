package ca.potapchyk.LibrarySpringBoot.repositories;


import ca.potapchyk.LibrarySpringBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

   //  Optional<Person> findByFullNameAndYearOfBirth(Person person);

    Optional<Person> findByFullNameAndYearOfBirth(String fullName, Integer yearOfBirth);
}