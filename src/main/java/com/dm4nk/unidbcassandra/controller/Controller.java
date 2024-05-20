package com.dm4nk.unidbcassandra.controller;

import com.dm4nk.unidbcassandra.mappers.Mapper;
import com.dm4nk.unidbcassandra.model.BookByTitleAndPublisher;
import com.dm4nk.unidbcassandra.model.BookByYear;
import com.dm4nk.unidbcassandra.repository.BookByTitleAndPublisherRepository;
import com.dm4nk.unidbcassandra.repository.BookByYearRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class Controller {
    private final BookByTitleAndPublisherRepository bookByTitleAndPublisherRepository;
    private final BookByYearRepository bookByYearRepository;

    @GetMapping("/all")
    public ResponseEntity<List<BookByTitleAndPublisher>> getAll() {
        return ResponseEntity.ok(bookByTitleAndPublisherRepository.findAll());
    }

    @GetMapping("/title")
    public ResponseEntity<BookByTitleAndPublisher> get(@RequestParam String title, @RequestParam String publisher) {
        return ResponseEntity.ok(bookByTitleAndPublisherRepository.findByTitleAndPublisher(title, publisher).orElse(null));
    }

    @GetMapping("/tags")
    public ResponseEntity<List<BookByTitleAndPublisher>> getByTags(@RequestParam String tag) {
        return ResponseEntity.ok(bookByTitleAndPublisherRepository.findAllByTagsIsIn(tag));
    }

    @GetMapping("/year")
    public ResponseEntity<List<BookByYear>> getByYear(@RequestParam Integer year) {
        return ResponseEntity.ok(bookByYearRepository.findAllByYear(year));
    }

    @PostMapping
    public ResponseEntity<BookByTitleAndPublisher> create(@RequestBody BookByTitleAndPublisher bookByTitleAndPublisher) {
        BookByTitleAndPublisher saved = bookByTitleAndPublisherRepository.save(bookByTitleAndPublisher);
        bookByYearRepository.save(Mapper.toBookByYear(bookByTitleAndPublisher));
        return ResponseEntity.ok(saved);
    }

    @PatchMapping
    public ResponseEntity<BookByTitleAndPublisher> update(@RequestBody BookByTitleAndPublisher bookByTitleAndPublisher) {
        if (bookByTitleAndPublisher.getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        BookByTitleAndPublisher saved = bookByTitleAndPublisherRepository.save(bookByTitleAndPublisher);
        bookByYearRepository.save(Mapper.toBookByYear(bookByTitleAndPublisher));
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam String title, @RequestParam String publisher) {
        BookByTitleAndPublisher deleted = bookByTitleAndPublisherRepository.findByTitleAndPublisher(title, publisher).orElse(null);

        bookByTitleAndPublisherRepository.delete(deleted);
        bookByYearRepository.delete(Mapper.toBookByYear(deleted));
        return ResponseEntity.ok().build();
    }
}
