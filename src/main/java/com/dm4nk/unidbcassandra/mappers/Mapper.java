package com.dm4nk.unidbcassandra.mappers;

import com.dm4nk.unidbcassandra.model.BookByTitleAndPublisher;
import com.dm4nk.unidbcassandra.model.BookByYear;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {
    public static BookByTitleAndPublisher toBookByTitleAndPublisherRepository(BookByYear bookByYear) {
        return BookByTitleAndPublisher.builder()
                .id(bookByYear.getId())
                .title(bookByYear.getTitle())
                .publisher(bookByYear.getPublisher())
                .tags(bookByYear.getTags())
                .year(bookByYear.getYear())
                .build();
    }

    public static BookByYear toBookByYear(BookByTitleAndPublisher bookByTitleAndPublisher) {
        return BookByYear.builder()
                .id(bookByTitleAndPublisher.getId())
                .title(bookByTitleAndPublisher.getTitle())
                .publisher(bookByTitleAndPublisher.getPublisher())
                .tags(bookByTitleAndPublisher.getTags())
                .year(bookByTitleAndPublisher.getYear())
                .build();
    }
}
