package ca.potapchyk.LibrarySpringBoot.dao;//package ca.potapchyk.librarymvc.dao;
//
//import ca.potapchyk.librarymvc.models.Book;
//import ca.potapchyk.librarymvc.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//

//public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//

//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        List<Book> list = jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
//        System.out.println(list.get(1).getBook_id());
//        return list; //jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Book show(int id) {
//      return jdbcTemplate.query("SELECT * FROM BOOK WHERE book_id=?", new Object[]{id},
//                        new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO book( title, author, year) VALUES (?,?,?)",
//                book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book updatedBook) {
//        jdbcTemplate.update("UPDATE book SET title=?, author=?,year=?",
//                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear());
//    }
//
//    public Optional<Person> getBookOwner(int id) {
//        return jdbcTemplate.query("SELECT Person.* FROM book JOIN person ON book.person_id = person.person_id WHERE book.book_id=?",
//                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public void release(int id) {
//        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
//    }
//
//    public void assign(int id, Person selectedPerson) {
//        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", selectedPerson.getPerson_id(), id);
//        System.out.println("Assign method - "  + "Person id: " + selectedPerson.getPerson_id() + "book id: " + id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", id);
//    }
//
//}
