package by.sleptsov.library.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Book {
    private int id;

    @NotEmpty(message = "Поле имя не может быть пустым")
    @Size( max = 100, message = "Название не может превышать количества символов больше, чем 100 ")
    private String name;
    @NotEmpty(message = "Поле автора не может быть пустым")
    @Size( max = 50, message = "Имя автора не может превышать количества символов больше, чем 50 ")
    private String author;

    public Book() {
    }

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id  && Objects.equals(name, book.name)
                && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author);
    }
}
