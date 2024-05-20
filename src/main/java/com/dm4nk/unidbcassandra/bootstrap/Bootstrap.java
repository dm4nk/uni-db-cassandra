package com.dm4nk.unidbcassandra.bootstrap;

import com.dm4nk.unidbcassandra.mappers.Mapper;
import com.dm4nk.unidbcassandra.model.BookByTitleAndPublisher;
import com.dm4nk.unidbcassandra.repository.BookByTitleAndPublisherRepository;
import com.dm4nk.unidbcassandra.repository.BookByYearRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final BookByTitleAndPublisherRepository bookByTitleAndPublisherRepository;
    private final BookByYearRepository bookByYearRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        bookByTitleAndPublisherRepository.deleteAll();
        bookByYearRepository.deleteAll();

        BookByTitleAndPublisher bookByTitleAndPublisher1 = BookByTitleAndPublisher.builder()
                .id(UUID.randomUUID())
                .title("Clear Code")
                .publisher("Martin")
                .tags(Set.of("programming", "java"))
                .year(1999)
                .build();

        BookByTitleAndPublisher bookByTitleAndPublisher2 = BookByTitleAndPublisher.builder()
                .id(UUID.randomUUID())
                .title("Clear Architecture")
                .publisher("Martin")
                .tags(Set.of("programming", "architecture"))
                .year(2000)
                .build();

        bookByTitleAndPublisherRepository.save(bookByTitleAndPublisher1);
        bookByTitleAndPublisherRepository.save(bookByTitleAndPublisher2);
        bookByYearRepository.save(Mapper.toBookByYear(bookByTitleAndPublisher1));
        bookByYearRepository.save(Mapper.toBookByYear(bookByTitleAndPublisher2));
    }
}
