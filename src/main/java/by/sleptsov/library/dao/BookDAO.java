package by.sleptsov.library.dao;

import by.sleptsov.library.models.Book;
import by.sleptsov.library.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void save(Book newBook){
        jdbcTemplate.update("INSERT INTO book(name,author,reader) VALUES (?,?,?)", newBook.getName(),
                newBook.getAuthor(),null);
    }
    public void assignBook(int bookId, int personId){
        jdbcTemplate.update("UPDATE book SET reader=? WHERE id=?", personId, bookId);
    }
    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET name=?,author=? WHERE id=?",updatedBook.getName(),
                updatedBook.getAuthor(),id);
    }
    public Optional<Person> getOwnerById(int bookId){
        return (Optional<Person>) jdbcTemplate.query("SELECT * FROM book JOIN person ON book.id =? AND person.id = book.reader",
                new Object[] {bookId}, new BeanPropertyRowMapper(Person.class)).stream().findAny();
    }
    public void release(int id){
        jdbcTemplate.update("UPDATE book SET reader=null WHERE book.id=?",id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE id=?",id);
    }

}
