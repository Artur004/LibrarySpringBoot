package ca.potapchyk.LibrarySpringBoot.services;


import ca.potapchyk.LibrarySpringBoot.models.Book;
import ca.potapchyk.LibrarySpringBoot.models.Person;
import ca.potapchyk.LibrarySpringBoot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {

        updatedPerson.setPerson_id(id);
        peopleRepository.save(updatedPerson);
    }

    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> searchedPerson = peopleRepository.findById(id);

        if (searchedPerson.isPresent()) {
            Hibernate.initialize(searchedPerson.get().getBookList());

            /** -- Return EXPIRED check -- **/
            searchedPerson.get().getBookList().forEach(book -> {

                if (book.getTakenAt() != null) {
                    long timeDifference = (new Date().getTime() - book.getTakenAt().getTime());
                    long difference_in_days = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
                    System.out.println(difference_in_days > 10);
                    book.setOverDue(difference_in_days > 10);
                }

            });


            return searchedPerson.get().getBookList();

        } else {
            return Collections.EMPTY_LIST;
        }
    }
    public Optional<Person> getPersonByFullNameAndDateOfBirth(String fullName, Integer yearOfBirth) {
        return peopleRepository.findByFullNameAndYearOfBirth(fullName, yearOfBirth);
    }
}