package ca.potapchyk.LibrarySpringBoot.dao;//package ca.potapchyk.librarymvc.dao;
//
//import ca.potapchyk.librarymvc.models.Book;
//import ca.potapchyk.librarymvc.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//
//import java.util.List;
//import java.util.Optional;
//
//
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM PERSON WHERE person_id=?", id);
//    }
//
//    public void update(int id, Person updatedPerson) {
//        jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? where person_id=?",
//                updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
//    }
//
//    public List<Book> getBooksByPersonId(int id) {
//        return jdbcTemplate.query( "SELECT * FROM BOOK WHERE PERSON_ID=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
//    }
//
//
//    //For validation {Check if person is already exist}
//    public Optional<Person> getPersonByFullNameAndDateOfBirth(Person person) {
//        return jdbcTemplate.query("SELECT * where fullName =? and year_of_birth=?, values (?,?)",
//                new Object[] {person.getFullName(), person.getYearOfBirth()},
//                new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//}
