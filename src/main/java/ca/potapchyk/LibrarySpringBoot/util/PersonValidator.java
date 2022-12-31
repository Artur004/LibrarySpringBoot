package ca.potapchyk.LibrarySpringBoot.util;

import ca.potapchyk.LibrarySpringBoot.models.Person;
import ca.potapchyk.LibrarySpringBoot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personService.getPersonByFullNameAndDateOfBirth(person.getFullName(), person.getYearOfBirth()).isPresent())
            errors.rejectValue("fullName", "", "Such user already exists");
    }
}