package com.example.demo.repo;

import com.example.demo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepo extends CrudRepository<Book, Long> {
    @Transactional
    void deleteByAuthor(String s);
}
