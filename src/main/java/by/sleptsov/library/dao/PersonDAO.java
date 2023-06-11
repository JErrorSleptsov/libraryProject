package by.sleptsov.library.dao;

import by.sleptsov.library.models.Book;
import by.sleptsov.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Person> index(){
        List<Person> people = jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        return people;
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public List<Book> showPersonsBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Person JOIN book On Person.id =? " +
                        "AND Person.id = book.reader",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,birth_date) VALUES (?,?)",
                person.getName(),person.getBirthDate());
    }
    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET name=?, birth_date=? WHERE id=?",updatedPerson.getName(),
                updatedPerson.getBirthDate(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?",id);
    }
}
