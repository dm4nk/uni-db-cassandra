package com.dm4nk.unidbcassandra.repository;

import com.dm4nk.unidbcassandra.model.BookByTitleAndPublisher;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookByTitleAndPublisherRepository extends CassandraRepository<BookByTitleAndPublisher, UUID> {

    @Query("SELECT * FROM bookbytitleandpublisher WHERE title = ?0 and publisher = ?1")
    Optional<BookByTitleAndPublisher> findByTitleAndPublisher(String title, String publisher);

    @Query("SELECT * FROM bookbytitleandpublisher WHERE tags CONTAINS ?0 ALLOW FILTERING")
    List<BookByTitleAndPublisher> findAllByTagsIsIn(String tag);

    @Query("DELETE FROM bookbytitleandpublisher where title = ?0 and publisher = ?1")
    void deleteBookByTitleAndPublisher(String title, String publisher);


}
