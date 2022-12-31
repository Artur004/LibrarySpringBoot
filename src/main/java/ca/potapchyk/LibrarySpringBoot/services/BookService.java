package ca.potapchyk.LibrarySpringBoot.services;


import ca.potapchyk.LibrarySpringBoot.models.Book;
import ca.potapchyk.LibrarySpringBoot.models.Person;
import ca.potapchyk.LibrarySpringBoot.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    //Pagination
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear) {
        if (sortByYear)
            return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
        else
            return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    //Search
    public List<Book> search(String startsWith) {
        if (startsWith == null)
            return Collections.emptyList();
        return booksRepository.findAllByTitleContainingIgnoreCase(startsWith);
    }


    public Book findOne(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setBook_id(id);
        updatedBook.setOwner(bookToBeUpdated.getOwner());
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void release(int id) {
        Optional<Book> book = booksRepository.findById(id);
        if (book.isPresent()) {
            book.get().setOwner(null);
            book.get().setTakenAt(null);             ///-----------------/////
            booksRepository.save(book.get());
        }
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        Book book = booksRepository.findById(id).orElse(null);


        book.setOwner(selectedPerson);
       // selectedPerson.getBookList().add(book);
        book.setTakenAt(new Date());                     ///-----------------/////

        booksRepository.save(book);
    }

    public Optional<Person> getBookOwner(int id) {  // id (book id)  - return owner (if exist)
        Optional<Book> searchedBook = booksRepository.findById(id);
        if (searchedBook.isPresent()) {
            Book book = searchedBook.get();
            if (book.getOwner() == null)
                return Optional.empty();
            else
                return Optional.of(book.getOwner());
        } else
            return Optional.empty();
    }

}
