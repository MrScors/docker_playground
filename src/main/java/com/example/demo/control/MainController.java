package com.example.demo.control;

import com.example.demo.model.Book;
import com.example.demo.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    final
    BookRepo bookRepo;

    public MainController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping(path = "/read")
    public ResponseEntity<List<String>> readBooks() {
        List<String> books = new ArrayList<String>();
        for (Book b : bookRepo.findAll()) {
            books.add(b.toString());
        }

        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createBook(@QueryParam("author") String author,
                                             @QueryParam("title") String title
    ) {
        Book book = new Book(author, title);
        try {
            bookRepo.save(book);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving book", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }


    @PutMapping(path = "/update")
    public ResponseEntity<String> updateBook(@RequestParam("id") Long id,
                                             @RequestParam(defaultValue = "none") String author,
                                             @RequestParam(defaultValue = "none") String title
    ) {
        try {

            Book book = bookRepo.findById(id).orElse(null);
            assert book != null;

            if (!author.intern().equals("none")) {
                book.setAuthor(author);
            }
            if (!title.intern().equals("none")) {
                book.setTitle(title);
            }

            bookRepo.save(book);

        } catch (AssertionError e) {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete_by_author")
    public ResponseEntity<String> deleteBookByAuthor(@QueryParam("author") String author) {
        try {
            bookRepo.deleteByAuthor(author);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Recourse deleted successfully", HttpStatus.OK);
    }

}
